package com.joshuaCuenca.microservicio.controller;
import com.joshuaCuenca.microservicio.dto.ProductoRequestDTO;
import com.joshuaCuenca.microservicio.dto.ProductoMapper;
import com.joshuaCuenca.microservicio.dto.ProductoResponseDTO;
import com.joshuaCuenca.microservicio.model.Producto;
import com.joshuaCuenca.microservicio.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Slf4j
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {

    private final ProductoService productoService;
    @Operation(summary = "Obtener todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        log.info("GET /api/productos - obteniendo todos los productos");

        List<ProductoResponseDTO> productos = productoService.obtenerTodos()
                .stream()
                .map(ProductoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(productos);
    }
    // GET por ID → 200 si existe, 404 si no (el service ya lanza la excepción)
    @Operation(summary = "Obtener producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        log.info("GET /api/productos - obteniendo por id", id);
        Producto producto = productoService.obtenerProducto_ID(id);// si no existe, explota aquí
        ProductoResponseDTO response = ProductoMapper.toResponse(producto);// esto solo corre si existe
        return ResponseEntity.ok(response);
    }
    // POST crear → devuelve 201 CREATED con el producto creado
    @Operation(summary = "Crear nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearNuevo(@Valid @RequestBody ProductoRequestDTO requestDTO) {
        log.info("POST /api/productos - Crear nuevo producto");
        Producto producto = productoService.crearNuevo(ProductoMapper.toEntity(requestDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoMapper.toResponse(producto));
    }
    // PUT actualizar → devuelve 200 con el producto actualizado
    @Operation(summary = "Actualizar producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO requestDTO) {
        log.info("PUT /api/productos - Actualizar producto", id);
        Producto producto = productoService.actualizarProducto(id, ProductoMapper.toEntity(requestDTO));
        return ResponseEntity.ok(ProductoMapper.toResponse(producto));
    }

    // DELETE → devuelve 204 NO CONTENT (sin body)
    @Operation(summary = "Eliminar producto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.info("DELETE /api/productos - Eliminar producto", id);
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();

    }

}
