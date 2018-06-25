package com.basicservlet.controller;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Rith on 6/22/2018.
 */
public class FileModel {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
