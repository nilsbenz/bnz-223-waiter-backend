package ch.zli.waiterbackend;

import ch.zli.waiterbackend.entities.AppUser;
import ch.zli.waiterbackend.entities.Item;
import ch.zli.waiterbackend.entities.Order;
import ch.zli.waiterbackend.entities.OrderItem;
import ch.zli.waiterbackend.entities.Table;
import ch.zli.waiterbackend.repositories.AppUserRepository;
import ch.zli.waiterbackend.repositories.ItemRepository;
import ch.zli.waiterbackend.repositories.OrderItemRepository;
import ch.zli.waiterbackend.repositories.OrderRepository;
import ch.zli.waiterbackend.repositories.TableRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class WaiterBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaiterBackendApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run(
            AppUserRepository appUserRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            TableRepository tableRepository,
            ItemRepository itemRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository) {
        return (String[] args) -> {
            AppUser admin = new AppUser("admin", bCryptPasswordEncoder.encode("c20ad4d76fe97759aa27a0c99bff6710"), false, true);
            AppUser waiter = new AppUser("waiter", bCryptPasswordEncoder.encode("c20ad4d76fe97759aa27a0c99bff6710"), true, false);
            admin = appUserRepository.save(admin);
            waiter = appUserRepository.save(waiter);
            Table table1 = new Table("A1");
            Table table2 = new Table("A2");
            Table table3 = new Table("B1");
            Table table4 = new Table("B2");
            table1 = tableRepository.save(table1);
            table2 = tableRepository.save(table2);
            table3 = tableRepository.save(table3);
            table4 = tableRepository.save(table4);
            Item cola = new Item("Coca Cola", BigDecimal.valueOf(4));
            Item fries = new Item("Pommes Frites", BigDecimal.valueOf(8.5));
            Item cake = new Item("Kuchen", BigDecimal.valueOf(2));
            cola = itemRepository.save(cola);
            fries = itemRepository.save(fries);
            cake = itemRepository.save(cake);
            Order order1 = new Order(waiter, table1);
            Order order2 = new Order(waiter, table2);
            order1 = orderRepository.save(order1);
            order2 = orderRepository.save(order2);
            OrderItem orderItem1 = new OrderItem(order1, cola, 3);
            OrderItem orderItem2 = new OrderItem(order1, fries, 2);
            OrderItem orderItem3 = new OrderItem(order1, cake, 1);
            OrderItem orderItem4 = new OrderItem(order2, cola, 6);
            OrderItem orderItem5 = new OrderItem(order2, cake, 2);
            orderItemRepository.saveAll(List.of(
                    orderItem1,
                    orderItem2,
                    orderItem3,
                    orderItem4,
                    orderItem5
            ));
        };
    }
}
