package com.example.CrudPB.repository;

import com.example.CrudPB.entities.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITipoProductoRepository  extends JpaRepository<TipoProducto, Integer> {
}
