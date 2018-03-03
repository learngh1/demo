package com.example.mail_checker.impl_buffer;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import com.example.PropertyStorage;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by user on 30.10.2017.
 */
public class MarkerImpl implements Marker {
    private static final Logger log = Logger.getLogger(MarkerImpl.class);

    private String robotId;
    private Storage storage;

    public MarkerImpl(String robotId) {
        this.robotId = robotId;
        PropertiesConfiguration config = PropertyStorage.getConfiguration();
        this.storage = new DBStorage(config.getString("db.url"), config.getString("db.login"),config.getString("db.password"));
    }

    private int getHash(Message msg) {
        try {
            return Long.valueOf(msg.getSentDate().getTime()).hashCode();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void markAsProcessed(Message msg) {
        Date date = null;
        try {
             date = msg.getSentDate();
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }

        long time = date != null ? date.getTime() : 0L;

        storage.write(robotId, getHash(msg), time);
    }

    public boolean isProcessed(Message msg) {
        return storage.exist(robotId, getHash(msg));
    }
}