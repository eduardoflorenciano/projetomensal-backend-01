package service;
import model.Produto;
import java.util.ArrayList;
import java.util.List;
public class ProdutoService {

    // Lista de produtos cadastrados no sistema
    private List<Produto> produtos = new ArrayList<>();

    // Controla o ID automático, incrementado a cada novo produto
    private int proximoId = 1;

    // Cadastrar, cria um novo Produto e adiciona na lista
    public void cadastrar(String nome, double preco, int estoque, String categoria) {
        Produto novoProduto = new Produto(proximoId, nome, preco, estoque, categoria);
        produtos.add(novoProduto);
        proximoId++;
        System.out.println("\u001B[32mProduto cadastrado com sucesso! ID:\u001B[0m " + novoProduto.getId());
    }

    // Listar, exibe todos os produtos cadastrados
    public void listarTodos() {
        if (produtos.isEmpty()) {
            System.out.println("\u001B[31mNenhum produto cadastrado\u001B[0m");
            return;
        }
        System.out.println("\n\033[34m======\033[0m LISTA DE PRODUTOS \033[34m======\033[0m");
        for (Produto p : produtos) {
            System.out.println(p.exibirDetalhes());
        }
    }

    // Atualizar, busca pelo ID e substitui os dados do produto
    public void atualizar(int id, String novoNome, double novoPreco, int novoEstoque, String novaCategoria) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            System.out.println("\u001B[31mProduto com ID\u001B[0m " + id + " \u001B[31mnão encontrado\u001B[0m");
            return;
        }
        produto.setNome(novoNome);
        produto.setPreco(novoPreco);
        produto.setQuantidadeEstoque(novoEstoque);
        produto.setCategoria(novaCategoria);
        System.out.println("\u001B[32mProduto atualizado com sucesso!\u001B[0m");
    }

    // Remover, busca pelo ID e remove o produto da lista
    public void remover(int id) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            System.out.println("\u001B[31mProduto com ID\u001B[0m " + id + " \u001B[31mnão encontrado\u001B[0m");
            return;
        }
        produtos.remove(produto);
        System.out.println("\u001B[32mProduto removido com sucesso!\u001B[0m");
    }

    // Filtro, exibe produtos cujo nome contenha o texto buscado
    public void buscarPorNome(String nome) {
        System.out.println("\n\033[34m======\033[0m BUSCA POR NOME: " + nome + " \033[34m======\033[0m");
        boolean encontrou = false;
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println(p.exibirDetalhes());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("\u001B[31mNenhum produto encontrado com esse nome\u001B[0m");
        }
    }
    // Filtro, exibe produtos que pertençam à categoria buscada

    public void buscarPorCategoria(String categoria) {
        System.out.println("\n\033[34m======\033[0m BUSCA POR CATEGORIA: " + categoria + " \033[34m======\033[0m");
        boolean encontrou = false;
        for (Produto p : produtos) {
            if (p.getCategoria().toLowerCase().contains(categoria.toLowerCase())) {
                System.out.println(p.exibirDetalhes());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("\u001B[31mNenhum produto encontrado nessa categoria\u001B[0m");
        }
    }

    // Relatório, calcula e exibe estatísticas dos produtos
    public void exibirRelatorio() {
        if (produtos.isEmpty()) {
            System.out.println("\u001B[31mNenhum produto para gerar relatório\u001B[0m");
            return;
        }

        double valorTotalEstoque = 0;
        int totalUnidades = 0;
        Produto maisCaro = produtos.get(0);
        Produto maisBarato = produtos.get(0);

        for (Produto p : produtos) {
            valorTotalEstoque += p.getPreco() * p.getQuantidadeEstoque();
            totalUnidades += p.getQuantidadeEstoque();

            if (p.getPreco() > maisCaro.getPreco()) maisCaro = p;
            if (p.getPreco() < maisBarato.getPreco()) maisBarato = p;
        }

        double precoMedio = valorTotalEstoque / totalUnidades;

        System.out.println("\n\033[34m======\033[0m RELATÓRIO DE PRODUTOS \033[34m======\033[0m");
        System.out.printf("Total de tipos de produto: %d%n", produtos.size());
        System.out.printf("Total de unidades em estoque: %d%n", totalUnidades);
        System.out.printf("Valor total em estoque: R$ %.2f%n", valorTotalEstoque);
        System.out.printf("Preço médio ponderado: R$ %.2f%n", precoMedio);
        System.out.println("Produto mais caro: " + maisCaro.getNome() + " (R$ " + String.format("%.2f", maisCaro.getPreco()) + ")");
        System.out.println("Produto mais barato: " + maisBarato.getNome() + " (R$ " + String.format("%.2f", maisBarato.getPreco()) + ")");
    }

    // Busca um produto na lista pelo ID, reutilizado em atualizar() e remover()
    private Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


}
