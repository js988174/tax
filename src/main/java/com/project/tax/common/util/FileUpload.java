package com.project.tax.common.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUpload {

    public String save(MultipartFile file) throws IOException {

        String dir = System.getProperty("user.dir") + "/uploads/";

        File folder = new File(dir);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File dest = new File(dir + fileName);
        file.transferTo(dest);

        return fileName;
    }

}
