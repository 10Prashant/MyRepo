package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@Table(name = "users")

//@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;


    @NotBlank(message = "Username is required")

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")

    private String username;


    @NotBlank(message = "Password is required")

    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")

    private String password;


    @NotBlank(message = "Role is required")

    @Size(max = 50, message = "Role must be less than or equal to 50 characters")

    private String role;


    public int getUserId() {

        return userId;

    }

    public void setUserId(int userId) {

        this.userId = userId;

    }

    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }


    public String getPassword() {

        return password;

    }


    public void setPassword(String password) {

        this.password = password;

    }


    public String getRole() {

        return role;

    }


    public void setRole(String role) {

        this.role = role;

    }


    @Override

    public String toString() {

        return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role

                + "]";

    }


}

 
