package com.BerkanOzcelik.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "exam_questions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ExamQuestions extends BaseEntity {

    @ManyToOne
    private Exams examId;

    @ManyToOne
    private Questions questionId;




    
}
