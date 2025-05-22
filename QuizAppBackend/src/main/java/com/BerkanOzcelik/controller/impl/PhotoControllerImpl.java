package com.BerkanOzcelik.controller.impl;

import com.BerkanOzcelik.controller.IPhotoController;
import com.BerkanOzcelik.controller.RestBaseController;

import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.service.IPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class PhotoControllerImpl extends RestBaseController implements IPhotoController {

    private final IPhotoService photoService;

    @PostMapping("/upload")
    @Override
    public ResponseEntity<RootEntity<String>> uploadPhoto(@RequestParam("questionId") String questionId,
                                                          @RequestParam("file") MultipartFile file) {
        String photoUrl = photoService.uploadPhoto(questionId, file);
        return ResponseEntity.ok(ok(photoUrl));
    }

    @PutMapping("/update")
    @Override
    public ResponseEntity<RootEntity<String>> updatePhoto(@RequestParam("questionId") String questionId,
                                                          @RequestParam("file") MultipartFile file) {
        String photoUrl = photoService.updatePhoto(questionId, file);
        return ResponseEntity.ok(ok(photoUrl));
    }
    @DeleteMapping("/delete")
    @Override
    public ResponseEntity<RootEntity<String>> deletePhoto(@RequestParam("questionId") String questionId) {
        photoService.deletePhoto(questionId);
        return ResponseEntity.ok(ok("Fotoğraf başarıyla silindi."));
    }


}
