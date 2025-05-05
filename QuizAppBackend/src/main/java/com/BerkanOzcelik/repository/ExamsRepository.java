package com.BerkanOzcelik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.Exams;

@Repository
public interface ExamsRepository  extends JpaRepository<Exams, Long>{

}
