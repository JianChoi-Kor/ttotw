package com.project.ttotw.spring;

import com.project.ttotw.entity.Admin;
import com.project.ttotw.enums.Role;
import com.project.ttotw.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private boolean alreadySetup = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //check alreadySetup
        if (alreadySetup) {
            return;
        }
        //create initial admin
        createAdminIfNotFound("admin", "test1234!", Role.ROLE_ADMIN);
        alreadySetup = true;
    }

    Admin createAdminIfNotFound(final String email, final String password, final Role role) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin == null) {
            admin = Admin.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(role)
                    .build();
            //save
            adminRepository.save(admin);
        }
        return admin;
    }
}
