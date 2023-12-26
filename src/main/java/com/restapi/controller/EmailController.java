package com.restapi.controller;

import com.restapi.repository.UserRepository;
import com.restapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody long id) {
        String to = userRepository.findById(id).get().getEmail();
        String subject = "Event Booking Registration";
        String body = "Your booing has been confirmed";

        emailService.sendSimpleEmail(to, subject, body);

        return "Email sent successfully!";
    }
}
