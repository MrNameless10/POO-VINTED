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

    public TShirt(String descricao, String marca, double precoBase, boolean isNovo, int avaliacaoEstado,
                  int numDonosAnteriores, double desconto, boolean isVendido, String tamanho, String padrao, String dono, Transportadora transportadora) {
        super(descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, desconto, isVendido, dono, transportadora);

        this.tamanho = Tamanho.valueOf(tamanho.toUpperCase());
        this.padrao = Padrao.valueOf(padrao.toUpperCase());

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
                "tamanho=" + tamanho +
                ", padrao=" + padrao +
                ", descricao='" + descricao + '\'' +
                ", marca='" + marca + '\'' +
                ", precoBase=" + precoBase +
                ", isNovo=" + isNovo +
                ", avaliacaoEstado=" + avaliacaoEstado +
                ", numDonosAnteriores=" + numDonosAnteriores +
                ", desconto=" + desconto +
                ", isVendido=" + isVendido +
                ", dono='" + dono + '\'' +
                '}';
    }
}