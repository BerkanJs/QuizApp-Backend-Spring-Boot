package com.BerkanOzcelik.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.BerkanOzcelik.dto.DtoExamQuestions;
import com.BerkanOzcelik.model.ExamQuestions;

public interface IExamQuestionsController {

    ResponseEntity<RootEntity<ExamQuestions>> addQuestionToExam(DtoExamQuestions dto);

    ResponseEntity<RootEntity<String>> removeQuestionFromExam(Long examQuestionId);

    ResponseEntity<RootEntity<List<DtoExamQuestions>>> getQuestionsByExamId(Long examId);

    ResponseEntity<RootEntity<List<ExamQuestions>>> addMultipleQuestionsToExam(List<DtoExamQuestions> dtoList);

}