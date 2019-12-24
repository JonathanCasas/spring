package com.joncasas.spring.module.customers.forms;

import com.joncasas.spring.croydon.validators.ExtensionFiles;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

    @ExtensionFiles(types = {})
    MultipartFile file;


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
