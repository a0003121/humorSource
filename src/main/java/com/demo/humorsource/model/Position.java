package com.demo.humorsource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    // 主鍵由數據庫自動維護(AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    Integer id;

    @Column(name = "position_name")
    String name;

    Integer deptId;

    @OneToMany(mappedBy = "positionName")
    @JsonIgnore
    List<Employee> employeeList;

    @ManyToOne
    @JoinColumn(name = "deptId", insertable = false, updatable = false)
    Dept deptName;
}
