package com.example.gcy.repository;

import com.example.gcy.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUsernameAndPassword(String username, String password);

    Teacher findByUsername(String username);
}
