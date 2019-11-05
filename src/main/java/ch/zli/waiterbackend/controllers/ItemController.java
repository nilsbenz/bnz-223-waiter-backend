package ch.zli.waiterbackend.controllers;

import ch.zli.waiterbackend.entities.Item;
import ch.zli.waiterbackend.services.ItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the REST API of the item entity.
 * Everyone with a token can list all the items, creating and deleting can only be done by admins.
 * All the requests start with /api/items.
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private ItemService itemService;

    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> listItems() {
        return itemService.listItems();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        this.itemService.deleteItem(id);
    }
}
