package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "salary_emp_infodetail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryEmpInfodetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer empNo;
    Integer infoId;
    String leaveType;
    Float leaveHours;
    Float total;
}
