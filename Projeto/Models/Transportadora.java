package Projeto.Models;

public class Transportadora {
    private String nome;
    private double valorBaseExpedicaoPequena;
    private double valorBaseExpedicaoMedia;
    private double valorBaseExpedicaoGrande;
    private double factorImpostos;
    private double margemLucro;
    private boolean transportadoraPremium;

    public Transportadora(String nome, double valorBaseExpedicaoPequena, double valorBaseExpedicaoMedia,
                          double valorBaseExpedicaoGrande, double factorImpostos, double margemLucro,
                          boolean transportadoraPremium) {
        this.nome = nome;
        this.valorBaseExpedicaoPequena = valorBaseExpedicaoPequena;
        this.valorBaseExpedicaoMedia = valorBaseExpedicaoMedia;
        this.valorBaseExpedicaoGrande = valorBaseExpedicaoGrande;
        this.factorImpostos = factorImpostos;
        this.margemLucro = margemLucro;
        this.transportadoraPremium = transportadoraPremium;
    }

    public String getNome() {
        return nome;
    }

    public double getValorBaseExpedicaoPequena() {
        return valorBaseExpedicaoPequena;
    }

    public double getValorBaseExpedicaoMedia() {
        return valorBaseExpedicaoMedia;
    }

    public double getValorBaseExpedicaoGrande() {
        return valorBaseExpedicaoGrande;
    }

    public double getFactorImpostos() {
        return factorImpostos;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public boolean isTransportadoraPremium() {
        return transportadoraPremium;
    }

    public double calcularPrecoExpedicao(int numArtigos, boolean isPremium) {
        double valorBaseExpedicao;
        if (numArtigos == 1) {
            valorBaseExpedicao = valorBaseExpedicaoPequena;
        } else if (numArtigos >= 2 && numArtigos <= 5) {
            valorBaseExpedicao = valorBaseExpedicaoMedia;
        } else {
            valorBaseExpedicao = valorBaseExpedicaoGrande;
        }

        double precoExpedicao = valorBaseExpedicao * margemLucro * (1 + factorImpostos);
        if (isPremium && transportadoraPremium) {
            precoExpedicao *= 0.9;
        }
        return precoExpedicao;
    }
}
