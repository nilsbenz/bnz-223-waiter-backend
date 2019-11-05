package ch.zli.waiterbackend.controllers.dtos;

import ch.zli.waiterbackend.entities.OrderItem;
import ch.zli.waiterbackend.entities.Table;

import java.util.List;

public class OrderRequestDto {

    private Table table;
    private List<OrderItem> orderItems;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
