package com.mora.ordermanager.Services;

import com.mora.ordermanager.Entities.Product;
import java.util.List;

public interface ProductService {
    Product crearProducto(Product producto);
    List<Product> obtenerTodos();
    Product obtenerPorId(Long id);
}