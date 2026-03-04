package com.joshuaCuenca.microservicio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshuaCuenca.microservicio.dto.ProductoRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Helper para no repetir la creación del DTO
    private ProductoRequestDTO crearProductoDTO() {
        ProductoRequestDTO dto = new ProductoRequestDTO();
        dto.setName("Laptop Dell");
        dto.setPrice(999.99);
        dto.setStock(10);
        return dto;
    }

    @Test
    void crear_debeRetornar201() throws Exception {
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoDTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Laptop Dell"))
                .andExpect(jsonPath("$.precio").value(999.99))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void obtenerTodos_debeRetornar200() throws Exception {
        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void obtenerPorId_cuandoExiste_debeRetornar200() throws Exception {
        // Primero creamos un producto
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoDTO())))
                .andExpect(status().isCreated());

        // Luego lo buscamos
        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Laptop Dell"));
    }

    @Test
    void obtenerPorId_cuandoNoExiste_debeRetornar404() throws Exception {
        mockMvc.perform(get("/api/productos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void crear_conDatosInvalidos_debeRetornar400() throws Exception {
        ProductoRequestDTO dtoInvalido = new ProductoRequestDTO();
        dtoInvalido.setName(""); // nombre vacío — debe fallar validación
        dtoInvalido.setPrice(-1.0); // precio negativo — debe fallar
        dtoInvalido.setStock(-5); // stock negativo — debe fallar

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoInvalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void eliminar_cuandoExiste_debeRetornar204() throws Exception {
        // Crear primero
        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crearProductoDTO())))
                .andExpect(status().isCreated());

        // Eliminar
        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminar_cuandoNoExiste_debeRetornar404() throws Exception {
        mockMvc.perform(delete("/api/productos/99"))
                .andExpect(status().isNotFound());
    }
}
