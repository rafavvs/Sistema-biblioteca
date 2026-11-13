package com.rafael.Sistema_biblioteca.Controllers;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @GetMapping
    public String inicio(){
        return "autores/inicio";
    }
}
