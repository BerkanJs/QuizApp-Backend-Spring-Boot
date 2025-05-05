package com.BerkanOzcelik.controller;


import com.BerkanOzcelik.dto.DtoExamResults;
import com.BerkanOzcelik.dto.DtoExamResultsIU;
import com.BerkanOzcelik.model.ExamResults;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IExamResultController {

    ResponseEntity<RootEntity<ExamResults>> saveExamResult(DtoExamResultsIU input);

    ResponseEntity<RootEntity<List<DtoExamResults>>> getAllExamResults();

    ResponseEntity<RootEntity<List<DtoExamResults>>> getExamResultsByExamId(Long examId);
}