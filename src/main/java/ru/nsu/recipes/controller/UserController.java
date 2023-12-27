package ru.nsu.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nsu.recipes.service.RecipesUserService;

@Controller
public class UserController {
    private final RecipesUserService userService;

    @Autowired
    public UserController(RecipesUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/oauth2Success")
    public String user(OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        userService.processOAuthPostLogin(oAuth2User);
        return "redirect:/";
    }
}
