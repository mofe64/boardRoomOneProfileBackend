package com.example.boardroomoneprofiles.services;

import com.example.boardroomoneprofiles.web.requests.Base64ImageUploadRequest;
import com.example.boardroomoneprofiles.web.requests.ProfileImageUploadRequest;
import com.example.boardroomoneprofiles.web.responses.ProfileImageResponse;

public interface ProfileService {
    ProfileImageResponse uploadProfilePicture(ProfileImageUploadRequest request) throws Exception;
    ProfileImageResponse uploadProfilePicture(Base64ImageUploadRequest request) throws Exception;
}
