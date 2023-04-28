package Projeto;

import java.time.LocalDate;

public class Mala extends ArtigoBase {
    private String dimensao;
    private String material;
    private int anoColecao;
    private boolean premium;
    private double valorizacaoAnual;

    public Mala(String codigo, String descricao, String marca, double precoBase, String dimensao, String material, int anoColecao, boolean premium, double valorizacaoAnual) {
        super(codigo, descricao, marca, precoBase);
        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
        this.premium = premium;
        this.valorizacaoAnual = valorizacaoAnual;
    }

    public double getPrecoFinal() {
        if (premium) {
            int anosDesdeColecao = LocalDate.now().getYear() - anoColecao;
            double precoFinal = precoBase + (precoBase * valorizacaoAnual * anosDesdeColecao);
            return precoFinal;
        } else {
            return precoBase;
        }
    }
}