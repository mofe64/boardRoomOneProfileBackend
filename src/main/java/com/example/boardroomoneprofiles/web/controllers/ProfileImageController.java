package com.example.boardroomoneprofiles.web.controllers;

import com.example.boardroomoneprofiles.services.ProfileService;
import com.example.boardroomoneprofiles.web.requests.Base64ImageUploadRequest;
import com.example.boardroomoneprofiles.web.requests.ProfileImageUploadRequest;
import com.example.boardroomoneprofiles.web.responses.ApiResponse;
import com.example.boardroomoneprofiles.web.responses.ProfileImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/profiles")
@Slf4j
public class ProfileImageController {

    @Autowired
    private ProfileService profileService;


    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        log.info("file --> {}", file);
        ProfileImageUploadRequest request = new ProfileImageUploadRequest();
        request.setImageFile(file);
        try {
            ProfileImageResponse response = profileService.uploadProfilePicture(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    ResponseEntity<?> uploadImage(@Valid @RequestBody Base64ImageUploadRequest request) {
        try {
            ProfileImageResponse response = profileService.uploadProfilePicture(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
