package com.mora.ordermanager.Services;

import com.mora.ordermanager.Entities.Customer;
import com.mora.ordermanager.Exceptions.ResourceNotFoundException;
import com.mora.ordermanager.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer crearCliente(Customer cliente) {
        return customerRepository.save(cliente);
    }

    @Override
    public List<Customer> obtenerTodos() {
        return customerRepository.findAll();
    }

    @Override
    public Customer obtenerPorId(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }
}