package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="clocktime")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClockTime {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Date clockDate;
	Integer empNo;
	Timestamp startTime;
	Timestamp endTime;
	Integer startId;
	Integer endId;
	Integer status;
}
