package com.santos.springboot.app.dao;


import com.santos.springboot.app.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria,Integer> {
}
