package com.santos.springboot.app.service;

import java.util.List;
import com.santos.springboot.app.entity.Remitente;;



public interface IRemitenteService {
	
	public List<com.santos.springboot.app.entity.Remitente> findAll();

	public void save(Remitente Remitente);
	
	public Remitente findOne(Long id);
	
	public void delete(Long id);

}
