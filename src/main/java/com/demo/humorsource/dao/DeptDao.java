package com.demo.humorsource.dao;

import com.demo.humorsource.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<Dept, Integer> {
	// 使用自動化命名規則進行條件搜尋
//    Dept findByName(String name);


}
