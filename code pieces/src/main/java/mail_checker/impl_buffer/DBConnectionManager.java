package com.example.mail_checker.impl_buffer;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 01.11.2017.
 */
public class DBConnectionManager {
    private static final Logger log = Logger.getLogger(DBConnectionManager.class);
    private Connection connection;
    private final int MAX_RECONNECT_ATTEMPTS = 7200;//20 hours
    private final int RECONNECT_TIMEOUT_SEC = 10;
    private String url;
    private String login;
    private String password;

    public DBConnectionManager(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        connection = getConnection();
    }

    public Connection getConnection() {
        return getConnection(MAX_RECONNECT_ATTEMPTS);
    }

    private Connection getConnection(int leftAttempts) {
        if (isValid(connection)) {
            return connection;
        } else if (leftAttempts > 0) {
            try {
                connection = createConnection();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
            sleep(RECONNECT_TIMEOUT_SEC * 1000);
            return getConnection(leftAttempts--);
        } else {
            throw new RuntimeException("Cant connect to DB");
        }
    }

    private boolean isValid(Connection connection) {
        try {
            return connection != null && connection.isValid(0);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    private void sleep(int msecs) {
        try {
            Thread.sleep(msecs);
        } catch (InterruptedException e) {
            log.warn(e.getMessage(), e);
        }
    }
}
