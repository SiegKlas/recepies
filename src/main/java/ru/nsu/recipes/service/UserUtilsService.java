package ru.nsu.recipes.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import ru.nsu.recipes.entity.RecipesUser;

import java.util.Map;

public class UserUtilsService {

    public static String getUsernameFromAuthentication(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
            return attributes.get("name").toString();
        } else {
            return authentication.getName();
        }
    }

    public static RecipesUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        if (principal instanceof RecipesUser) {
            return (RecipesUser) principal;
        }

        return null;
    }

}
