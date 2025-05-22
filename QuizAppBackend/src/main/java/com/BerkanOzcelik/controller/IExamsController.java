package com.BerkanOzcelik.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.BerkanOzcelik.dto.DtoExams;
import com.BerkanOzcelik.enums.ExamStatus;

public interface IExamsController {
    
    ResponseEntity<RootEntity<DtoExams>> addExam(DtoExams dtoExams);

    ResponseEntity<RootEntity<DtoExams>> updateExam(Long examId, DtoExams dtoExams);

    ResponseEntity<RootEntity<String>> deleteExam(Long examId);

    ResponseEntity<RootEntity<Page<DtoExams>>> listExams(Pageable pageable);

    ResponseEntity<RootEntity<DtoExams>> updateExamStatus(Long examId, ExamStatus examStatus);
}
