package ch.zli.waiterbackend.controllers;

import ch.zli.waiterbackend.entities.Table;
import ch.zli.waiterbackend.services.TableService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This is the REST API for managing the tables.
 * An admin can create and delete the tables, and everyone (with a token) can list all tables.
 * All requests start with /api/tables.
 */
@RestController
@RequestMapping("/api/tables")
public class TableController {

    private TableService tableService;

    TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public List<Table> listTables() {
        return tableService.listTables();
    }

    @PostMapping
    public Table createTable(@RequestBody Table table) {
        return tableService.createTable(table);
    }

    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
    }
}
