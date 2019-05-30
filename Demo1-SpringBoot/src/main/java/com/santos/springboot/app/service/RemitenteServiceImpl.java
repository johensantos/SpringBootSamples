package com.santos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.santos.springboot.app.dao.IRemitenteDao;
import com.santos.springboot.app.entity.Remitente;

@Service
public class RemitenteServiceImpl implements IRemitenteService {

	@Autowired
	private IRemitenteDao remitenteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Remitente> findAll() {
		
		return (List<Remitente>) remitenteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Remitente Remitente) {
		remitenteDao.save(Remitente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Remitente findOne(Long id) {
		// TODO Auto-generated method stub
		return remitenteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {

			remitenteDao.deleteById(id);
		
	}

}
