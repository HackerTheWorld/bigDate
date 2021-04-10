package com.bigdate.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/bigData")
public class BigDataUploadController{

    @PostMapping("/uploadBigData")
    @ResponseBody
    public JSONObject uploadBigData(@RequestParam("fileParam") MultipartFile partFile,
                                    @RequestParam("fileLength") Integer fileLength,
                                    @RequestParam("fileIndex") Integer fileIndex,
                                    @RequestParam("fileGroup") String fileGroup){
        JSONObject jsonObject = new JSONObject();
        try {
            String filePath = "D:/javaWorkSpace/bigDate/bigDate/uploadFile/"+fileGroup+fileIndex;
            Path path = Paths.get(filePath);
            partFile.transferTo(path);
            ThreadLocal<String[]> filePaths = new ThreadLocal<>();
                if(StaticFileUpload.conFile.containsKey(fileGroup)){
                    filePaths = StaticFileUpload.conFile.get(fileGroup);
                }else{
                    filePaths.set(new String[fileLength]);
                }
                filePaths.get()[fileIndex-1]= filePath;
                StaticFileUpload.conFile.put(fileGroup, filePaths);
                for(String str:StaticFileUpload.conFile.get(fileGroup).get()){
                    if(ObjectUtils.isEmpty(str)){
                        return jsonObject;
                    }
                }
            MergePathFile.merge(fileGroup, partFile.getOriginalFilename());
        } catch (IllegalStateException |IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
