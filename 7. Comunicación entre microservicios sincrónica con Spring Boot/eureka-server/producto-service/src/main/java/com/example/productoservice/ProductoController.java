package com.example.productoservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private static final List<Producto> PRODUCTOS = List.of(
        new Producto(1L, "Laptop", 1200.00),
        new Producto(2L, "Smartphone", 800.00)
    );

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return PRODUCTOS.stream()
            .filter(p -> p.id().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return PRODUCTOS;
    }
} 