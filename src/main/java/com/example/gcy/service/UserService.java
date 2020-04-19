package com.example.gcy.service;


import com.example.gcy.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findById(Long id);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);

    Page<User> listUser(Pageable pageable);

    List<User> listUser();


}
