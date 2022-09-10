package com.demo.humorsource.dao;

import com.demo.humorsource.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);
}
