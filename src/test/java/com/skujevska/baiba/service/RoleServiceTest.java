package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import com.skujevska.baiba.repository.RoleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoleServiceTest {

    @Mock
    RoleRepository repository;

    @InjectMocks
    RoleService service = new RoleService();

    Optional<Role> role;

    @BeforeAll
    void setup() {
        this.role = createRole();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void roleExists() {
        when(repository.findByName(any(RoleE.class))).thenReturn(role);

        Optional<Role> result = this.service.findByName(RoleE.ROLE_USER);
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(RoleE.ROLE_USER);
    }

    @Test
    void roleNotExists() {
        when(repository.findByName(any(RoleE.class))).thenReturn(Optional.empty());

        Optional<Role> result = this.service.findByName(RoleE.ROLE_USER);
        assertThat(result).isEmpty();
    }

    Optional<Role> createRole() {
        Role role = new Role();
        role.setId(1l);
        role.setName(RoleE.ROLE_USER);
        return Optional.of(role);
    }
}