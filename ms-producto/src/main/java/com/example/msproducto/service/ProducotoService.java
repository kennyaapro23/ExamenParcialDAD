package com.example.msproducto.service;

import com.example.msproducto.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listAll();

    Optional<Producto> findById(Integer id);

    Producto save(Producto producto);

    Producto update(Producto producto);

    void deleteById(Integer id);

    void reduceStock(Integer productId, Integer amount);

    List<Producto> searchByName(String name);

    List<Producto> searchByCategory(String category);

    List<Producto> searchByCode(String code);

    List<Producto> advancedSearch(String name, String category, String code);
}