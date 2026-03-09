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

    // Retorna o nome do usuário
    public String getNome() {
        return nome;
    }

    // Retorna o email do usuário
    public String getEmail() {
        return email;
    }

    // Retorna a senha do usuário, usada pelo AuthService no login
    public String getSenha() {
        return senha;
    }

    // Método abstrato, cada subclasse exibe o perfil do seu jeito (polimorfismo)
    public abstract String exibirPerfil();
}
