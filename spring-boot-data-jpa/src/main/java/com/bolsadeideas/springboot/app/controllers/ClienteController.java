package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import com.bolsadeideas.springboot.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Controller
//Se puede usar session para guardar el objeto cliente, y ya no se tendria que mandar el hiddenId
@SessionAttributes("cliente") 
public class ClienteController {

	
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar",method = RequestMethod.GET)
	public String listar( @RequestParam(name = "page",defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page,5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		PageRender<Cliente> pageRender = new PageRender<>("/listar",clientes);

		model.addAttribute("titulo","Listado de Clientes");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page",pageRender);
		
		return "listar";
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.GET)
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("cliente", cliente);
		model.put("titulo","Formulario de Clientes");
		
		
		return "form";
	}
	
	@RequestMapping(value = "/form",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,RedirectAttributes flash ,SessionStatus status) {
		//el binding resul tiene que ir junto al objeto a ser validado
		
		if(result.hasErrors()) {
			/*el cliente se esta pasando de forma automatica, solo si tiene el mismo nombre que la clase, 
			de lo contrario usar @ModelAttribute*/
			
			model.addAttribute("titulo","Formulario del cliente");
			return "form";
		}
		
		String mensajeFlash=(cliente.getId() != null)?"Cliente editado Correctamente":"Cliente Creado Correctamente";
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success",mensajeFlash);
		
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/form/{id}",method = RequestMethod.GET)
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model,RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id>0) {
			
			cliente = clienteService.findOne(id);
				if (cliente==null) {
					flash.addFlashAttribute("error","El id del cliente no existe en la base de datos!");
					return "redirect:/listar";
				}
		}else {
			flash.addFlashAttribute("error","El id del cliente no puede ser 0");
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		model.put("boton", "Editar");
		
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}",method = RequestMethod.GET)
	public String eliminar(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
		
		
		
		if(id>0) {
			
			clienteService.delete(id);
			flash.addFlashAttribute("success","Cliente Eliminado Correctamente");
		}
		return "redirect:/listar";
		
		
	}
	
	
	
}
