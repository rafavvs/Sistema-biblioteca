package com.rafael.Sistema_biblioteca.Controllers;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @GetMapping
    public String inicio(){
        return "categorias/inicio";
    }
}
