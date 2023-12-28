package ru.nsu.recipes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.RecipesUserRepository;

@Component
public class UserInitListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipesUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitListener(RecipesUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (userRepository.findByUsername("admin") == null) {
            RecipesUser admin = new RecipesUser("admin");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setRole(RecipesUser.Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
