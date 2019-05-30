package com.bolsadeideas.springboot.web.app.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.web.app.models.Usuario;


@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${texto.indexcontroller.index.titulo}")
	private String textoIndex;
	@Value("${texto.indexcontroller.perfil.titulo}")
	private String textoPerfil;
	@Value("${texto.indexcontroller.listar.titulo}")
	private String textoListar;
	
	//@Por defecto es GET, pero tambien se puede especificar.
	//@GetMapping es lo mismo
	//se pone llaves para asignar varias rutas	
	@RequestMapping(value= {"/index","/","","/home"},method=RequestMethod.GET)
	public String index(Model model) {	//tambien se puede usar modelMap,Model, Map, ModelAndView
		model.addAttribute("titulo",textoIndex);
		
		return "index";	
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Mauro");
		usuario.setApellido("Guevara");
		usuario.setEmail("maurojohen@gmail.com");
		model.addAttribute("titulo",textoPerfil+usuario.getNombre());
		model.addAttribute("usuario",usuario);
		return "perfil";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		
		
		//usuarios.add(new Usuario("Mauro","Guevara","santos@gmail.com"));
		//usuarios.add(new Usuario("johen","Guevara","johen@xxx.com"));
		//usuarios.add(new Usuario("eduardo","Guzman","eduardo@gmail.com"));
		
		model.addAttribute("titulo",textoListar);
		//model.addAttribute("usuarios",usuarios);
		
		return "listar";
	}
	
	
	//PASA LA LISTA DE USUARIOS A TODOS LOS REQUESTAMPPING OSEA ESTA DISPONIBLE EN TODOS LOS METODOS
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Mauro","Guevara","santos@gmail.com"));
		usuarios.add(new Usuario("johen","Guevara","johen@xxx.com"));
		usuarios.add(new Usuario("eduardo","Guzman","eduardo@gmail.com"));
		
		return usuarios;
	}

	
	

}
