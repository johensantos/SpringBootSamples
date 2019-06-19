package com.santos.springboot.app.controllers;


import com.santos.springboot.app.entity.Categoria;
import com.santos.springboot.app.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoriaController {


    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping(value = "/listarCategoria")
    public String listar(Model model){

        List<Categoria> categorias = categoriaService.findAll();

        model.addAttribute("Titulo",".:Categorias:.");
        model.addAttribute("categorias",categorias);

        return "categoria/listarCategoria";

    }

}
