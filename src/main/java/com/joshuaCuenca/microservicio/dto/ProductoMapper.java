package com.joshuaCuenca.microservicio.dto;

import com.joshuaCuenca.microservicio.model.Producto;

public class ProductoMapper {

    // Pista 1: recibe un RequestDTO y devuelve una entidad Producto
    // solo asigna nombre, precio y stock (el ID lo pone la BD)
    public static Producto toEntity(ProductoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();

        // Asignamos sólo los campos permitidos por la pista
        producto.setName(dto.getName());
        producto.setPrice(dto.getPrice());
        producto.setStock(dto.getStock());

        // No tocar el ID: lo genera la base de datos al persistir
        return producto;
    }

    // Pista 2: recibe una entidad Producto y devuelve un ResponseDTO
    // asigna todos los campos incluyendo el ID
    public static ProductoResponseDTO toResponse(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoResponseDTO dto = new ProductoResponseDTO();

        // Asignamos todos los campos relevantes (incluido ID)
        dto.setId(producto.getId());
        dto.setName(producto.getName());
        dto.setPrice(producto.getPrice());
        dto.setStock(producto.getStock());


        return dto;
    }
}

