package com.java.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱认证
 *
 ** User: nimin
 * Date: 14-3-14
 * Time: 下午1:41
 * Version: v1.0
 */
public class MailAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(MailConfig.MAIL_ACCOUNT, MailConfig.MAIL_PASSWORD);
    }

}
