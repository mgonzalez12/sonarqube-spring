package com.example.crud_example.repositories;

import com.example.crud_example.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto,Long> {
}
