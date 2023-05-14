package Projeto.Models;

import java.time.Year;

public class TShirt extends ArtigoBase {
    public enum Tamanho {
        S, M, L, XL
    }

    public enum Padrao {
        LISO, RISCAS, PALMEIRAS
    }

    private Tamanho tamanho;
    private Padrao padrao;

    public TShirt(String codigo, String descricao, String marca, double precoBase, boolean isNovo, int avaliacaoEstado,
                  int numDonosAnteriores, double desconto, boolean isVendido, Tamanho tamanho, Padrao padrao, String dono) {
        super(codigo, descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, desconto, isVendido, dono);
        this.tamanho = tamanho;
        this.padrao = padrao;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    @Override
    public double getPrecoFinal() {
        if (!isNovo && padrao != Padrao.LISO) {
            return precoBase * 0.5;
        } else {
            return precoBase;
        }
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", precoBase=" + precoBase +
                ", isNovo=" + isNovo +
                ", avaliacaoEstado=" + avaliacaoEstado +
                ", numDonosAnteriores=" + numDonosAnteriores +
                ", desconto=" + desconto +
                ", isVendido=" + isVendido +
                ", tamanho=" + tamanho +
                ", padrao=" + padrao +
                '}';
    }
}