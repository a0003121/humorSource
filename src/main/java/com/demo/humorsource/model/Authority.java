package com.demo.humorsource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="authority")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer userId;
	String feature;

	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="featureList")
    private List<User> users; 	
}
