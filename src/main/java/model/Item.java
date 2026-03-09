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

    // Retorna o id do item
    public int getId() {
        return id;
    }

    // Retorna o nome do item
    public String getNome() {
        return nome;
    }

    // Atualiza o nome do item
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o preço do item
    public double getPreco() {
        return preco;
    }

    // Atualiza o preço do item
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método abstrato, cada subclasse implementa do seu jeito (polimorfismo)
    public abstract String exibirDetalhes();

    // Ao imprimir um Item, chama exibirDetalhes() automaticamente
    @Override
    public String toString() {
        return exibirDetalhes();
    }
}