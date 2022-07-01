package com.skujevska.baiba.service;

import com.skujevska.baiba.frontmodel.UserFront;
import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    UserRepository repository;

    @Mock
    RoleService roleService;

    @Mock
    PasswordEncoder encoder;

    @InjectMocks
    UserService service = new UserService();

    UserFront userFront;

    User user;

    Optional<Role> role;

    @BeforeAll
    void setup() {
        this.role = createRole();
        this.userFront = createUserFront();
        this.user = createUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void userExists() {
        when(repository.existsByUsername(any(String.class))).thenReturn(true);

        boolean result = this.service.existsByUsername("oto");
        assertThat(result).isTrue();
    }

    @Test
    void userNotExists() {
        when(repository.existsByUsername(any(String.class))).thenReturn(false);

        boolean result = this.service.existsByUsername("oto");
        assertThat(result).isFalse();
    }

    @Test
    void successSavingUser() {
        when(repository.save(any(User.class))).thenReturn(user);
        when(roleService.findByName(RoleE.ROLE_USER)).thenReturn(role);
        when(encoder.encode(any(String.class))).thenReturn("encoded password");

        User savedUser = this.service.save(userFront);
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void savingUserThrowsError() {
        when(roleService.findByName(RoleE.ROLE_USER)).thenReturn(Optional.empty());
        when(encoder.encode(any(String.class))).thenReturn("encoded password");

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            this.service.save(userFront);
        });

        assertTrue(exception.getMessage().contains("Role is not found"));
    }

    UserFront createUserFront() {
        UserFront newUser = new UserFront("mia", "pssw");
        return newUser;
    }

    User createUser() {
        User newUser = new User("mia", "pssw");
        return newUser;
    }

    Optional<Role> createRole() {
        Role role = new Role();
        role.setId(1l);
        role.setName(RoleE.ROLE_USER);
        return Optional.of(role);
    }
}
