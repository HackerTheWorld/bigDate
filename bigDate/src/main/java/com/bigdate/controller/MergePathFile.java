package com.bigdate.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MergePathFile {
    
    public static String merge(String fileGroup,String fileName){
        String[] cop = StaticFileUpload.conFile.get(fileGroup);
        String fileFinalPath = "D:/javaWorkSpace/bigDate/bigDate/uploadFile/"+fileName;
        Path tPath = Paths.get(fileFinalPath);
        try(FileChannel outChannel = new FileOutputStream(tPath.toFile(),true).getChannel()){
            for(int i=0;i<cop.length;i++){
                Path inPath = Paths.get(cop[i]);
                
                try(FileChannel inFileChannel = new FileInputStream(inPath.toFile()).getChannel()){
                    long pos = 0;
                    long size = inFileChannel.size();
                    while (pos < size) {
                       long posSize = inFileChannel.transferTo(0, 1000,outChannel);
                       pos = pos + posSize;
                    }
                    System.out.println(outChannel.size());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileFinalPath;
    }
}
