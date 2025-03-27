package com.together.furture.util;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Component
public class FileUploadUtil {

    @Value("${file.upload-dir}")
    private String savePath;

    public MultipartRequest uploadFile(HttpServletRequest request) throws IOException {
        File directory = new File(savePath);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("디렉토리 생성: " + savePath);
        }

        int maxSize = 1024 * 1024 * 10; // 10MB
        String encoding = "UTF-8";
        DefaultFileRenamePolicy rename = new DefaultFileRenamePolicy();

        return new MultipartRequest(request, savePath, maxSize, encoding, rename);
    }
    

}