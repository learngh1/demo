package com.example.mail_checker.impl_buffer;

import com.example.PropertyStorage;
import com.example.mail_checker.MailChecker;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by user on 30.10.2017.
 */
public class BufferedMailChecker implements MailChecker {
    private Marker marker;
    private long lastCheckedBefore;

    public BufferedMailChecker(String robotId) {
        marker = new MarkerImpl(robotId);
        lastCheckedBefore = PropertyStorage.getConfiguration().getLong("mail2jira.lastchecked", 0);
    }

    public void onStartCheck() {}

    public boolean shouldProcessMessage(Message msg) throws MessagingException {
        Date msgDateObj = msg.getSentDate();
        long msgDate = ((Date) msgDateObj).getTime();
        boolean notCheckedBefore =  msgDate > lastCheckedBefore;
        return notCheckedBefore && !marker.isProcessed(msg);
    }

    public void onEndProcessMessage(Message msg) throws MessagingException {
        marker.markAsProcessed(msg);
    }

    public void onEndCheck() {}
}
