package com.BerkanOzcelik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.Departments;


@Repository
public interface DepartmentsRepository  extends JpaRepository<Departments ,Long>{

}
