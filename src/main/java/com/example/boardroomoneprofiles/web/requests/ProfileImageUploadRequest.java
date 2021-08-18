package com.example.boardroomoneprofiles.web.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ProfileImageUploadRequest {
    @NotNull(message = "profileImage cannot be null")
    private MultipartFile imageFile;
}
