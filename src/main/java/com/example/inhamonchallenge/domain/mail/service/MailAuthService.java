package com.example.inhamonchallenge.domain.mail.service;

import com.example.inhamonchallenge.domain.mail.dto.EmailVerifyResponse;
import com.example.inhamonchallenge.domain.mail.repository.MailRepository;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.domain.mail.domain.Mail;
import com.example.inhamonchallenge.domain.mail.exception.ExistEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class MailAuthService {

    private final UserRepository userRepository;
    private final MailService mailService;
    private final MailRepository mailRepository;

    public void sendCodeToEmail(String email) {
        checkDuplicatedEmail(email);
        String title = "인하먼챌 회원가입 인증";

        String authCode = createCode();
        mailService.sendEmail(email, title, authCode);
        mailRepository.findByEmail(email).ifPresentOrElse(
                mail -> mail.updateAuthCode(authCode),
                () -> mailRepository.save(new Mail(email, authCode))
        );
    }

    public EmailVerifyResponse verifiedCode(String email, String authCode) {
        checkDuplicatedEmail(email);
        Optional<Mail> findMail = mailRepository.findByEmail(email);
        if(findMail.isEmpty()) {
            return EmailVerifyResponse.from(false, "인증 정보가 존재하지 않습니다.");
        }
        if(isTimeDifferenceGreaterThan30Minutes(findMail.get().getCreatedDateTime())) {
            return EmailVerifyResponse.from(false, "인증 시간이 초과되었습니다.");
        }
        if(!findMail.get().getCode().equals(authCode)) {
            return EmailVerifyResponse.from(false, "인증 코드가 일치하지 않습니다.");
        }
        mailRepository.delete(findMail.get());
        return EmailVerifyResponse.from(true, "인증에 성공하였습니다.");
    }

    private void checkDuplicatedEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ExistEmailException();
        }
    }

    private String createCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000); // 0부터 999999 사이의 난수 생성
        return String.format("%06d", randomNumber);
    }

    private static boolean isTimeDifferenceGreaterThan30Minutes(LocalDateTime creationTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(creationTime, currentTime);
        long minutesDifference = Math.abs(duration.toMinutes());
        return minutesDifference > 30;
    }
}
