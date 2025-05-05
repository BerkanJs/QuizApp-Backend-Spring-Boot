package com.BerkanOzcelik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.ExamResults;

import java.util.List;

@Repository
public interface ExamResultsRepository extends JpaRepository<ExamResults,Long>{

    List<ExamResults> findByExamId_Id(Long examId);

}
