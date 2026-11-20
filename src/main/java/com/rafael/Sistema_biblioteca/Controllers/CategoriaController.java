package com.rafael.Sistema_biblioteca.Controllers;

import com.rafael.Sistema_biblioteca.Models.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    static final List<Categoria> CATEGORIAS = new ArrayList<>();

    @GetMapping({"", "/", "/inicio"})
    public String listar(Model model) {
        model.addAttribute("categorias", CATEGORIAS);
        return "categorias/inicio";
    }

    @GetMapping("/novo")
    public String cadastrar() {
        return "categorias/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Categoria categoria) {
        categoria.setId(CATEGORIAS.stream().mapToInt(Categoria::getId).max().orElse(0) + 1);
        CATEGORIAS.add(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Categoria categoria = CATEGORIAS.stream().filter(item -> item.getId() == id).findFirst().get();
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Categoria dados) {
        Categoria categoria = CATEGORIAS.stream().filter(item -> item.getId() == id).findFirst().get();
        categoria.setNome(dados.getNome());
        categoria.setDescricao(dados.getDescricao());
        return "redirect:/categorias";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable int id) {
        CATEGORIAS.removeIf(item -> item.getId() == id);
        return "redirect:/categorias";
    }
}
