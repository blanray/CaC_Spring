package com.example.CrudPB.repository;

import com.example.CrudPB.entities.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoProductoRepository  extends JpaRepository<TipoProducto, Integer> {
}
