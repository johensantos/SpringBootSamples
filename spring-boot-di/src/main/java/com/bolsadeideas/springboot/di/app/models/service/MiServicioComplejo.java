package com.bolsadeideas.springboot.di.app.models.service;


//@Primary//SELECCIONA LA IMPLEMENTACION POR DEFAULT AL USAR LA INTERFACE
//@Component("miServicioComplejo") //PUEDE SER SERVICE O COMPONENTE
public class MiServicioComplejo implements IServicio{

	@Override
	public String operacion() {
		// TODO Auto-generated method stub
		return "EJECUTANDO ALGUN PROCESO IMPORTANTE COMPLICADO";
	}

	//TODO COMPONENTE SPRING TIENE QUE TENER UN UN CONSTRUCTOR SIN ARGUMENTO

	
}
