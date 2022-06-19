package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
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

    private User user;

    private Optional<Role> role;

    @BeforeAll
    public void setup() {
        this.role = createRole();
        this.user = createUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void userExists() {
        when(repository.existsByUsername(any(String.class))).thenReturn(true);

        boolean result = this.service.existsByUsername("oto");
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void userNotExists() {
        when(repository.existsByUsername(any(String.class))).thenReturn(false);

        boolean result = this.service.existsByUsername("oto");
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void successSavingUser() {
        when(repository.save(any(User.class))).thenReturn(user);
        when(roleService.findByName(RoleE.ROLE_USER)).thenReturn(role);
        when(encoder.encode(any(String.class))).thenReturn("encoded password");

        User savedUser = this.service.save(user);
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    private User createUser() {
        User newUser = new User("mia", "pssw");
        return newUser;
    }

    private Optional<Role> createRole() {
        Role role = new Role();
        role.setId(1l);
        role.setName(RoleE.ROLE_USER);
        return Optional.of(role);
    }
}
