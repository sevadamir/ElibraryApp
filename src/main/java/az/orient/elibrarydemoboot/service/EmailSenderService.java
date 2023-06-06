package az.orient.elibrarydemoboot.service;

public interface EmailSenderService {

    void sendEmail(String to, String activationCode);
}
