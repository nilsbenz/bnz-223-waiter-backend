package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.entities.Item;
import ch.zli.waiterbackend.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    private AuthService authService;

    ItemService(ItemRepository itemRepository, AuthService authService) {
        this.itemRepository = itemRepository;
        this.authService = authService;
    }

    public List<Item> listItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        if (authService.isCurrentUserAdmin()) {
            return itemRepository.save(item);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void deleteItem(Long id) {
        if (authService.isCurrentUserAdmin()) {
            itemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }
}
