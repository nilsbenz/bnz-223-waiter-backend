package ch.zli.waiterbackend.repositories;

import ch.zli.waiterbackend.entities.Order;
import ch.zli.waiterbackend.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT i from OrderItem i WHERE i.order = :app_order")
    List<OrderItem> findAllOfOrder(@Param("app_order") Order order);
}
