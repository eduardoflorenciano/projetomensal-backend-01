package model;

// Herda de Item, representa um produto físico da oficina
public class Produto extends Item {
    // Atributos exclusivos de Produto
    private int quantidadeEstoque;
    private String categoria;

    // Construtor, super() preenche id, nome e preco na classe pai
    public Produto(int id, String nome, double preco, int quantidadeEstoque, String categoria) {
        super(id, nome, preco);
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }