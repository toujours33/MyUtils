package cst.wyz;

import cst.wyz.mail.MailConfig;
import cst.wyz.mail.MailUtil;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class MailTest {

    @Test
    public void testSend(){
        MailConfig config = new MailConfig("qq.properties");
        MailUtil.loadConfig(config);
        try {
            MailUtil.sendMail("1012242313@qq.con","Hello","邮件测试");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfigSend(){
        MailConfig config = new MailConfig("qq.properties");
        MailUtil.loadConfig(config);
        try {
            String[] files = {"/Users/jerrywang/WebLearning/spring/springmvc流程图.pdf"};
            MailUtil.sendMail("1179399706@qq.com","hello","Test Mail Send With attach files",files);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
