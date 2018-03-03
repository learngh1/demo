package com.example.mail_checker.impl_buffer;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by user on 30.10.2017.
 */
public class DBStorage implements Storage {
    private DBConnectionManager connectionManager;

    public DBStorage(String url, String login, String password) {
        connectionManager = new DBConnectionManager(url, login, password);
    }

    private Statement getStatement() {
        Connection connection = connectionManager.getConnection();
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String robotId, int msgHash, long time) {
        try {
            getStatement().executeUpdate("INSERT INTO msg_buffer(robot_id, msg_hash, msg_time) values('" + robotId + "', " + msgHash + ", " + time + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exist(String robotId, int msgHash) {
        ResultSet res;
        try {
            res = getStatement().executeQuery("SELECT robot_id FROM msg_buffer WHERE robot_id='" + robotId + "' AND msg_hash=" + msgHash);
            return res.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String robotId, int msgHash) {
        try {
            getStatement().execute("DELETE FROM msg_buffer WHERE robot_id='" + robotId + "' AND msg_hash=" + msgHash);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}