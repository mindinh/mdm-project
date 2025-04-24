package com.mdm.project.service;


import com.mdm.project.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.avatar}")
    private String uploadAvatar;

    public String copyFile(MultipartFile file) {
        String imgUrl = "";
        try {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }

            Path filePath = root.resolve(file.getOriginalFilename());
            imgUrl = "/images/" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new FileUploadException("File upload failed " + e.getMessage());
        }

        return imgUrl;
    }

    public String copyAvatarFile(MultipartFile file, String userId) {
        String imgUrl = "";
        try {
            Path root = Paths.get(uploadAvatar);
            if (!Files.exists(root)) {
                Files.createDirectory(root);
            }

            String originalName = file.getOriginalFilename();
            String extension = "";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            String newFileName = "avatar_" + userId + extension;

            Path filePath = root.resolve(newFileName);
            imgUrl = "/avatars/" + newFileName;
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Exception e) {
            throw new FileUploadException("File upload failed " + e.getMessage());
        }

        return imgUrl;
    }

    public Resource loadFile(String filename) {
        try {
            Path imagePath = Paths.get(uploadPath).resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists()) {
                return resource;
            }
            else {
                throw new FileNotFoundException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new FileUploadException("File upload failed " + e.getMessage());
        }

    }
}
