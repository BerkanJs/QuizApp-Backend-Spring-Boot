package com.BerkanOzcelik.model;

import java.util.Date;

import com.BerkanOzcelik.enums.ExamResultsStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exam_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamResults extends BaseEntity{

    @Column(name = "score")
    private Long score;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ExamResultsStatus examResultsStatus;

    @Column(name="submitted_at")
    private Date submittedAt;

    @ManyToOne
    private Exams examId;

    @ManyToOne
    private User userId;







}
