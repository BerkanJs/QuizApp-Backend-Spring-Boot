package com.BerkanOzcelik.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BerkanOzcelik.dto.DtoExamResults;
import com.BerkanOzcelik.dto.DtoExamResultsIU;
import com.BerkanOzcelik.model.ExamResults;
import com.BerkanOzcelik.model.Exams;
import com.BerkanOzcelik.model.User;
import com.BerkanOzcelik.repository.ExamResultsRepository;
import com.BerkanOzcelik.repository.ExamsRepository;
import com.BerkanOzcelik.repository.UserRepository;
import com.BerkanOzcelik.service.IExamResultService;

import lombok.RequiredArgsConstructor;

import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements IExamResultService {

    @Autowired
    private ExamResultsRepository examResultRepository;

    @Autowired
    private ExamsRepository examsRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ExamResults saveExamResult(DtoExamResultsIU input) {
     
        Exams exam = examsRepository.findById(input.getExamId()) 
        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Exam")));
       
        User user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User")));

      
        ExamResults result = new ExamResults();
        result.setExamId(exam); 
        result.setUserId(user);  
        result.setScore(input.getScore());  
        result.setExamResultsStatus(input.getExamResultsStatus());  
     
        return examResultRepository.save(result);
    }


    @Override
    public List<DtoExamResults> getAllExamResults() {

        List<ExamResults> results = examResultRepository.findAll();


        return results.stream().map(result -> {
            DtoExamResults dto = new DtoExamResults();
            dto.setId(result.getId());
            dto.setUserId(result.getUserId().getId());
            dto.setExamId(result.getExamId().getId());
            dto.setScore(result.getScore());
            dto.setExamResultsStatus(result.getExamResultsStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<DtoExamResults> getExamResultsByExamId(Long examId) {
        List<ExamResults> results = examResultRepository.findByExamId_Id(examId);

        if (results.isEmpty()) {
            System.out.println("No results found for examId: " + examId);
        }

        return results.stream().map(result -> {
            DtoExamResults dto = new DtoExamResults();
            dto.setId(result.getId());
            dto.setUserId(result.getUserId().getId());
            dto.setExamId(result.getExamId().getId());
            dto.setScore(result.getScore());
            dto.setExamResultsStatus(result.getExamResultsStatus());
            return dto;
        }).collect(Collectors.toList());
    }




}
