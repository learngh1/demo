package com.example.mail_checker;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Created by user on 30.10.2017.
 */
public interface MailChecker {
    void onStartCheck();
    boolean shouldProcessMessage(Message msg) throws MessagingException;
    void onEndProcessMessage(Message msg) throws MessagingException;
    void onEndCheck();
}
