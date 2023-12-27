package ru.nsu.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.nsu.recipes.entity.RecipesUser;
import ru.nsu.recipes.repository.RecipesUserRepository;

@Service
public class RecipesUserService {
    private final RecipesUserRepository userRepository;

    @Autowired
    public RecipesUserService(RecipesUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processOAuthPostLogin(OAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        RecipesUser user = userRepository.findByUsername(name);

        if (user == null) {
            user = new RecipesUser(name);
            userRepository.save(user);
        }
    }
}

