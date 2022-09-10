package com.demo.humorsource.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "employee_leave")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer employeeId;

    Integer leaveId;

    Date leaveDate;

    Timestamp startTime;

    Timestamp endTime;

    Float hours;
    Integer status;

    @ManyToOne
    @JoinColumn(name = "leaveId", insertable = false, updatable = false)
    Leave leave;

}
