package com.mora.ordermanager.Controllers;

import com.mora.ordermanager.DTO.OrderRequestDTO;
import com.mora.ordermanager.Entities.Order;
import com.mora.ordermanager.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> crearOrden(@Valid @RequestBody OrderRequestDTO request) {
        Order nuevaOrden = orderService.crearOrden(request);
        return new ResponseEntity<>(nuevaOrden, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> obtenerTodas() {
        return ResponseEntity.ok(orderService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.obtenerPorId(id));
    }
}