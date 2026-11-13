package com.rafael.Sistema_biblioteca.Controllers;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @GetMapping("/livro")
    public String inicio(){
        return "livros/inicio";
    }

    public String exibirLivro(Model model){

        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setAutor("Machado de Assis");
        livro.setIsbn("978-85-00-0000-0");

        model.addAttribute("livro", livro);
        return "livro";
    }
}
