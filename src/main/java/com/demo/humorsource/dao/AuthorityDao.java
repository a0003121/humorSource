package com.demo.humorsource.dao;

import com.demo.humorsource.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
}
