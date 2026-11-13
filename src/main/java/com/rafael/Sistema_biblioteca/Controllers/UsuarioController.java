package com.rafael.Sistema_biblioteca.Controllers;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @GetMapping
    public String inicio(){
        return "usuarios/inicio";
    }
}
