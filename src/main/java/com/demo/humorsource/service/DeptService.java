package com.demo.humorsource.service;

import com.demo.humorsource.dao.DeptDao;
import com.demo.humorsource.model.Dept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptService {
    private final DeptDao deptDAO;

    public List<Dept> getAllDepts() {
        return deptDAO.findAll();
    }

    public Dept findById(int id) {
        return deptDAO.findById(id).get();
    }

    public Dept createDept(String name) {
        Dept dept = new Dept();
        dept.setId(0);
        dept.setName(name);
        return deptDAO.save(dept);
    }

    public Dept updateDept(int id, String name, Integer empNo) {
        Dept dept = new Dept();
        dept.setId(id);
        dept.setName(name);
        dept.setMgEmpNo(empNo);
        return deptDAO.save(dept);
    }

    public void deleteDept(int id) {
        deptDAO.deleteById(id);
    }


}
