package com.example.gcy.service;

import com.example.gcy.pojo.Grade;
import com.example.gcy.pojo.User;
import com.example.gcy.repository.GradeRepository;
import com.example.gcy.repository.UserRepository;
import com.example.gcy.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private UserService userService;

    @Override
    public Grade findByid(Long id) {
        return gradeRepository.findById(id).get();
    }

    @Override
    public List<Grade> findByUser(User user) {
        return gradeRepository.findByUser(user);
    }

    @Override
    public Grade save(Grade grade) {

        Long id = grade.getUser().getId();

        grade.setUser(userService.findById(id));

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(Long id, Grade grade) {
        Grade g = gradeRepository.findById(id).get();
        if (g!=null) {
            UpdateUtil.copyNullProperties(grade, g);
            return gradeRepository.save(g);
        }
        return null;
    }

    @Override
    public boolean deleteGrade(Long id) {

        Grade g = gradeRepository.findById(id).get();
        if (g!=null) {

            g.setUser(null);
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGrade(List<Grade> list) {

        gradeRepository.deleteAll(list);

        return true;
    }

    @Override
    public Page<Grade> listGrade(Pageable pageable) {
        return gradeRepository.findAll(pageable);
    }
}
