package com.BerkanOzcelik.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoController {

    ResponseEntity<RootEntity<String>> uploadPhoto(@RequestParam("questionId") String questionId,
                                                   @RequestParam("file") MultipartFile file);

    ResponseEntity<RootEntity<String>> updatePhoto(@RequestParam("questionId") String questionId,
                                                   @RequestParam("file") MultipartFile file);

    ResponseEntity<RootEntity<String>> deletePhoto(@RequestParam("questionId") String questionId);
}
