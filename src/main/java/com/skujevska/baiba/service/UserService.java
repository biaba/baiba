package com.skujevska.baiba.service;

import com.skujevska.baiba.frontmodel.UserFront;
import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    public boolean existsByUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }

    public User save(UserFront userFront) {
        User newUser = new User(userFront.getUsername(),
                encoder.encode(userFront.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByName(RoleE.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role is not found"));
        roles.add(userRole);
        newUser.setRoles(roles);
        return userRepository.save(newUser);
    }
}
