package com.rafael.Sistema_biblioteca.Controllers;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @GetMapping
    public String inicio(){
        return "emprestimos/inicio";
    }
}
