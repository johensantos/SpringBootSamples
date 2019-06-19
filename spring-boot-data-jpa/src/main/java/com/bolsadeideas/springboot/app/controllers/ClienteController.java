package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import com.bolsadeideas.springboot.app.models.service.IUploadFileService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@Controller
//Se puede usar session para guardar el objeto cliente, y ya no se tendria que mandar el hiddenId
@SessionAttributes("cliente") 
public class ClienteController {

	
	@Autowired
	private IClienteService clienteService;


	@Autowired
	private IUploadFileService uploadFileService;



	//ver la foto de forma programatica
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verfoto(@PathVariable String filename){

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"").body(recurso);
	}




	//METODO PARA VER LA FOTO
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id,Map<String,Object> model , RedirectAttributes flash){

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error","El cliente no existe en la base de datos");
					return "redirect:/listar";

		}
		model.put("cliente",cliente);
		model.put("titulo","Detalle cliente : "+cliente.getNombre());


		return "ver";

	}




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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file")MultipartFile foto, RedirectAttributes flash , SessionStatus status) {
		//el binding resul tiene que ir junto al objeto a ser validado
		
		if(result.hasErrors()) {
			/*el cliente se esta pasando de forma automatica, solo si tiene el mismo nombre que la clase, 
			de lo contrario usar @ModelAttribute*/
			
			model.addAttribute("titulo","Formulario del cliente");
			return "form";
		}
		if(!foto.isEmpty()){
			//Path directorioRecursos = Paths.get("src//main//resources//static/upload"); --directorio interno en el mismo proyecto
			//String rootPath = directorioRecursos.toFile().getAbsolutePath(); --directorio interno en el mismo proyecto


			//String rootPath = "c://Temp//uploads"; --DIRECTORIO ESTATICO FUERA DEL PROYECTO




			if (cliente.getId() !=null && cliente.getId()>0 && cliente.getFoto().length()>0){

				uploadFileService.delete(cliente.getFoto());
			}

			String uniqueFileName = null;
			try {
				uniqueFileName = uploadFileService.copy(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}

			flash.addFlashAttribute("info","Has subido correctamente'"+uniqueFileName+"'");
			cliente.setFoto(uniqueFileName);




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
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			flash.addFlashAttribute("success","Cliente Eliminado Correctamente");



				if (uploadFileService.delete(cliente.getFoto())){
					flash.addFlashAttribute("info","Foto :"+ cliente.getFoto() + "eliminada con exito!");
				}




		}
		return "redirect:/listar";
		
		
	}
	
	
	
}
