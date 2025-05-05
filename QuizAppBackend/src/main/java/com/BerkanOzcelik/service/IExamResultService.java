package com.BerkanOzcelik.service;

import java.util.List;

import com.BerkanOzcelik.dto.DtoExamResults;
import com.BerkanOzcelik.dto.DtoExamResultsIU;
import com.BerkanOzcelik.model.ExamResults;

public interface IExamResultService {

    ExamResults saveExamResult(DtoExamResultsIU input);

    List<DtoExamResults> getAllExamResults();

    List<DtoExamResults> getExamResultsByExamId(Long examId);
}