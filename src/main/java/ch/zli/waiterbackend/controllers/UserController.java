package ch.zli.waiterbackend.controllers;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.services.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the REST API for handling users properties.
 * All requests must start with /api/users, and for all of them a token is required.
 * Additionally, the conducting user must be administrator.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/myself")
    public AppUser myself() {
        return userService.myself();
    }

    @PostMapping("/waiters/{id}")
    public void addWaiter(@PathVariable Long id) {
        userService.addWaiter(id);
    }

    @DeleteMapping("/waiters/{id}")
    public void deleteWaiter(@PathVariable Long id) {
        userService.deleteWaiter(id);
    }

    @PostMapping("/admins/{id}")
    public void addAdmin(@PathVariable Long id) {
        userService.addAdmin(id);
    }

    @DeleteMapping("/admins/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        userService.deleteAdmin(id);
    }
}
