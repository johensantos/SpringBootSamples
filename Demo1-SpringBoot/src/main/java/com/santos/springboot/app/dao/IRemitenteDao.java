package com.santos.springboot.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.santos.springboot.app.entity.Remitente;

public interface IRemitenteDao extends CrudRepository<Remitente, Long>{

}
