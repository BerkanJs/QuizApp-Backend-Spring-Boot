package com.BerkanOzcelik.service.impl;

import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.Questions;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.repository.QuestionsRepository;
import com.BerkanOzcelik.service.IPhotoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements IPhotoService {

    private final QuestionsRepository questionsRepository;
    private static final String PHOTO_DIRECTORY = "uploads/questions";

    @Override
    public String uploadPhoto(String questionId, MultipartFile file) {


        Questions question = questionsRepository.findById(Long.parseLong(questionId))
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

        String photoUrl = photoFunction.apply(questionId, file);
        question.setPhotoUrl(photoUrl);
        questionsRepository.save(question);

        return photoUrl;
    }

    @Override
    public String updatePhoto(String questionId, MultipartFile file) {
        Questions question = questionsRepository.findById(Long.parseLong(questionId))
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

        // Eski dosya varsa sil
        deletePhysicalFileIfExists(question.getPhotoUrl());

        // Yeni fotoğrafı yükle
        String photoUrl = photoFunction.apply(questionId, file);
        question.setPhotoUrl(photoUrl);
        questionsRepository.save(question);
        return photoUrl;
    }

    @Override
    public void deletePhoto(String questionId) {
        Questions question = questionsRepository.findById(Long.parseLong(questionId))
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

        // Fotoğraf dosyasını sil
        deletePhysicalFileIfExists(question.getPhotoUrl());

        // DB'deki alanı null yap
        question.setPhotoUrl(null);
        questionsRepository.save(question);
    }


    private void deletePhysicalFileIfExists(String photoUrl) {
        if (photoUrl == null) return;

        try {
            String filename = Paths.get(new URI(photoUrl).getPath()).getFileName().toString();
            Path filePath = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting image file", e);
        }
    }

    private final Function<String, String> fileExtension = filename ->
            Optional.ofNullable(filename)
                    .filter(name -> name.contains("."))
                    .map(name -> "." + name.substring(filename.lastIndexOf('.') + 1))
                    .orElse(".png");

    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }

            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/uploads/questions/")
                    .path(filename)
                    .toUriString();

        } catch (IOException e) {
            throw new RuntimeException("Unable to save image", e);
        }
    };
}

