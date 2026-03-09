import service.ProdutoService;
import service.ServicoService;
import service.AuthService;
import model.Usuario;
import java.util.Scanner;

// Ponto de entrada do sistema
public class Main {
    // Scanner único usado por todos os métodos de leitura
    static Scanner scanner = new Scanner(System.in);

    // Instâncias dos serviços usadas em todo o programa
    static ProdutoService produtoService = new ProdutoService();
    static ServicoService servicoService = new ServicoService();
    static AuthService authService = new AuthService();

    // Método principal
    public static void main(String[] args) {
        // Exige login antes de abrir o sistema
        Usuario usuarioLogado = telaDeLogin();

        // Exibe boas-vindas com o perfil do usuário logado
        System.out.println("\n\u001B[36m======================================\u001B[0m");
        System.out.println("  BEM-VINDO AO SISTEMA DA AUTO CENTER SILVA   ");
        System.out.println("  " + usuarioLogado.exibirPerfil());
        System.out.println("\u001B[36m======================================\u001B[0m");

        int opcao = -1;

        // Loop principal, mantém o sistema rodando até o usuário sair
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    menuServicos();
                    break;
                case 0:
                    // Despedida personalizada com o nome do usuário logado
                    System.out.println("\n\u001B[33mSistema encerrado. Até logo,\u001B[0m " + usuarioLogado.getNome() + "\u001B[33m!\u001B[0m");
                    break;
                default:
                    System.out.println("\u001B[31mOpção inválida! Tente novamente\u001B[0m");
            }
        }

        // Fecha o Scanner ao encerrar o programa
        scanner.close();
    }

    // Exibe o menu de login e fica em loop até o login ser concluído
    static Usuario telaDeLogin() {
        Usuario logado = null;

        while (logado == null) {
            System.out.println("\n\u001B[36m===================================\u001B[0m");
            System.out.println("         SISTEMA - LOGIN         ");
            System.out.println("\u001B[36m===================================\u001B[0m");
            System.out.println("1 - Entrar                     ");
            System.out.println("2 - Cadastrar novo usuário     ");
            System.out.println("0 - Sair                       ");
            System.out.println("\u001B[36m===================================\u001B[0m");

            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    // Se o login falhar, logado fica null e o loop repete
                    logado = fluxoDeLogin();
                    break;
                case 2:
                    fluxoDeCadastro();
                    break;
                case 0:
                    System.out.println("\u001B[33mSaindo do sistema\u001B[0m");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\u001B[31mOpção inválida!\u001B[0m");
            }
        }

        return logado;
    }

    // Coleta email e senha, o usuário tem 3 tentativas antes de voltar ao menu
    static Usuario fluxoDeLogin() {
        int tentativas = 3;

        while (tentativas > 0) {
            System.out.println("\n\u001B[36m======\u001B[0m LOGIN \u001B[36m======\u001B[0m");
            String email = lerTexto("Email: ");
            String senha = lerSenha("Senha: ");

            Usuario usuario = authService.login(email, senha);

            // Login correto, retorna o usuário autenticado
            if (usuario != null) {
                System.out.println("\n\u001B[32mLogin realizado com sucesso!\u001B[0m");
                return usuario;
            }

            // Login errado, desconta uma tentativa
            tentativas--;
            if (tentativas > 0) {
                System.out.println("\u001B[31mEmail ou senha incorretos! Tentativas restantes:\u001B[0m " + tentativas);
            } else {
                System.out.println("\u001B[33mNúmero de tentativas esgotado. Voltando ao menu de login\u001B[0m");
            }
        }

        // Retorna null para indicar falha no login
        return null;
    }

    // Coleta os dados de um novo usuário e registra no sistema
    static void fluxoDeCadastro() {
        System.out.println("\n\u001B[36m======\u001B[0m CADASTRAR NOVO USUÁRIO \u001B[36m======\u001B[0m");
        String nome = lerTexto("Seu nome: ");
        String email = lerTexto("Email: ");
        String senha = lerSenha("Crie uma senha: ");
        authService.cadastrarUsuario(nome, email, senha);
    }

    // Exibe as opções do menu principal
    static void exibirMenuPrincipal() {
        System.out.println("\n\u001B[36m======\u001B[0m MENU PRINCIPAL \u001B[36m======\u001B[0m");
        System.out.println("1 - Gerenciar Produtos");
        System.out.println("2 - Gerenciar Serviços");
        System.out.println("0 - Sair");
        System.out.println("\u001B[36m============================\u001B[0m");
    }
}