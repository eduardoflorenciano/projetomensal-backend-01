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

    // Retorna a duração do serviço em minutos
    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    // Atualiza a duração do serviço
    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    // Retorna o tipo do serviço
    public String getTipo() {
        return tipo;
    }

    // Atualiza o tipo de serviço
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Implementação do método abstrato, exibe detalhes específicos de Servico em roxo
    @Override
    public String exibirDetalhes() {
        return String.format(
                "\033[35m[SERVIÇO]\033[0m ID: %d | Nome: %s | Tipo: %s | Preço: R$ %.2f | Duração: %d min",
                getId(), getNome(), tipo, getPreco(), duracaoMinutos
        );
    }
}
