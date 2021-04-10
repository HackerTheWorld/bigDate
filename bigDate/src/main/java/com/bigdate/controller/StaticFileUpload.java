package com.bigdate.controller;
import java.util.concurrent.ConcurrentHashMap;

public class StaticFileUpload {
    
    public static ConcurrentHashMap<String,ThreadLocal<String[]>> conFile = new ConcurrentHashMap<>();

}
