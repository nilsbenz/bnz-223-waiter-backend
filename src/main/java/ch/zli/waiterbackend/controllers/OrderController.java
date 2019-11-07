package ch.zli.waiterbackend.controllers;

import ch.zli.waiterbackend.controllers.dtos.OrderRequestDto;
import ch.zli.waiterbackend.controllers.dtos.OrderResponseDto;
import ch.zli.waiterbackend.services.OrderService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the REST API of the order entity.
 * Only waiters and admins can conduct these operations.
 * All requests start with /api/orders.
 * It also includes the OrderRequestDto and the OrderResponseDto.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDto> listOrders() {
        return orderService.listOrders();
    }

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
