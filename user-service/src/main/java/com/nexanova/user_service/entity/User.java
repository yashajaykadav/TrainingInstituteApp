package com.nexanova.user_service.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Added CsvBindByName to ensure CSV columns map correctly
    @CsvBindByName(column = "username")
    @Column(nullable = false, unique = true)
    private String username;

    @CsvBindByName(column = "password")
    @Column(nullable = false)
    private String password;

    @CsvBindByName(column = "role")
    @Column(nullable = false)
    private String role;

    @CsvBindByName(column = "fullName")
    private String fullName;
}