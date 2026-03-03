package com.joshuaCuenca.microservicio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity //Esta clase es una tabla en la base de datos
@Table(name = "productos") // Genera la tabla, en caso de poner nombre usa el nombre de la clase
@Data //Genera getters, setters, toString y equals automaticamente
@NoArgsConstructor //Constructor vacío new Producto()
@AllArgsConstructor //Constructor con todos los campos new Producto

public class Producto {
    @Id //Campo para el ID unico de cada fila
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera el id de forma automaticamente
    private Long id;

    private String name;
    private Double price;
    private Integer stock;
}
