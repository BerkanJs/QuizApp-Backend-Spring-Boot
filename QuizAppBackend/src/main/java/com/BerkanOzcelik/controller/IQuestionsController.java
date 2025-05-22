package com.BerkanOzcelik.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.BerkanOzcelik.dto.DtoQuestions;
import com.BerkanOzcelik.dto.DtoQuestionsIU;
import com.BerkanOzcelik.model.Questions;

public interface IQuestionsController {

    ResponseEntity<RootEntity<Questions>> createQuestion(DtoQuestionsIU dto);

    ResponseEntity<RootEntity<Questions>> updateQuestion(DtoQuestionsIU dto);

    ResponseEntity<RootEntity<String>> deleteQuestion(Long questionId);


    ResponseEntity<RootEntity<Page<DtoQuestions>>> getAllQuestions(Pageable pageable);
}