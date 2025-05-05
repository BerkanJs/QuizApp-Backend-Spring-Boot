package com.BerkanOzcelik.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.BerkanOzcelik.dto.DtoUserAnswers;
import com.BerkanOzcelik.dto.DtoUserAnswersIU;
import com.BerkanOzcelik.model.UserAnswers;

public interface IUserAnswersController {

    ResponseEntity<RootEntity<UserAnswers>> createAnswer(DtoUserAnswers dto);

    ResponseEntity<RootEntity<UserAnswers>> updateAnswer(Long id, DtoUserAnswersIU dto);

    ResponseEntity<RootEntity<Void>> deleteAnswer(Long id);

    ResponseEntity<RootEntity<List<UserAnswers>>> getAnswersByUserId(Long userId);

    ResponseEntity<RootEntity<List<UserAnswers>>> getAnswersByQuestionId(Long questionId);

    ResponseEntity<RootEntity<UserAnswers>> getAnswerById(Long id);
}