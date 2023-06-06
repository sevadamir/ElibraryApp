package az.orient.elibrarydemoboot.service;

import az.orient.elibrarydemoboot.entity.Customer;
import az.orient.elibrarydemoboot.enums.EnumAvailableStatus;
import az.orient.elibrarydemoboot.repository.CustomerRepository;
import az.orient.elibrarydemoboot.service.impl.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailSenderThread extends Thread{
    private final CustomerRepository customerRepository;
    private final EmailSender emailSender;

    private final JavaMailSender javaMailSender;

    @EventListener(ContextClosedEvent.class)
    public void startSendingEmails() {
//        String message = String.format(
//                "Hello, %s! \n" + "Welcome to E-library. Please, visit next link: http://localhost:8082/activate/%s",
//                customerRepository.getName(),
//                customerRepository.getActivationCode()
//        );
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    List<String> emails = customerRepository.getEmails();
                    System.out.println(emails);
                    for (String email : emails) {
                        try {
                            //emailSender.sendEmail(email, "Activation code", message);
                            MimeMessage message = javaMailSender.createMimeMessage();
                            MimeMessageHelper helper = new MimeMessageHelper(message, true);

                            helper.setTo(email);
                            helper.setSubject("Library");
                            helper.setText("Activate");

                            javaMailSender.send(message);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Customer> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
        System.out.println("hello");
        return customerList;
    }
}
