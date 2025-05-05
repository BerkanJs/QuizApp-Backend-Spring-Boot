package com.BerkanOzcelik.model;

import java.util.Date;

import com.BerkanOzcelik.enums.ExamStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exams extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "starting_time")
    private Date startingTime;

    @Column(name = "ending_time")
    private Date endingTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ExamStatus examStatus;
}
