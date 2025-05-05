package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoUserAnswers;
import com.BerkanOzcelik.dto.DtoUserAnswersIU;
import com.BerkanOzcelik.model.UserAnswers;

public interface IUserAnswersService {
    UserAnswers createAnswer(DtoUserAnswers dto);

    UserAnswers updateAnswer(DtoUserAnswersIU dto);

    void deleteAnswer(Long answerId);

    List<UserAnswers> getAnswersByUserId(Long userId);

    List<UserAnswers> getAnswersByQuestionId(Long questionId);

    UserAnswers getAnswerById(Long id);

}