package com.example.gcy.service;

import com.example.gcy.pojo.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    Teacher save(Teacher teacher);

    Teacher findById(Long id);

    Teacher findByUsernameAndPassword(String username, String password);

    Teacher findByUsername(String username);

    Teacher updateTeacher(Long id, Teacher teacher);

    boolean deleteTeacher(Long id);

    Page<Teacher> listTeacher(Pageable pageable);
}
