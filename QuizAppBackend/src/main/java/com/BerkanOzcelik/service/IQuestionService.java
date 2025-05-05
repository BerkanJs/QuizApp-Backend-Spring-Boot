package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoQuestions;
import com.BerkanOzcelik.dto.DtoQuestionsIU;
import com.BerkanOzcelik.model.Questions;

public interface IQuestionService {
    Questions createQuestion(DtoQuestionsIU dto);
    Questions updateQuestion(DtoQuestionsIU dto);
    void deleteQuestion(Long questionId);
    List<DtoQuestions> getAllQuestions();
    
}
