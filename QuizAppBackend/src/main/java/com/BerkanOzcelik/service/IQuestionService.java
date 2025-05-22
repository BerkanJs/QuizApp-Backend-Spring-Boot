package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoQuestions;
import com.BerkanOzcelik.dto.DtoQuestionsIU;
import com.BerkanOzcelik.model.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IQuestionService {
    Questions createQuestion(DtoQuestionsIU dto);
    Questions updateQuestion(DtoQuestionsIU dto);
    void deleteQuestion(Long questionId);
    Page<DtoQuestions> getAllQuestions(Pageable pageable);
    
}
