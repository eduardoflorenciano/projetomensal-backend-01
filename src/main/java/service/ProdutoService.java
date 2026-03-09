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
}
