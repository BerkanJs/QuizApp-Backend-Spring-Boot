package com.BerkanOzcelik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.Categories;

@Repository
public interface CategoriesRepository  extends JpaRepository<Categories ,Long>{

}
