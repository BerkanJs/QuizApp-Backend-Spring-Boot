package com.BerkanOzcelik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "user_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswers  extends BaseEntity{

    @Column(name = "selected_option")
    private String selectedOption;

    @Column(name="written_answer")
    private String writtenAnswer;

    @Column(name = "is_graded")
    private Boolean isGraded;

    @ManyToOne
    private User userId;

    @ManyToOne
    private Questions questionId;
    
}
