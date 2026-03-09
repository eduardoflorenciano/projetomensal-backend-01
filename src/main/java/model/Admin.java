package model;

// Herda de usuário, representa um administrador do sistema
public class Admin extends Usuario {
    // Construtor que repassa os dados para a classe pai via super();
    public Admin(String nome, String email, String senha) {
        super(nome, email, senha);
    }
    // Implementação do método abstrato, exibe perfil com etiqueta [ ADMIN ]
    @Override
    public String exibirPerfil() {
        return "[ ADMIN ] " + getNome() + " | " + getEmail();
    }
}