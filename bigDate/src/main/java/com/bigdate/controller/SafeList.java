package com.bigdate.controller;

import java.util.concurrent.locks.ReentrantLock;

public class SafeList {
    
    private String[] strs;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public SafeList(String[] strs){
        this.strs = strs;
    }

    public void onLock(){
        reentrantLock.lock();
    }

    public void unLock(){
        reentrantLock.unlock();
    }

}
