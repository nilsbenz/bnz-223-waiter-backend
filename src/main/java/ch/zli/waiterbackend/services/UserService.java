package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.repositories.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private AppUserRepository appUserRepository;

    private AuthService authService;

    UserService(AppUserRepository appUserRepository, AuthService authService) {
        this.appUserRepository = appUserRepository;
        this.authService = authService;
    }

    public List<AppUser> listUsers() {
        if (authService.isCurrentUserAdmin()) {
            return appUserRepository.findAll();
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public AppUser myself() {
        return authService.getCurrentUser();
    }

    public void deleteUser(Long id) {
        if (authService.isCurrentUserAdmin()) {
            appUserRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void addWaiter(Long id) {
        if (authService.isCurrentUserAdmin()) {
            AppUser user = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            user.setWaiter(true);
            appUserRepository.save(user);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void deleteWaiter(Long id) {
        if (authService.isCurrentUserAdmin()) {
            AppUser user = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            user.setWaiter(false);
            appUserRepository.save(user);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void addAdmin(Long id) {
        if (authService.isCurrentUserAdmin()) {
            AppUser user = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            user.setAdmin(true);
            appUserRepository.save(user);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void deleteAdmin(Long id) {
        if (authService.isCurrentUserAdmin()) {
            AppUser user = appUserRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            user.setAdmin(false);
            appUserRepository.save(user);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }
}
