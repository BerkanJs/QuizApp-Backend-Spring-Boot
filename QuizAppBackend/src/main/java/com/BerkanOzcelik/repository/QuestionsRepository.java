package com.BerkanOzcelik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions,Long>{

}
