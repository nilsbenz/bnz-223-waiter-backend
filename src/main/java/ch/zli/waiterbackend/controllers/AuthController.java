package ch.zli.waiterbackend.controllers;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the REST API for authenticating users.
 * It is used for creating, updating and deleting users.
 * All requests must start with /api/auth, for the /register no token is required.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser user) {
        authService.register(user);
    }
}
