package Projeto.Models;

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

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
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
        this.premium = premium;
    }

    public double getValorizacaoAnual() {
        return valorizacaoAnual;
    }

    public void setValorizacaoAnual(double valorizacaoAnual) {
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



    @Override
    public boolean isPremium() {
        return false;
    }

    @Override
    public boolean isVendido() {
        return false;
    }
}