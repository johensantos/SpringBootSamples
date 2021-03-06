package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.di.app.models.service.IServicio;

@Controller
public class IndexController {
 
	
	//inyeccion por atributo @autowired
	@Autowired
	@Qualifier("miServicioComplejo") //para seleccionar la implementacion
	private IServicio servicio;
	
	/*
	@autowired//en las ultimas versiones se puede omitir el autowired
	public IndexController(IServicio servicio) {
		
		this.servicio = servicio;
	}*/



	@GetMapping({"/","","/index"})
	public String index(Model model) {
		model.addAttribute("objeto",servicio.operacion());
		return "index";
		
		
	}

	/*//inyeccion por metodo set
	@Autowired
	public void setServicio(IServicio servicio) {
		this.servicio = servicio;
	}*/
	
	
	
}
