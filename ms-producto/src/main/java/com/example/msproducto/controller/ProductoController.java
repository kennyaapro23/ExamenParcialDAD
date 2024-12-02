package com.example.msproducto.controller;

import com.example.msproducto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> list(){
        return ResponseEntity.ok().body(productoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(productoService.buscarPorId(id).get());
    }

    @PostMapping()
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        return ResponseEntity.ok().body(productoService.guardar(producto));
    }

    @PutMapping()
    public ResponseEntity<Producto> update(@RequestBody Producto producto){
        return ResponseEntity.ok().body(productoService.actualizar(producto));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true)Integer id){
        productoService.eliminarPorId(id);
        return "elminacion correcta";
    }

    @PutMapping("/{id}/reduce-stock")
    public ResponseEntity<Void> reduceStock(@PathVariable Integer id, @RequestParam Integer amount) {
        productoService.reduceStock(id, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Producto>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String code) {

        List<Producto> results = productoService.advancedSearch(name, categoria, code);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/{id}/increase-stock")
    public ResponseEntity<Void> increaseStock(@PathVariable Integer id, @RequestParam Integer amount) {
        System.out.println("Increase stock called for Producto ID: " + id + " with Amount: " + amount);

        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto not found"));

        System.out.println("Producto found: " + producto);

        producto.setStock(producto.getStock() + amount);
        productoService.actualizar(producto);
        return ResponseEntity.ok().build();
    }
}