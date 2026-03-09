package model;

// Classe abstrata base para qualquer tipo de usuário do sistema
public abstract class Usuario {
    // Atributos privados, acessados apenas pelos métodos get abaixo
    private String nome;
    private String email;
    private String senha;

    // Construtor chamado pelas subclasses via super()
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

}
