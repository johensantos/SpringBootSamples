package com.santos.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santos.springboot.app.entity.Remitente;
import com.santos.springboot.app.service.IRemitenteService;

@Controller
public class RemitenteController {
		
		@Autowired
		private IRemitenteService remitenteService;
	
		@RequestMapping(value= {"/listar","/"})
		public String listar(Model model) {
			
			model.addAttribute("titulo",".::Remitentes::.");
			
			model.addAttribute("remitentes",remitenteService.findAll());
			return "listar";
		}
		
		@GetMapping(value = "/form")
		public String crear(Map<String, Object> model) {
			Remitente remitente = new Remitente();
			
			model.put("titulo",".:Form Remitentes:.");
			model.put("remitente", remitente);
		
			return "form";
		}
		
		@PostMapping(value = "/form")
		public String guardar(Remitente remitente,Model model){
			
			remitenteService.save(remitente);
			
			return "redirect:listar";
		}
		
		@GetMapping(value = "/eliminar/{idRemitente}")//tiene que ser elmismo nombre
		public String eliminar(@PathVariable Long idRemitente) {
			
			remitenteService.delete(idRemitente);
			return "redirect:/listar";
		}
		
		@GetMapping(value = "/form/{idRemitente}")
		public String remitente(@PathVariable Long idRemitente, Map<String, Object> model) {
			
			Remitente remitente;
			remitente = remitenteService.findOne(idRemitente);
			
			model.put("remitente", remitente);
			model.put("titulo", ".:Editar:.");
			
			
			return "form";
		}
		
		
}
