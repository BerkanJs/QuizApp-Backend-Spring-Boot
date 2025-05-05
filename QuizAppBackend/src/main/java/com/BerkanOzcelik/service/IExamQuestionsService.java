package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoExamQuestions;
import com.BerkanOzcelik.model.ExamQuestions;

public interface IExamQuestionsService {
    ExamQuestions addQuestionToExam(DtoExamQuestions dto);
    void removeQuestionFromExam(Long examQuestionId);
    List<DtoExamQuestions> getQuestionsByExamId(Long examId);
    List<ExamQuestions> addMultipleQuestionsToExam(List<DtoExamQuestions> dtoList);
}