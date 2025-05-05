package com.BerkanOzcelik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.ExamQuestions;

@Repository
public interface ExamQuestionsRepository  extends JpaRepository<ExamQuestions, Long> {
    List<ExamQuestions> findByExamId_Id(Long examId);

}
