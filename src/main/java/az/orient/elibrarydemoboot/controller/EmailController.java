//package az.orient.elibrarydemoboot.controller;
//
//import az.orient.elibrarydemoboot.service.EmailSenderService;
//import az.orient.elibrarydemoboot.service.impl.EmailSender;
//import az.orient.elibrarydemoboot.util.EmailMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.AddressException;
//import java.io.IOException;
//
//@RestController
//public class EmailController {
//
//    private final EmailSender emailSender;
//
//    public EmailController(EmailSender emailSender) {
//        this.emailSender = emailSender;
//    }
//
//    @PostMapping( "/sendEmail")
//    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
//        this.emailSender.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
//        return ResponseEntity.ok("Success");
//    }
//}
