package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class EjemploVariablesRutaController {
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("titulo","Enviar parametros de la ruta (@PathVariable)");
		return "variables/index";
		
	}
	
	@GetMapping("/string/{texto}")//el nombre de la variables tiene que ser igual al de la ruta o se le tiene que indicar con name
	public String variables(@PathVariable(name="texto") String texto,Model model ) {
		
		model.addAttribute("titulo","Recibir parametros de la ruta (@PathVariable)");
		model.addAttribute("resultado","El texto enviado es: "+ texto);
		
		return "variables/ver";
	}
	@GetMapping("/string/{texto}/{numero}")//el nombre de la variables tiene que ser igual al de la ruta o se le tiene que indicar con name
	public String variables(@PathVariable(name="texto") String texto,@PathVariable Integer numero, Model model ) {
		
		model.addAttribute("titulo","Recibir parametros de la ruta (@PathVariable)");
		model.addAttribute("resultado","El texto enviado es: "+ texto + "y el numero enviado en la ruta es: "+numero);
		
		return "variables/ver";
	}
	
}
