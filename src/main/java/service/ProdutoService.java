package service;
import model.Produto;
import java.util.ArrayList;
import java.util.List;
public class ProdutoService {
    public static void main(String[] args) {
        System.out.println("Teste");
    }
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
}
