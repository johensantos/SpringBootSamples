package com.bolsadeideas.springboot.app.models.dao;



import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

//NO ES NECESARIO INDICAR QUE ES UN REPOSITORY O SERVICE
// pagin tambien hereda a crudreposotirpy
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {


}
