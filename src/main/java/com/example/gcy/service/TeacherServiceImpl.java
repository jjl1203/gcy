package com.example.gcy.service;


import com.example.gcy.pojo.Teacher;
import com.example.gcy.repository.TeacherRepository;
import com.example.gcy.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher findByUsernameAndPassword(String username, String password) {
        return teacherRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {

        Teacher t = teacherRepository.findById(id).get();
        if (t!=null) {

            UpdateUtil.copyNullProperties(teacher, t);
            return teacherRepository.save(t);
        }

        return null;
    }

    @Override
    public boolean deleteTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id).get();
        if (teacher==null) {
            return false;
        }
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public Page<Teacher> listTeacher(Pageable pageable) {

        return teacherRepository.findAll(pageable);
    }
}
