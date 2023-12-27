package ru.nsu.recipes.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        if (exception instanceof InternalAuthenticationServiceException) {
            request.getSession().setAttribute("loginError", "An internal error occurred. Please try again.");
        } else {
            request.getSession().setAttribute("loginError", "Invalid username or password.");
        }
        response.sendRedirect("/login?error");
    }
}

