package com.joncasas.spring.module.customers.services;

import com.joncasas.spring.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class UploadFileService {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public void upload(MultipartFile file, String name) throws IOException {
        File path = new File(uploadDir + File.separator + "Documentos" + File.separator + "uploadsApp" + File.separator);
        if (!path.exists()) {
            path.mkdirs();
        }
        Path copyLocation = Paths.get(path.getPath() + File.separator + name);
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    }

    public void upload(MultipartFile file) throws IOException {
        File path = new File(uploadDir + File.separator + "Documentos" + File.separator + "uploadsApp" + File.separator);
        if (!path.exists()) {
            path.mkdirs();
        }
        Path copyLocation = Paths.get(path.getPath() + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
    }
}
