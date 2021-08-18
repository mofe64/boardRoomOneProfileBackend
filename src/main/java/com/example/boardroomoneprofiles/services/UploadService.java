package com.example.boardroomoneprofiles.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    Map<?, ?> uploadImage(MultipartFile file ) throws Exception;
    Map<?, ?> uploadImage(String base64Url ) throws Exception;
}
