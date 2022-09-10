package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="clock_raw")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClockRaw {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Timestamp time;
	Integer empNo;
}
