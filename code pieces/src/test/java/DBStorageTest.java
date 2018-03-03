package com.example.test;

import junit.framework.TestCase;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import com.example.PropertyStorage;
import com.example.mail_checker.impl_buffer.DBStorage;
import com.example.mail_checker.impl_buffer.Storage;

import java.io.IOException;

/**
 * Created by user on 30.10.2017.
 */
public class DBStorageTest extends TestCase{
    private static final Logger log = Logger.getLogger(DBStorageTest.class);
    private PropertiesConfiguration config;
    private Storage storage;

    @Override
    protected void setUp() throws Exception {
        try {
            PropertyStorage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config = PropertyStorage.getConfiguration();
        storage = new DBStorage(config.getString("db.url"), config.getString("db.login"),config.getString("db.passowrd"));
    }

    public void test() {
        String robotId = "unit test";
        int msg_hash = -11;
        long msg_time = -111;

        boolean exist = storage.exist(robotId, msg_hash);
        assertEquals(false, exist);

        storage.write(robotId, msg_hash, msg_time);
        exist = storage.exist(robotId, msg_hash);
        assertEquals(true, exist);

        storage.remove(robotId, msg_hash);
        exist = storage.exist(robotId, msg_hash);
        assertEquals(false, exist);
    }
}