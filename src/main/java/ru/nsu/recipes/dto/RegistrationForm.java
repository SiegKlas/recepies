package ru.nsu.recipes.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.nsu.recipes.entity.RecipesUser;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public RecipesUser toUser(PasswordEncoder passwordEncoder) {
        RecipesUser user = new RecipesUser(username);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}