package Projeto.Models;

public class CTT extends Transportadora {
    private double margemLucro;

    public CTT(String codigo, String nome, double valorBasePequena, double valorBaseMedia,
               double valorBaseGrande, double imposto, boolean especializadaPremium,
               double margemLucro) {
        super(codigo, nome, valorBasePequena, valorBaseMedia, valorBaseGrande, imposto, especializadaPremium);
        this.margemLucro = margemLucro;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }


    @Override
    public double calcularPrecoExpedicao(Encomenda encomenda) {
        double valorBase;
        int numArtigos = encomenda.getArtigos().size();

        if (numArtigos == 1) {
            valorBase = 5.0;  // Valor base para encomendas pequenas
        } else if (numArtigos >= 2 && numArtigos <= 5) {
            valorBase = 10.0;  // Valor base para encomendas médias
        } else {
            valorBase = 15.0;  // Valor base para encomendas grandes
        }

        double imposto = 0.2;  // Valor do imposto definido pelo sistema

        // Fórmula de cálculo do preço
        return (valorBase * margemLucro * (1 + imposto)) * 0.9;
    }
}
