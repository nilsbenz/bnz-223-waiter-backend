package ch.zli.waiterbackend.repositories;

import ch.zli.waiterbackend.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    @Query("SELECT COUNT(u.id) FROM AppUser u")
    int getNumberOfUsers();
}
