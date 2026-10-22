package com.mora.ordermanager.Services;

import com.mora.ordermanager.Entities.Customer;
import java.util.List;

public interface CustomerService {
    Customer crearCliente(Customer cliente);
    List<Customer> obtenerTodos();
    Customer obtenerPorId(Long id);
}