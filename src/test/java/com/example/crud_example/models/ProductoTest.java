package com.example.crud_example.models;

import com.example.crud_example.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductoTest {

    @Autowired
    private ProductoRepository repository;

    @Test
    public void guardarProducto(){
        Producto producto = new Producto("Televisor Samsung",3000.00);
        Producto productoGuardado = repository.save(producto);

        assertNotNull(productoGuardado);
    }

}