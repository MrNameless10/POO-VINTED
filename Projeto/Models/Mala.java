package Projeto.Models;

import java.time.LocalDate;

public class Mala extends ArtigoBase {
    private int dimensao;
    private String material;
    private int anoColecao;

    private boolean isPremium;
    private double valorizacaoAnual;

    public Mala(String codigo, String descricao, String marca, double precoBase, boolean isNovo, double avaliacaoEstado, int numDonosAnteriores, double desconto, boolean isVendido, int dimensao, String material, int anoColecao, boolean isPremium, double valorizacaoAnual) {
        super(codigo, descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, desconto, isVendido);
        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
        this.isPremium = isPremium;
        this.valorizacaoAnual = valorizacaoAnual;
    }


    public double getPrecoFinal() {
        if (isPremium) {
            int anosDesdeColecao = LocalDate.now().getYear() - anoColecao;
            double precoFinal = precoBase + (precoBase * valorizacaoAnual * anosDesdeColecao);
            return precoFinal;
        } else {
            return precoBase;
        }
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAnoColecao() {
        return anoColecao;
    }

    public void setAnoColecao(int anoColecao) {
        this.anoColecao = anoColecao;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public double getValorizacaoAnual() {
        return valorizacaoAnual;
    }

    public void setValorizacaoAnual(double valorizacaoAnual) {
        this.valorizacaoAnual = valorizacaoAnual;
    }

    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public boolean isVendido() {
        return false;
    }
}