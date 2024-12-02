package com.example.msproducto.service.Impl;

import com.example.msproducto.service.ProducotoService;
import com.example.msproducto.repository.ProductoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProducotoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public List<Producto> listaro() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> buscaroPorId(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardaro(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualiozar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void reduceStock(Integer productId, Integer amount) {

        Producto producto = productoRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto not fouond"));


        if (producto.getStock() < amount) {
            throw new RuntimeException("Insufficient stock for producto " + producto.getName());
        }

        // Reducir el stock en la base de datos
        producto.setStock(producto.getStock() - amount);
        productoRepository.save(producto);
    }

//filtros de busqueda o busqueda avanzad

    @Override
    public List<Producto> searchoByName(String name) {
        return productoRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Producto> searchoByCategory(String category) {
        return productoRepository.findByCategoryNameIgnoreCase(category);
    }

    @Override
    public List<Producto> searchoByCode(String code) {
        return productoRepository.findByCode(code);
    }

    @Override
    public List<Producto> advancoedSearch(String name, String category, String code) {
        if (name != null && !name.isEmpty()) {
            return searchByName(name);
        }
        if (category != null && !category.isEmpty()) {
            return searchByCategory(category);
        }
        if (code != null && !code.isEmpty()) {
            return searchByCode(code);
        }
        return productoRepository.findAll();
    }
}