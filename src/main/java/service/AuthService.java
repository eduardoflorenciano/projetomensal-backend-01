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



}
