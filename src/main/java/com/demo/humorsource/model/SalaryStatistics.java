package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "salarystatistics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer infoId;
    Integer empNo;
    Integer roughSalary;
    Integer leaveCount;
    Integer workInsurance;
    Integer healthInsurance;
    Integer salaryResult;
}
