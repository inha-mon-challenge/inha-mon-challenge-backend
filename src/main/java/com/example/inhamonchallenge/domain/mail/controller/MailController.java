package com.example.inhamonchallenge.domain.mail.controller;

import com.example.inhamonchallenge.domain.common.dto.MessageResponse;
import com.example.inhamonchallenge.domain.mail.dto.EmailVerifyResponse;
import com.example.inhamonchallenge.domain.mail.service.MailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
public class MailController {
    private final MailAuthService mailAuthService;

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestParam String email) {
        mailAuthService.sendCodeToEmail(email);
        return ResponseEntity.ok().body(MessageResponse.from("이메일을 발송하였습니다."));
    }

    @GetMapping("/verify")
    public ResponseEntity<EmailVerifyResponse> verificationEmail(@RequestParam String email,
                                                                 @RequestParam String authCode) {
        EmailVerifyResponse response = mailAuthService.verifiedCode(email, authCode);

        return ResponseEntity.ok(response);
    }
}
