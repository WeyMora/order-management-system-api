package com.mora.ordermanager.Services;

import com.mora.ordermanager.DTO.OrderItemDTO;
import com.mora.ordermanager.DTO.OrderRequestDTO;
import com.mora.ordermanager.Entities.Customer;
import com.mora.ordermanager.Entities.Order;
import com.mora.ordermanager.Entities.OrderDetail;
import com.mora.ordermanager.Entities.Product;
import com.mora.ordermanager.Exceptions.InsufficientStockException;
import com.mora.ordermanager.Exceptions.ResourceNotFoundException;
import com.mora.ordermanager.Repository.CustomerRepository;
import com.mora.ordermanager.Repository.OrderRepository;
import com.mora.ordermanager.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional // Si falla el stock de algún producto, se cancela todo automáticamente
    public Order crearOrden(OrderRequestDTO request) {
        Customer cliente = customerRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + request.getClienteId()));

        Order orden = new Order();
        orden.setCustomer(cliente);

        BigDecimal acumuladoTotal = BigDecimal.ZERO;

        for (OrderItemDTO item : request.getItems()) {
            Product producto = productRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + item.getProductoId()));

            // Validación de stock disponible
            if (producto.getStock_disponible() < item.getCantidad()) {
                throw new InsufficientStockException("Stock insuficiente para el producto '" + producto.getNombre() +
                        "'. Stock actual: " + producto.getStock_disponible() + ", solicitado: " + item.getCantidad());
            }

            // Descontar inventario
            producto.setStock_disponible(producto.getStock_disponible() - item.getCantidad());
            productRepository.save(producto);

            // Crear el detalle de la orden
            OrderDetail detalle = new OrderDetail(producto, item.getCantidad(), producto.getPrecio());
            orden.agregarDetalle(detalle);

            acumuladoTotal = acumuladoTotal.add(detalle.getSubtotal());
        }

        orden.setTotal(acumuladoTotal);
        orden.setEstado("COMPLETADO");

        return orderRepository.save(orden);
    }

    @Override
    public List<Order> obtenerTodas() {
        return orderRepository.findAll();
    }
    // Para ag
    @Override
    public Order obtenerPorId(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + id));
    }
}