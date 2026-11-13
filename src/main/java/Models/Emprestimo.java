package model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(int id, Livro livro, Usuario usuario, int diasPrazo) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataRetirada = LocalDate.now();
        this.dataDevolucaoPrevista = this.dataRetirada.plusDays(diasPrazo);
        this.livro.setDisponivel(false);
    }

    public void registrarDevolucao() {
        if (this.dataDevolucaoReal == null) {
            this.dataDevolucaoReal = LocalDate.now();
            this.livro.setDisponivel(true);
        }
    }

    public boolean isAtivo() {
        return this.dataDevolucaoReal == null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}