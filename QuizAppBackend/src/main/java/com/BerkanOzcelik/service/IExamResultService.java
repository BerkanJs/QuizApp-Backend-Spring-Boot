package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoExamResults;
import com.BerkanOzcelik.dto.DtoExamResultsIU;
import com.BerkanOzcelik.model.ExamResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IExamResultService {

    ExamResults saveExamResult(DtoExamResultsIU input);

    Page<DtoExamResults> getAllExamResults(Pageable pageable);


    List<DtoExamResults> getExamResultsByExamId(Long examId);
}