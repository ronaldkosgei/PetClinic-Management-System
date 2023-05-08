package com.PetClinic.Management.System.repository;

import com.PetClinic.Management.System.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
