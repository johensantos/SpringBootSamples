package com.santos.springboot.app.service;

import com.santos.springboot.app.entity.Categoria;


import java.util.List;

public interface ICategoriaService  {

    public List<Categoria> findAll();

    public void save(Categoria categoria);

    public Categoria findOne(Integer id);

    public void delete(Integer id);

}
