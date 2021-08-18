package com.example.boardroomoneprofiles.web.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Base64ImageUploadRequest {
    @NotBlank(message = "base64Url cannot be blank")
    private String base64Url;
}
