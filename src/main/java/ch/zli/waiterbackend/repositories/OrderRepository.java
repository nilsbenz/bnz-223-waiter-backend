package ch.zli.waiterbackend.repositories;

import ch.zli.waiterbackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
