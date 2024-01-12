package com.example.inhamonchallenge.domain.mail.service;

import com.example.inhamonchallenge.domain.mail.dto.EmailVerifyResponse;
import com.example.inhamonchallenge.domain.mail.exception.AuthTimeOutException;
import com.example.inhamonchallenge.domain.mail.exception.NotFoundAuthException;
import com.example.inhamonchallenge.domain.mail.exception.WrongAuthCodeException;
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
        Optional<Mail> findEmail = mailRepository.findByEmail(email);
        if(findEmail.isPresent()) {
            mailRepository.delete(findEmail.get());
        }
        mailRepository.save(new Mail(email, authCode));
    }

    public EmailVerifyResponse verifiedCode(String email, String authCode) {
        checkDuplicatedEmail(email);
        Optional<Mail> findMail = mailRepository.findByEmail(email);
        if(findMail.isEmpty()) {
            throw new NotFoundAuthException();
        }
        if(isTimeDifferenceGreaterThan5Minutes(findMail.get().getCreatedAt())) {
            mailRepository.delete(findMail.get());
            throw new AuthTimeOutException();
        }
        if(!findMail.get().getCode().equals(authCode)) {
            throw new WrongAuthCodeException();
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

    private static boolean isTimeDifferenceGreaterThan5Minutes(LocalDateTime creationTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(creationTime, currentTime);
        long minutesDifference = Math.abs(duration.toMinutes());
        return minutesDifference > 5;
    }
}
