package com.mora.ordermanager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank("El nombre del producto no puede estar vacío")
    private String nombre;
    @NotBlank ("La descripción del producto no puede estar vacía")
    private String descripcion;
    @NotBlank ("La categoría del producto no puede estar vacía")
    private String categoria;
    @NotNull ("El precio del producto no puede estar vacío")
    @Min (value = 0, message = "El precio del producto no puede ser negativo")
    private Double precio;
    @Min (value = 0, message = "El stock disponible del producto no puede ser negativo")
    private Integer stock_disponible;


    // Getters And Setters

    




}
