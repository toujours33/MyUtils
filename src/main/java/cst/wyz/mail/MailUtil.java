package cst.wyz.mail;

import org.omg.CORBA.TIMEOUT;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailUtil {

    private static String PROTOCOL = "SMTP";
    private static String HOST = "smtp.163.com";
    private static Integer PORT = 25;
    private static String USERNAME = "18270885240@163.com";
    private static String PASSWORD = "8023jerry";
    private static String timeout = "25000";
    private static String personal = "Mr.Wang";

    public static void loadConfig(MailConfig mailConfig){
        HOST = mailConfig.getMailHost();
        PORT = mailConfig.getMailPort();
        USERNAME = mailConfig.getMailUserName();
        PASSWORD = mailConfig.getMailPassword();
        timeout = mailConfig.getTimeout() != null ? mailConfig.getTimeout() : "25000";
        personal = mailConfig.getPersonal() != null ? mailConfig.getPersonal() : "Mr.Wang";
    }


    private static Session createSession(){
        Properties props = new Properties();
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.socketFactory.port", String.valueOf(PORT));
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout", timeout);
        // 验证账号及密码，密码对应邮箱授权码（163密码可行，qq必须授权码）
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        return session;
    }

    //添加多个附件
    public static void addTach(String[] fileList, Multipart multipart) throws MessagingException, UnsupportedEncodingException {
        for (int index = 0; index < fileList.length; index++) {
            MimeBodyPart mailArchieve = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(fileList[index]);
            mailArchieve.setDataHandler(new DataHandler(fds));
            mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),"UTF-8","B"));
            multipart.addBodyPart(mailArchieve);
        }
    }

    public static void sendMail(String to,String subject,String content,String[] fileList) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(createSession());

        message.setFrom(new InternetAddress(USERNAME));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));

        message.setSubject(subject);

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content,"text/html;charset=utf-8");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (fileList != null)
            addTach(fileList,multipart);

        message.setContent(multipart);

        Transport.send(message);
    }

    public static void sendMail(String to,String subject,String content) throws MessagingException, UnsupportedEncodingException {
        sendMail(to, subject, content,null);
    }

}
