package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "salaryinfo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer infoId;
    String calcDate;
    Timestamp startDate;
    Timestamp endDate;
    String name;
}
