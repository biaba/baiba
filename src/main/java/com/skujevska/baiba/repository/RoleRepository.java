package com.skujevska.baiba.repository;

import com.skujevska.baiba.model.Role;
import com.skujevska.baiba.model.RoleE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleE name);
}
