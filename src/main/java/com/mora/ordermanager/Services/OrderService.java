package com.mora.ordermanager.Services;

import com.mora.ordermanager.DTO.OrderRequestDTO;
import com.mora.ordermanager.Entities.Order;
import java.util.List;

public interface OrderService {
    Order crearOrden(OrderRequestDTO request);
    List<Order> obtenerTodas();
    Order obtenerPorId(Long id);
}