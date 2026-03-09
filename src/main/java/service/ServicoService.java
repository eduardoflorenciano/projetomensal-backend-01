package service;
import model.Servico;
import java.util.ArrayList;
import java.util.List;

public class ServicoService {
    // Lista de serviços cadastrados no sistema
    private List<Servico> servicos = new ArrayList<>();

    // Controla o ID automático, incrementado a cada novo serviço
    private int proximoId = 1;

    // Cadastrar, cria um novo Servico e adiciona na lista
    public void cadastrar(String nome, double preco, int duracaoMinutos, String tipo) {
        Servico novoServico = new Servico(proximoId, nome, preco, duracaoMinutos, tipo);
        servicos.add(novoServico);
        proximoId++;
        System.out.println("\u001B[32mServiço cadastrado com sucesso! ID:\u001B[0m " + novoServico.getId());
    }

    // Listar, exibe todos os serviços cadastrados
    public void listarTodos() {
        if (servicos.isEmpty()) {
            System.out.println("\u001B[31mNenhum serviço cadastrado\u001B[0m");
            return;
        }
        System.out.println("\n\033[35m======\033[0m LISTA DE SERVIÇOS \033[35m======\033[0m");
        for (Servico s : servicos) {
            System.out.println(s.exibirDetalhes());
        }
    }

    // Atualizar, busca pelo ID e substitui os dados do serviço
    public void atualizar(int id, String novoNome, double novoPreco, int novaDuracao, String novoTipo) {
        Servico servico = buscarPorId(id);
        if (servico == null) {
            System.out.println("\u001B[31mServiço com ID\u001B[0m " + id + " \u001B[31mnão encontrado\u001B[0m");
            return;
        }
        servico.setNome(novoNome);
        servico.setPreco(novoPreco);
        servico.setDuracaoMinutos(novaDuracao);
        servico.setTipo(novoTipo);
        System.out.println("\u001B[32mServiço atualizado com sucesso!\u001B[0m");
    }

    // Remover, busca pelo ID e remove o serviço da lista
    public void remover(int id) {
        Servico servico = buscarPorId(id);
        if (servico == null) {
            System.out.println("\u001B[31mServiço com ID\u001B[0m " + id + " \u001B[31mnão encontrado\u001B[0m");
            return;
        }
        servicos.remove(servico);
        System.out.println("\u001B[32mServiço removido com sucesso!\u001B[0m");
    }

    // Filtro, exibe serviços cujo nome contenha o texto buscado
    public void buscarPorNome(String nome) {
        System.out.println("\n\033[35m======\033[0m BUSCA POR NOME: " + nome + " \033[35m======\033[0m");
        boolean encontrou = false;
        for (Servico s : servicos) {
            if (s.getNome().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println(s.exibirDetalhes());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("\u001B[31mNenhum serviço encontrado com esse nome\u001B[0m");
        }
    }

    // Filtro, exibe serviços que correspondam ao tipo buscado
    public void buscarPorTipo(String tipo) {
        System.out.println("\n\033[35m======\033[0m BUSCA POR TIPO: " + tipo + " \033[35m======\033[0m");
        boolean encontrou = false;
        for (Servico s : servicos) {
            if (s.getTipo().toLowerCase().contains(tipo.toLowerCase())) {
                System.out.println(s.exibirDetalhes());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("\u001B[31mNenhum serviço encontrado com esse tipo\u001B[0m");
        }
    }

    // Relatório, calcula e exibe estatísticas dos serviços
    public void exibirRelatorio() {
        if (servicos.isEmpty()) {
            System.out.println("\u001B[31mNenhum serviço para gerar relatório\u001B[0m");
            return;
        }

        double somaPrecos = 0;
        int somaDuracoes = 0;
        Servico maisCaro = servicos.get(0);
        Servico maisRapido = servicos.get(0);

        for (Servico s : servicos) {
            somaPrecos += s.getPreco();
            somaDuracoes += s.getDuracaoMinutos();

            if (s.getPreco() > maisCaro.getPreco()) maisCaro = s;
            if (s.getDuracaoMinutos() < maisRapido.getDuracaoMinutos()) maisRapido = s;
        }

        double precoMedio = somaPrecos / servicos.size();
        double duracaoMedia = (double) somaDuracoes / servicos.size();

        System.out.println("\n\033[35m======\033[0m RELATÓRIO DE SERVIÇOS \033[35m======\033[0m");
        System.out.printf("Total de serviços cadastrados: %d%n", servicos.size());
        System.out.printf("Preço médio dos serviços: R$ %.2f%n", precoMedio);
        System.out.printf("Duração média dos serviços: %.0f minutos%n", duracaoMedia);
        System.out.println("Serviço mais caro:  " + maisCaro.getNome() + " (R$ " + String.format("%.2f", maisCaro.getPreco()) + ")");
        System.out.println("Serviço mais rápido: " + maisRapido.getNome() + " (" + maisRapido.getDuracaoMinutos() + " min)");
    }

    // Busca um serviço na lista pelo ID, reutilizado em atualizar() e remover()
    private Servico buscarPorId(int id) {
        for (Servico s : servicos) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

}
