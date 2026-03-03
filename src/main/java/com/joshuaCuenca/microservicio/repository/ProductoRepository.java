package com.joshuaCuenca.microservicio.repository;

import com.joshuaCuenca.microservicio.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    /**
     * Por que esta vacio?
     * Porque JpaRepository ya te da un montón de métodos listos para usar sin que escribas nada
     * repository.save(producto)        // INSERT o UPDATE
     * repository.findById(1L)          // SELECT WHERE id = 1
     * repository.findAll()             // SELECT *
     * repository.deleteById(1L)        // DELETE WHERE id = 1
     * repository.existsById(1L)        // ¿existe este ID?
     * repository.count()               // ¿cuántos registros hay?
     * ```
     *
     * Spring genera toda la implementation por ti en tiempo de ejection. Tú solo declaras la interfaz.
     *
     * ---
     *
     * ## ¿Qué significa `JpaRepository<Producto, Long>`?
     *
     * Son dos parametros que le dices a JPA:
     * ```
     * JpaRepository<Producto, Long>
     *                   ↑       ↑
     *             La entidad   El tipo del ID
     *
     *
     * */
}
