package com.bolsadeideas.springboot.di.app.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope//CICLO DE VIDA: CREA UNA INSTANCIA PARA CADA PETICION, EL COMPONENTE CLIENTE TAMBIEN DEVE ESTAR EN REQUESTSCOPE,
//@SessionScope - se destruye cuando se cierra en navegador o se finaliza la session, debe implementar serializable
//AplicationScope - Igual que el singleton pero en contexto de servlet
public class Factura implements Serializable {	//POR DEFECTO ES SINGLETON Y NO SE DESTRUYE HASTA CERRAR EL SERVIDOR
	private static final long serialVersionUID = 1L;


	@Value("${factura.descripcion}")
	private String descripcion;
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("itemsFacturaOficina")//busca en los beans del AppConfg
	private List<ItemFactura> items;
	
	@PostConstruct//CICLO DE VIDA, ANTES DE QUE SE CREE EL COMPONENTE
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("Mauro "));
		descripcion = descripcion.concat("Del Cliente").concat(cliente.getNombre());
	}
	
	@PreDestroy //destruye la instancia, por defecto singleton y se destruye al detener el servidor, no aplica en @SessionScope 
	public void destruir() {
		System.out.println("Factura Destruida:".concat(descripcion));
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

}
