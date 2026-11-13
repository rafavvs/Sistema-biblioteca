package Models;

public class Usuario {
    private int id;
    private String nome;
    private int cpf;
    private String email;
    private int matricula;

    public Usuario(int id, String nome, int cpf, String email, int matricula) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.matricula = matricula;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
