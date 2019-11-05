package ch.zli.waiterbackend.repositories;

import ch.zli.waiterbackend.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
