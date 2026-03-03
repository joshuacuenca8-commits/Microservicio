package com.joshuaCuenca.microservicio.service;

import com.joshuaCuenca.microservicio.model.Producto;
import java.util.List;


public interface ProductoService {

    List<Producto> obtenerTodos();
    Producto obtenerProducto_ID(Long id);
    Producto crearNuevo(Producto producto);
    Producto actualizarProducto(Long id, Producto producto);
    void eliminar(Long id);
}
