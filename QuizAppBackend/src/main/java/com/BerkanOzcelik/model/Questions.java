package com.BerkanOzcelik.model;

import java.util.Date;
import java.util.List;

import com.BerkanOzcelik.enums.QuestionType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Questions extends BaseEntity {

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_text")
    private List<String> options;

    @Column(name = "correct_option")
    private String option;

    @Column(name = "reviewed_by")
    private Date reviewedBy;

    @Column(name = "reviewed_at")
    private Date reviewedAt;

    @Column(name = "question_point")
    private Long questionPoint;

    @OneToOne
    private User userId;

    @OneToOne
    private Departments departmentId;

    @ManyToOne
    private Categories categorieId;

}
