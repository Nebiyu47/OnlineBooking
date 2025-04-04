package com.example.jwtdemo.config;

import com.example.jwtdemo.model.Role;
import com.example.jwtdemo.model.RoleEnum;
import com.example.jwtdemo.repository.RoleRepository;
import jakarta.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createRoleIfMissing(RoleEnum.ROLE_ADMIN);
        createRoleIfMissing(RoleEnum.ROLE_USER);
        createRoleIfMissing(RoleEnum.ROLE_MODERATOR);
    }

    private void createRoleIfMissing(RoleEnum roleEnum) {
        if (!roleRepository.existsByName(roleEnum)) {
            Role role = new Role();
            role.setName(roleEnum);
            roleRepository.save(role);
        }
    }
}