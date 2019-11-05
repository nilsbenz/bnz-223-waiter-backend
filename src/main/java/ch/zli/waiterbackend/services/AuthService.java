package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.authentication.IAuthenticationFacade;
import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.repositories.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private IAuthenticationFacade authenticationFacade;

    AuthService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, IAuthenticationFacade authenticationFacade) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    public void register(AppUser user) {
        for (AppUser u : appUserRepository.findAll()) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new IllegalArgumentException("Dieser Benutzername ist bereits vergeben.");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (appUserRepository.getNumberOfUsers() == 0) {
            user.setAdmin(true);
        }
        appUserRepository.save(user);
    }

    public AppUser getCurrentUser() {
        String username = authenticationFacade.getAuthentication().getName();
        AppUser user = appUserRepository.findByUsername(username);
        user.setPassword(null);
        return user;
    }

    public boolean isCurrentUserAdmin() {
        return getCurrentUser().isAdmin();
    }

    public boolean isCurrentUserWaiter() {
        return getCurrentUser().isWaiter();
    }
}
