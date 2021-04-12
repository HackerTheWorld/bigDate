package com.bigdate.controller;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SafeList {
    
    private String[] strs;
    private ReentrantLock reentrantLock;

    public SafeList(String[] strs){
        reentrantLock = new ReentrantLock();
        this.strs = strs;
    }

    public void setStrs(int index,String str){
        try {
            if(reentrantLock.tryLock(1,TimeUnit.SECONDS)){
                strs[index] = str;
            }else{
                System.out.println("get lock faild");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            reentrantLock.unlock();
        }
    }

    public String getStrs(int index){
        try {
            if(reentrantLock.tryLock(1,TimeUnit.SECONDS)){
                return strs[index];
            }else{
                System.out.println("get lock faild");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            reentrantLock.unlock();
        }
        return null;
    }

    public String[] getStrs(){
        try {
            if(reentrantLock.tryLock(1,TimeUnit.SECONDS)){
                return strs;
            }else{
                System.out.println("get lock faild");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            reentrantLock.unlock();
        }
        return null;
    }

    public void onLock(){
        try {
            reentrantLock.lock();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public void unLock(){
        try {
            reentrantLock.unlock();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

}
