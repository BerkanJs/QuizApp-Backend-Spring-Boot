package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.DtoExams;
import com.BerkanOzcelik.enums.ExamStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IExamsService {
    DtoExams addExam(DtoExams dtoExams);

    DtoExams updateExam(Long examId, DtoExams dtoExams);

    void deleteExam(Long examId);



    Page<DtoExams> listExams(Pageable pageable);

    DtoExams updateExamStatus(Long examId, ExamStatus examStatus);

}
