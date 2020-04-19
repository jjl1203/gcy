package com.example.gcy.service;

import com.example.gcy.pojo.Grade;
import com.example.gcy.pojo.User;
import com.example.gcy.repository.UserRepository;
import com.example.gcy.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GradeService gradeService;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {

        User u = userRepository.findById(id).get();
        if (u!=null) {
            UpdateUtil.copyNullProperties(user, u);
            return userRepository.save(u);
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        if (user==null) {
            return false;
        }
        List<Grade> grades = user.getGrades();
        for (Grade grade : grades) {
            grade.setUser(null);
        }
        gradeService.deleteGrade(grades);
        userRepository.deleteById(id);
        return true;

    }

    @Override
    public Page<User> listUser(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }
}
