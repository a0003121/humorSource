package com.demo.humorsource.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class YearCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Date date;
    Integer type;
    String note;
}
