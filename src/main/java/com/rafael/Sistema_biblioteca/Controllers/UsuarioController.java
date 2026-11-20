package com.rafael.Sistema_biblioteca.Controllers;

import com.rafael.Sistema_biblioteca.Models.Usuario;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    static final List<Usuario> USUARIOS = new ArrayList<>();

    @GetMapping({"", "/", "/inicio"})
    public String listar(Model model) {
        model.addAttribute("usuarios", USUARIOS);
        return "usuarios/inicio";
    }

    @GetMapping("/novo")
    public String cadastrar() {
        return "usuarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        usuario.setId(USUARIOS.stream().mapToInt(Usuario::getId).max().orElse(0) + 1);
        USUARIOS.add(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Usuario usuario = USUARIOS.stream().filter(item -> item.getId() == id).findFirst().get();
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable int id, @ModelAttribute Usuario dados) {
        Usuario usuario = USUARIOS.stream().filter(item -> item.getId() == id).findFirst().get();
        usuario.setNome(dados.getNome());
        usuario.setCpf(dados.getCpf());
        usuario.setEmail(dados.getEmail());
        usuario.setMatricula(dados.getMatricula());
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable int id) {
        USUARIOS.removeIf(item -> item.getId() == id);
        return "redirect:/usuarios";
    }
}
