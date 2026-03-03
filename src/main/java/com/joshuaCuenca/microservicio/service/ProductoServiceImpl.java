package com.joshuaCuenca.microservicio.service;

import com.joshuaCuenca.microservicio.model.Producto;
import com.joshuaCuenca.microservicio.repository.ProductoRepository;
import com.joshuaCuenca.microservicio.service.ProductoService;
import com.joshuaCuenca.microservicio.exception.ProductoNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProducto_ID(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(
                        "Producto con ID " + id + " no encontrado"
                ));
    }

    @Override
    public Producto crearNuevo(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado por Id: " + id));
        existente.setName(producto.getName());
        existente.setPrice(producto.getPrice());
        existente.setStock(producto.getStock());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        if(!productoRepository.existsById(id)){
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
