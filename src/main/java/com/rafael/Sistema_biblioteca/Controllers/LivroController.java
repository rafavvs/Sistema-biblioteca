package com.rafael.Sistema_biblioteca.Controllers;

import com.rafael.Sistema_biblioteca.Models.Categoria;
import com.rafael.Sistema_biblioteca.Models.Livro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/livros")
public class LivroController {

    static final List<Livro> LIVROS = new ArrayList<>();

    @GetMapping({"", "/", "/inicio"})
    public String listar(Model model) {
        model.addAttribute("livros", LIVROS);
        return "livros/inicio";
    }

    @GetMapping("/novo")
    public String cadastrar(Model model) {
        model.addAttribute("categorias", CategoriaController.CATEGORIAS);
        return "livros/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, @RequestParam int categoriaId) {
        Categoria categoria = CategoriaController.CATEGORIAS.stream()
                .filter(item -> item.getId() == categoriaId)
                .findFirst()
                .get();
        livro.setCategoria(categoria);
        livro.setDisponivel(true);
        LIVROS.add(livro);
        return "redirect:/livros";
    }

    @GetMapping("/{isbn}/editar")
    public String editar(@PathVariable String isbn, Model model) {
        Livro livro = LIVROS.stream().filter(item -> item.getIsbn().equals(isbn)).findFirst().get();
        model.addAttribute("livro", livro);
        model.addAttribute("categorias", CategoriaController.CATEGORIAS);
        return "livros/formulario";
    }

    @PostMapping("/{isbn}")
    public String atualizar(
            @PathVariable String isbn, @ModelAttribute Livro dados, @RequestParam int categoriaId) {
        Livro livro = LIVROS.stream().filter(item -> item.getIsbn().equals(isbn)).findFirst().get();
        Categoria categoria = CategoriaController.CATEGORIAS.stream()
                .filter(item -> item.getId() == categoriaId)
                .findFirst()
                .get();
        livro.setTitulo(dados.getTitulo());
        livro.setAutor(dados.getAutor());
        livro.setCategoria(categoria);
        return "redirect:/livros";
    }

    @PostMapping("/{isbn}/excluir")
    public String excluir(@PathVariable String isbn) {
        LIVROS.removeIf(item -> item.getIsbn().equals(isbn));
        return "redirect:/livros";
    }
}
