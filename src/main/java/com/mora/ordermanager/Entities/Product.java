package com.mora.ordermanager.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String nombre;
    @NotBlank (message = "La descripción del producto no puede estar vacía")
    private String descripcion;
    @NotBlank (message = "La categoría del producto no puede estar vacía")
    private String categoria;
    @NotNull (message = "El precio del producto no puede estar vacío")
    @Min (value = 0, message = "El precio del producto no puede ser negativo")
    private Double precio;
    @Min (value = 0, message = "El stock disponible del producto no puede ser negativo")
    private Integer stock_disponible;

    // Constructor

    public Product() {}

    public Product(String nombre, String descripcion, String categoria, Double precio, Integer stock_disponible) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.stock_disponible = stock_disponible;
    }

    // Getters And Setters

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getNombre() { return nombre;}
    public void setNombre(String nombre) { this.nombre = nombre;}

    public String getDescripcion() { return descripcion;}
    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

    public String getCategoria() { return categoria;}
    public void setCategoria(String categoria) { this.categoria = categoria;}

    public Double getPrecio() { return precio;}
    public void setPrecio(Double precio) { this.precio = precio;}

    public Integer getStock_disponible() { return stock_disponible;}
    public void setStock_disponible(Integer stock_disponible) { this.stock_disponible = stock_disponible;}




}
