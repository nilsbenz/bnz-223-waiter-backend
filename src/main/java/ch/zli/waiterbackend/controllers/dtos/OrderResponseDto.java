package ch.zli.waiterbackend.controllers.dtos;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.entities.OrderItem;
import ch.zli.waiterbackend.entities.Table;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseDto {

    private Long id;
    private Table table;
    private AppUser user;
    private List<OrderItem> orderItems;
    private BigDecimal price;

    public void calculatePrice() {
        price = orderItems
                .stream()
                .map(orderItem -> orderItem.getItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getAmount())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
