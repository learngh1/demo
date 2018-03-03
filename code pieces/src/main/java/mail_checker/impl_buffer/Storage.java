package com.example.mail_checker.impl_buffer;

/**
 * Created by user on 30.10.2017.
 */
public interface Storage {
    void write(String robotId, int msgHash, long time);
    boolean exist(String robotId, int msgHash);
    void remove(String robotId, int msgHash);
}
