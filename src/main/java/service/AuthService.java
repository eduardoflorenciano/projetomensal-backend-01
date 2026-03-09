package service;
import model.Admin;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    // Lista de usuários cadastrados no sistema
    private List<Usuario> usuarios = new ArrayList<>();

    // Adiciona um usuário padrão ao iniciar o sistema
    public AuthService() {
        usuarios.add(new Admin("Administrador", "admin@oficina.com", "admin123"));
    }
    // Cadastra um novo usuário se o email aina não existir
    public void cadastrarUsuario(String nome, String email, String senha) {
        if (buscarPorEmail(email) != null) {
            System.out.println("\u001B[33mEste email já está cadastrado\u001B[0m");
            return;
        }
        usuarios.add(new Admin(nome, email, senha));
        System.out.println("\u001B[32mUsuário cadastrado com sucesso!\u001B[0m");
    }

    // Verifica email e senha, retorna o Usuario se corretos, ou null se errados
    public Usuario login(String email, String senha) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }


}
