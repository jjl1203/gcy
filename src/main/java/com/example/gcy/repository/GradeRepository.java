package com.example.gcy.repository;

import com.example.gcy.pojo.Grade;
import com.example.gcy.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByUser(User user);
}
