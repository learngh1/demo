package com.example.mail_checker;

import com.example.PropertyStorage;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by user on 30.10.2017.
 */
public class OldMailChecker implements MailChecker {
    private static final long MAX_MAIL_CHECK_INTERVAL = 14*24*3600*1000; // two weeks

    private long lastCheckedDate_1 = 0L;
    private long newLastDate = 0L;
    private long lastChecked = 0L;

    public OldMailChecker(long lastCheckedDate) {
        lastCheckedDate_1 = Math.max(lastCheckedDate, new Date().getTime() - MAX_MAIL_CHECK_INTERVAL);
    }

    public boolean shouldProcessMessage(Message msg) throws MessagingException {
        Date msgDateObj = msg.getSentDate();
        long msgDate = ((Date) msgDateObj).getTime();
        return msgDate > lastCheckedDate_1;
    }

    public void onEndProcessMessage(Message msg) throws MessagingException {
        Date msgDateObj = msg.getSentDate();
        long msgDate = ((Date) msgDateObj).getTime();
        long newVal = newLastDate;
        if (msgDate > newLastDate)
            newVal = msgDate;
        this.newLastDate = newVal;
    }

    public void onEndCheck() {
        lastCheckedDate_1 = newLastDate;
        if (lastCheckedDate_1 != lastChecked) {
            PropertyStorage.getConfiguration().setProperty("mail2jira.lastchecked", lastCheckedDate_1);
        }
    }

    public void onStartCheck() {
        newLastDate = lastCheckedDate_1;
        lastChecked = lastCheckedDate_1;
    }
}