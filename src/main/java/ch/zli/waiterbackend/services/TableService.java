package ch.zli.waiterbackend.services;

import ch.zli.waiterbackend.entities.Table;
import ch.zli.waiterbackend.repositories.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    private TableRepository tableRepository;

    private AuthService authService;

    TableService(TableRepository tableRepository, AuthService authService) {
        this.tableRepository = tableRepository;
        this.authService = authService;
    }

    public List<Table> listTables() {
        return tableRepository.findAll();
    }

    public Table createTable(Table table) {
        if (authService.isCurrentUserAdmin()) {
            return tableRepository.save(table);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }

    public void deleteTable(Long id) {
        if (authService.isCurrentUserAdmin()) {
            tableRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Diese Funktion können nur Administratoren ausführen.");
        }
    }
}
