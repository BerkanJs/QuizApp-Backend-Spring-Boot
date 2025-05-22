package com.BerkanOzcelik.service;

import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService {

    String uploadPhoto(String questionId, MultipartFile file);

    String updatePhoto(String questionId, MultipartFile file);

    void deletePhoto(String questionId);


}
