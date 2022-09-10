package com.demo.humorsource.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="employee")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	Integer id;
	
	String name;
	Integer position;
	Integer dept;
	Integer empNo;
	Date hireDate;
	Date leaveDate;
	Integer salary;
	Integer healthInsurance;
	Integer workInsurance;
	Integer compensation;
	Integer status;
	String email;
	String note;
	
	@ManyToOne
	@JoinColumn(name="position", insertable = false, updatable = false)
	Position positionName;
	
	@ManyToOne
	@JoinColumn(name="dept", insertable = false, updatable = false)
	Dept deptName;
}
