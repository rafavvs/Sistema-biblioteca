package Models;

import java.util.ArrayList;
import java.util.List;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;
    private List<Livro> obrasPublicadas;

    public Autor(int id, String nome, String nacionalidade, List<Livro> obrasPublicadas) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.obrasPublicadas = obrasPublicadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Livro> getObrasPublicadas() {
        return obrasPublicadas;
    }

    public void setObrasPublicadas(List<Livro> obrasPublicadas) {
        this.obrasPublicadas = obrasPublicadas;
    }
}
