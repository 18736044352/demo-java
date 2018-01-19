package com.java.mail;
/**
 * User: nimin
 * Date: 14-3-14
 * Time: 下午1:38
 * Version: v1.0
 */


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送程序（!!!仅供内部发邮件使用!!!）
 * <p/>
 * User: nimin
 * Date: 14-3-14
 * Time: 上午10:01
 * Version: v1.0
 */
public class MailSender {

    /**
     * 邮件服务器
     */
    private String smtpHost;
    /**
     * 发送者
     */
    private String mailFrom;
    /**
     * 发送者签名
     */
    private String mailFromPersonal = "Monitor";
    /**
     * 接收者
     */
    private String mailTo;
    /**
     * 抄送者
     */
    private String mailCcTo = "";
    /**
     * 暗送者
     */
    private String mailBccTo = "";
    /**
     * 是否需要验证
     */
    private String needAuth = "true";

    public static void main(String[] args) {
        MailSender mail = MailSender.getMailSender();
    }

    public static MailSender getMailSender() {
        return getMailSender(MailConfig.MAIL_FROM, MailConfig.MAIL_TO);
    }

    public static MailSender getMailSender(String mailTo) {
        return getMailSender(MailConfig.MAIL_FROM, mailTo);
    }

    /**
     * 获取默认的邮件发送器实例<br>
     * 从资源文件里读取配置的邮件服务器、发送者、接收者、抄送者、暗送者
     *
     * @return
     */
    public static MailSender getMailSender(String mailFrom, String mailTo) {

        return new MailSender(
                MailConfig.SMTP_HOST,
                mailFrom,
                MailConfig.MAIL_FROM_PERSONAL,
                mailTo,
                MailConfig.MAIL_CC_TO,
                MailConfig.MAIL_BCC_TO,
                MailConfig.NEED_AUTH
        );
    }

    public static MailSender getMailSenderWithPersonal(String mailTo, String mailFromPersonal) {

        return new MailSender(
                MailConfig.SMTP_HOST,
                MailConfig.MAIL_FROM,
                mailFromPersonal,
                mailTo,
                MailConfig.MAIL_CC_TO,
                MailConfig.MAIL_BCC_TO,
                MailConfig.NEED_AUTH
        );
    }

    private MailSender(String from, String to) {
        this.mailFrom = from;
        this.mailTo = to;
    }

    /**
     * 只包含发件人和收件人的邮件发送器实例
     *
     * @param host 邮件服务器
     * @param from 发送者
     * @param to   接收者
     */
    private MailSender(String host, String from, String to) {
        this.smtpHost = host;
        this.mailFrom = from;
        this.mailTo = to;
    }

    /**
     * 构造邮件发送器实例
     *
     * @param host     邮件服务器
     * @param from     发送者
     * @param personal 签名
     * @param to       接收者
     * @param cc       抄送者
     * @param bcc      暗送者
     */
    private MailSender(String host, String from, String personal, String to, String cc, String bcc, String needAuth) {
        this.smtpHost = host;
        this.mailFrom = from;
        this.mailFromPersonal = personal;
        this.mailTo = to;
        this.mailCcTo = cc;
        this.mailBccTo = bcc;
        this.needAuth = needAuth;
    }

    /**
     * 发送邮件
     *
     * @param subject 邮件标题
     * @param content 邮件正文
     * @return 是否发送成功
     */
    public boolean sendMail(String subject, String content) {
        return sendMail(subject, content, null);
    }

    /**
     * 发送携带附件的邮件
     *
     * @param subject          邮件标题
     * @param content          邮件正文
     * @param attachedFileList 附件列表
     * @return 是否发送成
     */
    public boolean sendMail(String subject, String content, List<String> attachedFileList) {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", needAuth);
        props.put("mail.debug", true);

        try {
            // 邮件会话
            Session session = Session.getInstance(props, new MailAuthenticator());

            // 会话消息
            MimeMessage message = new MimeMessage(session);
            // 发送者
            try {
                message.setFrom(new InternetAddress(mailFrom, mailFromPersonal));
            } catch (UnsupportedEncodingException e) {
                message.setFrom(new InternetAddress(mailFrom));
                e.printStackTrace();
            }
            // 接收者
            InternetAddress[] toAddr = InternetAddress.parse(mailTo);
            message.setRecipients(Message.RecipientType.TO, toAddr);
            // 抄送者
            InternetAddress[] ccAddr = InternetAddress.parse(mailCcTo);
            message.setRecipients(Message.RecipientType.CC, ccAddr);
            // 暗送者
            InternetAddress[] bccAddr = InternetAddress.parse(mailBccTo);
            message.setRecipients(Message.RecipientType.BCC, bccAddr);
            // 回复地址
            InternetAddress[] replyAddress = {new InternetAddress(mailFrom)};
            message.setReplyTo(replyAddress);
            // 邮件标题
            message.setSubject(subject);
            // 邮件正文
            Multipart multiPart = new MimeMultipart();
            MimeBodyPart bodyContent = new MimeBodyPart();
            bodyContent.setContent(content == null ? "" : content, MailConfig.MIME_TYPE);
            multiPart.addBodyPart(bodyContent);
            if (attachedFileList != null) {
                for (String fileName : attachedFileList) {
                    MimeBodyPart filePart = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(fileName);
                    filePart.setDataHandler(new DataHandler(fds));
                    //解决乱码
                    fileName = MimeUtility.encodeText(fileName);
                    filePart.setFileName(fileName);
                    multiPart.addBodyPart(filePart);
                }
            }
            message.setContent(multiPart);
            message.setSentDate(new Date());

            // 发送器
            Transport transport = null;
            transport = session.getTransport("smtp");
            transport.connect(smtpHost, MailConfig.MAIL_ACCOUNT, MailConfig.MAIL_PASSWORD);
            // 发送
            Transport.send(message);
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
