package com.example.mail_checker.impl_buffer;

import javax.mail.Message;

/**
 * Created by user on 30.10.2017.
 */
public interface Marker {
    void markAsProcessed(Message msg);
    boolean isProcessed(Message msg);
}
