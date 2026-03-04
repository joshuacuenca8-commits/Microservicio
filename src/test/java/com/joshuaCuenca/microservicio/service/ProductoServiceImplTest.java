package com.joshuaCuenca.microservicio.service;

import com.joshuaCuenca.microservicio.exception.ProductoNotFoundException;
import com.joshuaCuenca.microservicio.model.Producto;
import com.joshuaCuenca.microservicio.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setName("Laptop Dell");
        producto.setPrice(999.99);
        producto.setStock(10);
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornarProducto() {
        // ARRANGE — preparas los datos
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // ACT — ejecutas lo que quieres probar
        Producto resultado = productoService.obtenerProducto_ID(1L);

        // ASSERT — verificas el resultado
        assertNotNull(resultado);
        assertEquals("Laptop Dell", resultado.getName());
        assertEquals(999.99, resultado.getPrice());
        verify(productoRepository, times(1)).findById(1L);
    }
    // Test 1 — obtenerPorId cuando NO existe debe lanzar excepción
    @Test
    void obtenerPorId_cuandoNoExiste_debeLanzarExcepcion() {
        // ARRANGE
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(ProductoNotFoundException.class, () -> {
            productoService.obtenerProducto_ID(99L);
        });
    }

    @Test
    void obtenerTodos_debeRetornarListaDeProductos() {
        // ARRANGE
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        // ACT
        List<Producto> resultado = productoService.obtenerTodos();

        // ASSERT
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void crear_debeGuardarYRetornarProducto() {
        // ARRANGE
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);

        // ACT
        Producto resultado = productoService.crearNuevo(producto);

        // ASSERT
        assertNotNull(resultado);
        assertEquals("Laptop Dell", resultado.getName());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void eliminar_cuandoNoExiste_debeLanzarExcepcion() {
        // ARRANGE
        when(productoRepository.existsById(99L)).thenReturn(false);

        // ACT & ASSERT
        assertThrows(ProductoNotFoundException.class, () -> {
            productoService.eliminar(99L);
        });
        verify(productoRepository, never()).deleteById(anyLong());
    }
}