package com.BerkanOzcelik.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BerkanOzcelik.dto.DtoExamQuestions;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.ExamQuestions;
import com.BerkanOzcelik.model.Exams;
import com.BerkanOzcelik.model.Questions;
import com.BerkanOzcelik.repository.ExamQuestionsRepository;
import com.BerkanOzcelik.repository.ExamsRepository;
import com.BerkanOzcelik.repository.QuestionsRepository;
import com.BerkanOzcelik.service.IExamQuestionsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamQuestionsServiceImpl implements IExamQuestionsService {
    private final ExamQuestionsRepository examQuestionsRepository;
    private final ExamsRepository examsRepository;
    private final QuestionsRepository questionsRepository;

    @Override
    public ExamQuestions addQuestionToExam(DtoExamQuestions dto) {
        Exams exam = examsRepository.findById(dto.getExamId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Exam")));

        Questions question = questionsRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

        ExamQuestions examQuestion = new ExamQuestions();
        examQuestion.setExamId(exam);
        examQuestion.setQuestionId(question);

        return examQuestionsRepository.save(examQuestion);
    }

    @Override
    public void removeQuestionFromExam(Long examQuestionId) {
        ExamQuestions examQuestion = examQuestionsRepository.findById(examQuestionId)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Exam Question")));

        examQuestionsRepository.delete(examQuestion);
    }

    @Override
    public List<DtoExamQuestions> getQuestionsByExamId(Long examId) {
        List<ExamQuestions> examQuestions = examQuestionsRepository.findByExamId_Id(examId);
        return examQuestions.stream().map(eq -> {
            DtoExamQuestions dto = new DtoExamQuestions();
            dto.setId(eq.getId());
            dto.setExamId(eq.getExamId().getId());  // sadece examId'nin id'si
            dto.setQuestionId(eq.getQuestionId().getId());  // sadece questionId'nin id'si
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ExamQuestions> addMultipleQuestionsToExam(List<DtoExamQuestions> dtoList) {
        List<ExamQuestions> savedQuestions = new ArrayList<>();

        for (DtoExamQuestions dto : dtoList) {
            Exams exam = examsRepository.findById(dto.getExamId())
                    .orElseThrow(() -> new BaseException(
                            new ErrorMessage(MessageType.NO_RECORD_EXIST, "Exam")));

            Questions question = questionsRepository.findById(dto.getQuestionId())
                    .orElseThrow(() -> new BaseException(
                            new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

            ExamQuestions examQuestion = new ExamQuestions();
            examQuestion.setExamId(exam);
            examQuestion.setQuestionId(question);

            savedQuestions.add(examQuestion);
        }

        return examQuestionsRepository.saveAll(savedQuestions);
    }


}
