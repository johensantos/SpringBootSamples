package com.bolsadeideas.springboot.di.app.models.service;


//@Primary
//@Component("miServicioSimple") //PUEDE SER SERVICE O COMPONENT
public class MiServicio implements IServicio{

	@Override
	public String operacion() {
		// TODO Auto-generated method stub
		return "EJECUTANDO ALGUN PROCESO IMPORTANTE SIMPLE";
	}

	//TODO COMPONENTE SPRING TIENE QUE TENER UN UN CONSTRUCTOR SIN ARGUMENTO

	
}
