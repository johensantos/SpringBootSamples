package com.santos.springboot.app.service;

import com.santos.springboot.app.dao.ICategoriaDao;
import com.santos.springboot.app.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService{


    @Autowired
    private  ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        return  (List<Categoria>) categoriaDao.findAll();
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
            categoriaDao.save(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findOne(Integer id) {
      return  categoriaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoriaDao.deleteById(id);
    }
}
