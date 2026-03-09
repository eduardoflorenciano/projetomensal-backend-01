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

    // Exibe as opções do menu de produtos
    static void exibirMenuProdutos() {
        System.out.println("\n\033[34m======\033[0m MENU DE PRODUTOS \033[34m======\033[0m");
        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Atualizar Produto");
        System.out.println("4 - Remover Produto");
        System.out.println("5 - Buscar por Nome");
        System.out.println("6 - Buscar por Categoria");
        System.out.println("7 - Relatório de Produtos");
        System.out.println("0 - Voltar");
        System.out.println("\033[34m==============================\033[0m");
    }

    // Loop do submenu de produtos, permanece até o usuário voltar
    static void menuProdutos() {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenuProdutos();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1:
                    cadastraProdutos();
                    break;

                case 2:
                    produtoService.listarTodos();
                    break;

                case 3:
                    atualizarProduto();
                    break;

                case 4:
                    // Lista os produtos para o usuário ver os IDs antes de remover
                    System.out.println("\n\033[34m======\033[0m REMOVER PRODUTO \033[34m======\033[0m");
                    produtoService.listarTodos();
                    int idRemover = lerInteiro("ID do produto a remover: ");
                    produtoService.remover(idRemover);
                    break;

                case 5:
                    String buscarNome = lerTexto("Digite o nome para buscar: ");
                    produtoService.buscarPorNome(buscarNome);
                    break;

                case 6:
                    String buscarCategoria = lerTexto("Digite a categoria para buscar: ");
                    produtoService.buscarPorCategoria(buscarCategoria);
                    break;

                case 7:
                    produtoService.exibirRelatorio();
                    break;

                case 0:
                    System.out.println("\u001B[33mVoltando ao menu principal\u001B[0m");
                    break;

                default:
                    System.out.println("\u001B[31mOpção inválida! Tente novamente\u001B[0m");
            }
        }
    }

    // Coleta os dados do novo produto, estoque não pode ser zero no cadastro
    static void cadastraProdutos() {
        System.out.println("\033[34m======\033[0m CADASTRAR PRODUTO \033[34m======\033[0m");
        String nome = lerTexto("Nome do produto: ");
        double preco = lerDouble("Preço (ex: R$ 199.90): R$ ");
        // Impede cadastro com valor do produto esteja zerado ou negativo
        while (preco <= 0) {
            preco = lerDouble("\u001B[33mPor favor, ensira um valor acima de 0 para o preço:\u001B[0m ");
        }
        int estoque = lerInteiro("Quantidade em estoque: ");
        // Impede cadastro com estoque zerado ou negativo
        while (estoque <= 0) {
            estoque = lerInteiro("\u001B[33mPor favor, ensira um valor acima de 0 em estoque:\u001B[0m ");
        }
        String categoria = escolhaCategoria();
        produtoService.cadastrar(nome, preco, estoque, categoria);
    }

    // Coleta os novos dados, pede confirmação se o estoque for zerado
    static void atualizarProduto() {
        System.out.println("\n\033[34m======\033[0m ATUALIZAR PRODUTO \033[34m======\033[0m");
        produtoService.listarTodos();
        int idAtualizar = lerInteiro("ID do produto a atualizar: ");
        String novoNome = lerTexto("Novo nome: ");
        double novoPreco = lerDouble("Novo preço: R$ ");
        // Impede cadastro com valor do produto esteja zerado ou negativo
        while (novoPreco <= 0) {
            novoPreco = lerDouble("\u001B[33mPor favor, ensira um valor acima de 0 para o preço:\u001B[0m ");
        }
        boolean continuarEstoque = false;
        int novoEstoque = lerInteiro("Quantidade em estoque: ");

        while (!continuarEstoque) {
            if (novoEstoque <= 0) {
                // Estoque zerado, pede confirmação antes de salvar
                System.out.println("\u001B[33mTem certeza em deixar o estoque zerado?\u001B[0m ");
                char continuar = lerChar("\u001B[33m(S para SIM e N para NÃO):\u001B[0m ");
                if (continuar == 's' || continuar == 'S') {
                    continuarEstoque = true;
                    String categoria2 = escolhaCategoria();
                    produtoService.atualizar(idAtualizar, novoNome, novoPreco, novoEstoque, categoria2);
                    break;
                } else {
                    // Usuário não confirmou, pede o estoque novamente
                    novoEstoque = lerInteiro("\u001B[33mPor favor, ensira novamente a quantidade em estoque:\u001B[0m ");
                }
            } else {
                // Estoque válido, salva normalmente
                String categoria2 = escolhaCategoria();
                produtoService.atualizar(idAtualizar, novoNome, novoPreco, novoEstoque, categoria2);
                break;
            }
        }
    }
}