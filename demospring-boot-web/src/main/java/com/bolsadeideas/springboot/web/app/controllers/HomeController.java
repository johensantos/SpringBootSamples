package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

		@GetMapping("/")
		public String Home() {
			return "forward:/app/index";
			//REDIRECT CAMBIA LA RUTA
			//FORWARD NO CAMBIA LA URL POR LO TANTO SI SE PUEDEN USAR LAS PARABLES Y PARMS
			
		}
}
