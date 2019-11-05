package ch.zli.waiterbackend.repositories;

import ch.zli.waiterbackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
