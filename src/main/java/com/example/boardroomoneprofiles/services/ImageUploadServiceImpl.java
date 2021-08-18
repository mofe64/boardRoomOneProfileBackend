package com.example.boardroomoneprofiles.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.boardroomoneprofiles.BgRemoveConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
@Slf4j
public class ImageUploadServiceImpl implements UploadService {
    private final Cloudinary cloudinary;
    private BgRemoveConfig bgRemoveConfig;
    private RestTemplate restTemplate;

    @Autowired
    public ImageUploadServiceImpl(Cloudinary cloudinary, BgRemoveConfig config, RestTemplate restTemplate) {
        this.cloudinary = cloudinary;
        this.bgRemoveConfig = config;
        this.restTemplate = restTemplate;
    }

    @Override
    public Map<?, ?> uploadImage(MultipartFile file) throws Exception {
      byte[] bytes =  removeImageBackground(file);
        Map<?, ?> imageProps = ObjectUtils.asMap(
                "transformation", new Transformation().aspectRatio("1:1").width(500).gravity("faces").background("black").crop("fill"),
                "folder", "boardRoomOne"
        );
        return cloudinary.uploader().upload(bytes, imageProps);
    }

    @Override
    public Map<?, ?> uploadImage(String base64Url) throws Exception {

        Map<?, ?> imageProps = ObjectUtils.asMap(
                "background_removal", "cloudinary_ai:fine_edges",
                "transformation", new Transformation().aspectRatio("1:1").width(500).gravity("faces").background("black").crop("fill"),
                "folder", "boardRoomOne"
        );
        return cloudinary.uploader().upload(base64Url, imageProps);
    }

    private byte[] removeImageBackground(MultipartFile file) throws IOException {
        Response response = Request.Post("https://api.remove.bg/v1.0/removebg")
                .addHeader("X-Api-Key", bgRemoveConfig.getSecretKey())
                .body(
                        MultipartEntityBuilder.create()
                        .addBinaryBody("image_file", file.getInputStream())
                        .addTextBody("size", "auto")
                        .build()
                ).execute();
        Content c = response.returnContent();
        return c.asBytes();
    }
}
