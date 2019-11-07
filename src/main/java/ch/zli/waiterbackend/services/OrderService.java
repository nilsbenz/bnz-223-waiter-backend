package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.controllers.dtos.OrderRequestDto;
import ch.zli.waiterbackend.controllers.dtos.OrderResponseDto;
import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.entities.Order;
import ch.zli.waiterbackend.entities.OrderItem;
import ch.zli.waiterbackend.repositories.OrderItemRepository;
import ch.zli.waiterbackend.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private OrderItemRepository orderItemRepository;

    private AuthService authService;

    OrderService(OrderRepository orderRepository, AuthService authService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.authService = authService;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderResponseDto> listOrders() {
        if (authService.isCurrentUserAdmin()) {
            List<OrderResponseDto> response = getAllOrderResponseDtos();
            Collections.reverse(response);
            return response;
        } else if (authService.isCurrentUserWaiter()) {
            List<OrderResponseDto> response = getOrderResponseDtosOfCurrentUser();
            Collections.reverse(response);
            return response;
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren und das Servicepersonal ausführen.");
        }
    }

    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        if (authService.isCurrentUserAdmin() || authService.isCurrentUserWaiter()) {
            Order order = saveOrderFromDto(orderRequestDto);
            List<OrderItem> orderItems = saveOrderItemsFromDto(orderRequestDto, order);
            return getOrderResponseDto(order, orderItems);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren und das Servicepersonal ausführen.");
        }
    }

    public void deleteOrder(Long id) {
        if (authService.isCurrentUserAdmin() || authService.isCurrentUserWaiter()) {
            orderRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren und das Servicepersonal ausführen.");
        }
    }

    private List<OrderResponseDto> getAllOrderResponseDtos() {
        return orderRepository.findAll().stream()
                .map(order -> {
                    OrderResponseDto orderResponseDto = new OrderResponseDto();
                    orderResponseDto.setId(order.getId());
                    orderResponseDto.setTable(order.getTable());
                    orderResponseDto.setUser(order.getUser());
                    orderResponseDto.setOrderItems(orderItemRepository.findAllOfOrder(order));
                    orderResponseDto.calculatePrice();
                    return orderResponseDto;
                })
                .collect(Collectors.toList());
    }

    private List<OrderResponseDto> getOrderResponseDtosOfCurrentUser() {
        AppUser user = authService.getCurrentUser();
        return orderRepository.findAll().stream()
                .filter(order -> order.getUser() == user)
                .map(order -> {
                    OrderResponseDto orderResponseDto = new OrderResponseDto();
                    orderResponseDto.setId(order.getId());
                    orderResponseDto.setTable(order.getTable());
                    orderResponseDto.setUser(order.getUser());
                    orderResponseDto.setOrderItems(orderItemRepository.findAllOfOrder(order));
                    orderResponseDto.calculatePrice();
                    return orderResponseDto;
                })
                .collect(Collectors.toList());
    }

    private Order saveOrderFromDto(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setTable(orderRequestDto.getTable());
        order.setUser(authService.getCurrentUser());
        return orderRepository.save(order);
    }

    private List<OrderItem> saveOrderItemsFromDto(OrderRequestDto orderRequestDto, Order order) {
        orderRequestDto.getOrderItems()
                .forEach(item -> item.setOrder(order));
        return orderItemRepository.saveAll(orderRequestDto.getOrderItems());
    }

    private OrderResponseDto getOrderResponseDto(Order order, List<OrderItem> orderItems) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTable(order.getTable());
        orderResponseDto.setUser(order.getUser());
        orderResponseDto.setOrderItems(orderItems);
        orderResponseDto.calculatePrice();
        return orderResponseDto;
    }
}
