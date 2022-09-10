package com.demo.humorsource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dept")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
	@Id
	// 主鍵由數據庫自動維護(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dept_id")
	Integer id;
	
	@Column(name = "dept_name")
	String name;

	@Column(name = "mg_empno")
	Integer MgEmpNo;
	
	@OneToMany(mappedBy = "deptName")
	@JsonIgnore
	List<Employee> employeeList;
	
	@OneToMany(mappedBy = "deptName")
	@JsonIgnore
	List<Position> positionList;
}
