package com.example.boardroomoneprofiles.services;

import com.example.boardroomoneprofiles.web.requests.Base64ImageUploadRequest;
import com.example.boardroomoneprofiles.web.requests.ProfileImageUploadRequest;
import com.example.boardroomoneprofiles.web.responses.ProfileImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    private UploadService uploadService;

    @Override
    public ProfileImageResponse uploadProfilePicture(ProfileImageUploadRequest request) throws Exception {
       Map<?, ?> res = uploadService.uploadImage(request.getImageFile());
       log.info("cloudinary return ---> {}", res);
       ProfileImageResponse response = new ProfileImageResponse();
       response.setProfileImageUrl((String) res.get("secure_url"));
       return response;

    }

    @Override
    public ProfileImageResponse uploadProfilePicture(Base64ImageUploadRequest request) throws Exception {
        Map<?, ?> res = uploadService.uploadImage(request.getBase64Url());
        log.info("cloudinary return ---> {}", res);
        ProfileImageResponse response = new ProfileImageResponse();
        response.setProfileImageUrl((String) res.get("secure_url"));
        return response;
    }
}
