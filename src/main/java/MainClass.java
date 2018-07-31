import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

public class MainClass {

    public static void main(String[]args) throws Exception{

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        MailSenderClass mailSenderClass = (MailSenderClass) context.getBean("mailSenderClass");

        Properties properties = new Properties();
        properties.load(MainClass.class.getClassLoader().getResourceAsStream("mail.properties"));
        mailSenderClass.setProperties(properties);
        mailSenderClass.sendEmail();
    }
}
