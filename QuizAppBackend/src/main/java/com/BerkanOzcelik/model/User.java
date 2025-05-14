package com.BerkanOzcelik.model;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.BerkanOzcelik.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Questions> questions;

    @ManyToOne
    @JoinColumn(name = "department_id") // department_id foreign key ismi
    private Departments department;  // departmentId yerine department olmalı

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole != null) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + this.userRole.name()));
        }
        return List.of(); // Eğer userRole null ise boş liste dönebiliriz
    }
}
