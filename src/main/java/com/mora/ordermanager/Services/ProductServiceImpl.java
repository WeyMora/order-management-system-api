package com.mora.ordermanager.Services;

import com.mora.ordermanager.Entities.Product;
import com.mora.ordermanager.Exceptions.ResourceNotFoundException;
import com.mora.ordermanager.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product crearProducto(Product producto) {
        return productRepository.save(producto);
    }

    @Override
    public List<Product> obtenerTodos() {
        return productRepository.findAll();
    }

    @Override
    public Product obtenerPorId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }
}