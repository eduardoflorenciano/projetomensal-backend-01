package model;

// Herda de Item, representa um serviço prestado pela oficina
public class Servico extends Item {
    // Atributos exclusivos de Servico
    private int duracaoMinutos;
    private String tipo;

    // Construtor, super() preenche id, nome e preco na classe pai
    public Servico(int id, String nome, double preco, int duracaoMinutos, String tipo) {
        super(id, nome, preco);
        this.duracaoMinutos = duracaoMinutos;
        this.tipo = tipo;
    }


}
