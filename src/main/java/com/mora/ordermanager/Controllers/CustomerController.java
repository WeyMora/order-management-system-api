package com.mora.ordermanager.Controllers;

import com.mora.ordermanager.Entities.Customer;
import com.mora.ordermanager.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> crearCliente(@Valid @RequestBody Customer cliente) {
        Customer nuevoCliente = customerService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> obtenerTodos() {
        return ResponseEntity.ok(customerService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.obtenerPorId(id));
    }
}