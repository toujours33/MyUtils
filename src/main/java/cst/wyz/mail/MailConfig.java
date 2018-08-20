package cst.wyz.mail;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author jerrywang
 *
 * 邮件相关工具类
 */
public class MailConfig {

    private String propertyFilePath;

    private String mailHost;

    private Integer mailPort;

    private String mailUserName;

    private String mailPassword;

    private String timeout;

    private String personal;

    public String getMailHost() {
        return mailHost;
    }

    public Integer getMailPort() {
        return mailPort;
    }

    public String getMailUserName() {
        return mailUserName;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public String getTimeout() {
        return timeout;
    }

    public String getPersonal() {
        return personal;
    }

    public MailConfig(String propertyFilePath) {
        this.propertyFilePath = propertyFilePath;
        Properties properties = new Properties();
        try {
            InputStream in = MailConfig.class.getClassLoader().getResourceAsStream(propertyFilePath);
            System.out.println(MailConfig.class.getClassLoader());
            System.out.println(MailConfig.class.getClassLoader().getResourceAsStream(""));
            properties.load(in);
            in.close();
            mailHost = properties.getProperty("mailHost");
            mailPort = Integer.parseInt(properties.getProperty("mailPort"));
            mailUserName = properties.getProperty("mailUserName");
            mailPassword = properties.getProperty("mailPassword");
            if (properties.containsKey("timeout"))
                timeout = properties.getProperty("timeout");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
