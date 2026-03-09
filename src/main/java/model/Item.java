package model;

// Classe abstrata base para Produto e Servico
public abstract class Item {
    // Atributos privados, só acessados pelos métodos get/set
    private int id;
    private String nome;
    private double preco;

    // Construtor chamado pelas subclasses via super();
    public Item(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }