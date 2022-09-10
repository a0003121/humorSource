package com.demo.humorsource.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String password;
    Byte enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "authority", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "id"))
    List<Authority> featureList;
}
