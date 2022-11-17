package com.example.CrudPB.repository;

import com.example.CrudPB.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Integer>{
}
