package com.demo.humorsource.dao;

import com.demo.humorsource.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{
List<Employee> findAllByOrderByEmpNoAsc();
	List<Employee> findByStatus(Integer status);
	List<Employee> findByEmpNo(Integer empNo);

	@Query("select e from Employee e  where e.hireDate <= ?1 and (e.leaveDate is null or e.leaveDate>=?2)")
	List<Employee> findSalaryMemberByHireDateAndLeaveDate(Date hireDate, Date leaveDate);




}
