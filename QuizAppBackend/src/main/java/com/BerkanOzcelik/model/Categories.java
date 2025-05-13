package com.BerkanOzcelik.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categories extends BaseEntity {

    @Column(name = "name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;
}

