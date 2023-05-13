package Projeto.Models;

public abstract class Transportadora {
    private String codigo;
    private String nome;
    private double valorBasePequena;
    private double valorBaseMedia;
    private double valorBaseGrande;
    private double imposto;
    private boolean especializadaPremium;

    public Transportadora(String codigo, String nome, double valorBasePequena, double valorBaseMedia,
                          double valorBaseGrande, double imposto, boolean especializadaPremium) {
        this.codigo = codigo;
        this.nome = nome;
        this.valorBasePequena = valorBasePequena;
        this.valorBaseMedia = valorBaseMedia;
        this.valorBaseGrande = valorBaseGrande;
        this.imposto = imposto;
        this.especializadaPremium = especializadaPremium;
    }


    public double getValorBase(Encomenda.Dimensao dimensao) {
        switch (dimensao) {
            case PEQUENO:
                return valorBasePequena;
            case MEDIO:
                return valorBaseMedia;
            case GRANDE:
                return valorBaseGrande;
            default:
                throw new IllegalArgumentException("Dimensao desconhecida: " + dimensao);
        }
    }

    public boolean isEspecializadaPremium() {
        return especializadaPremium;
    }

    public abstract double calcularPrecoExpedicao(Artigo artigo);
}
