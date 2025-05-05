package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.DtoExams;
import com.BerkanOzcelik.enums.ExamStatus;

import java.util.List;

public interface IExamsService {
    DtoExams addExam(DtoExams dtoExams);

    DtoExams updateExam(Long examId, DtoExams dtoExams);

    void deleteExam(Long examId);

    List<DtoExams> listExams();

    DtoExams updateExamStatus(Long examId, ExamStatus examStatus);

}
