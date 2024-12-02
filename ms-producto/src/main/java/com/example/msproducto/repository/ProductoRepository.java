package com.example.msproducto.repository;

import com.example.msproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Para usar List

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNameContainingIgnoreCase(String name);
    List<Producto> findByCategoryNameIgnoreCase(String category);
    List<Producto> findByCode(String code);
}
