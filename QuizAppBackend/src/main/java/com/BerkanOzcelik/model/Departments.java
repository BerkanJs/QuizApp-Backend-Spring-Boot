package com.BerkanOzcelik.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Departments extends BaseEntity {

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "description")
    private String departmentDescription;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Exams> exams;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<User> users;


    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Questions> questions;
}
