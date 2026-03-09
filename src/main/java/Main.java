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

}