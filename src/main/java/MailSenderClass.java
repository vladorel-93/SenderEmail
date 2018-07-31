import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;


public class MailSenderClass {

    private JavaMailSender mailSender;

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail() throws Exception
    {
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(properties.getProperty("mail.smtps.receiver")));

        message.setFrom(new InternetAddress(properties.getProperty("mail.smtps.user")));

        message.setSubject("Tests");

        String text = new Scanner(MailSenderClass.class.getResourceAsStream("email.html")).useDelimiter("\\A").next();
        text = text.replace("fio", properties.getProperty("mail.fio"));
        text = text.replace("phone", properties.getProperty("mail.phone"));
        message.setContent(text, "text/html; charset=utf-8");

        Transport transport = mailSession.getTransport();
        transport.connect(properties.getProperty("mail.smtps.user"), properties.getProperty("mail.password"));
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
