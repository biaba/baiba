package com.skujevska.baiba.service;

import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public User save(User user) {
       return userRepository.save(user);
    }
}
