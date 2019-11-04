package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.repositories.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AppUserRepository appUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    AuthService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(AppUser user) {
        for (AppUser u : appUserRepository.findAll()) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                throw new IllegalArgumentException("Dieser Benutzername ist bereits vergeben.");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(appUserRepository.getNumberOfUsers() == 0) {
            user.setAdmin(true);
        }
        appUserRepository.save(user);
    }
}
