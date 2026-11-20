package com.rafael.Sistema_biblioteca.Controllers;

import com.rafael.Sistema_biblioteca.Models.Emprestimo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    static final List<Emprestimo> EMPRESTIMOS = new ArrayList<>();

    @GetMapping({"", "/", "/inicio"})
    public String listar(Model model) {
        model.addAttribute("emprestimos", EMPRESTIMOS);
        return "emprestimos/inicio";
    }

    @GetMapping("/novo")
    public String cadastrar(Model model) {
        model.addAttribute("livros", LivroController.LIVROS.stream().filter(livro -> livro.isDisponivel()).toList());
        model.addAttribute("usuarios", UsuarioController.USUARIOS);
        return "emprestimos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam String isbn,
            @RequestParam int usuarioId,
            @RequestParam int diasPrazo) {
        var livro = LivroController.LIVROS.stream()
                .filter(item -> item.getIsbn().equals(isbn))
                .findFirst()
                .get();
        var usuario = UsuarioController.USUARIOS.stream()
                .filter(item -> item.getId() == usuarioId)
                .findFirst()
                .get();
        int id = EMPRESTIMOS.stream().mapToInt(Emprestimo::getId).max().orElse(0) + 1;
        EMPRESTIMOS.add(new Emprestimo(id, livro, usuario, diasPrazo));
        return "redirect:/emprestimos";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable int id, Model model) {
        Emprestimo emprestimo = EMPRESTIMOS.stream().filter(item -> item.getId() == id).findFirst().get();
        model.addAttribute("emprestimo", emprestimo);
        return "emprestimos/editar";
    }

    @PostMapping("/{id}")
    public String atualizar(
            @PathVariable int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDevolucaoPrevista) {
        Emprestimo emprestimo = EMPRESTIMOS.stream().filter(item -> item.getId() == id).findFirst().get();
        emprestimo.setDataDevolucaoPrevista(dataDevolucaoPrevista);
        return "redirect:/emprestimos";
    }

    @PostMapping("/{id}/devolver")
    public String devolver(@PathVariable int id) {
        Emprestimo emprestimo = EMPRESTIMOS.stream().filter(item -> item.getId() == id).findFirst().get();
        emprestimo.registrarDevolucao();
        return "redirect:/emprestimos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable int id) {
        Emprestimo emprestimo = EMPRESTIMOS.stream().filter(item -> item.getId() == id).findFirst().get();
        if (emprestimo.isAtivo()) {
            emprestimo.registrarDevolucao();
        }
        EMPRESTIMOS.remove(emprestimo);
        return "redirect:/emprestimos";
    }
}
