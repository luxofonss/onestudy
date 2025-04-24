package com.quyennv.lms.config;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private Lock lock = new ReentrantLock();

    private int count = 0;

    public void increment() {
        lock.lock();

        try {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }
}
