package com.audora.weatherapp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_styles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name="style")
    private Set<String> preferredStyles;
}
