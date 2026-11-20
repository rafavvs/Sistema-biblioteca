package com.rafael.Sistema_biblioteca.Controllers;

import com.rafael.Sistema_biblioteca.Models.Autor;
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
@RequestMapping("/autores")
public class AutorController {

    static final List<Autor> AUTORES = new ArrayList<>();

    @GetMapping({"", "/", "/inicio"})
    public String listar(Model model) {
        model.addAttribute("autores", AUTORES);
        return "autores/inicio";
    }

    @GetMapping("/novo")
    public String cadastrar() {
        return "autores/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Autor autor) {
        autor.setId(AUTORES.stream().mapToInt(Autor::getId).max().orElse(0) + 1);
        AUTORES.add(autor);
        return "redirect:/autores";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Autor autor = AUTORES.stream().filter(item -> item.getId() == id).findFirst().get();
        model.addAttribute("autor", autor);
        return "autores/formulario";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Autor dados) {
        Autor autor = AUTORES.stream().filter(item -> item.getId() == id).findFirst().get();
        autor.setNome(dados.getNome());
        autor.setNacionalidade(dados.getNacionalidade());
        return "redirect:/autores";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable int id) {
        AUTORES.removeIf(item -> item.getId() == id);
        return "redirect:/autores";
    }
}
