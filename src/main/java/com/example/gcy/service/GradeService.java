package com.example.gcy.service;

import com.example.gcy.pojo.Grade;
import com.example.gcy.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GradeService {

    Grade save(Grade grade);

    Grade findByid(Long id);

    List<Grade> findByUser(User user);

    Grade updateGrade(Long id, Grade grade);

    boolean deleteGrade(Long id);

    boolean deleteGrade(List<Grade> list);

    Page<Grade> listGrade(Pageable pageable);

}
