package Projeto.Models;

public class Sapatilha extends ArtigoBase {
    private int tamanho;
    private boolean possuiAtacadores;
    private String cor;
    private int anoLancamento;
    private int numeroDonos;
    private double estadoUtilizacao;

    public Sapatilha(String codigo, String descricao, String marca, double precoBase, int tamanho, boolean possuiAtacadores, String cor, int anoLancamento, int numeroDonos, double estadoUtilizacao) {
        super(codigo, descricao, marca, precoBase);
        this.tamanho = tamanho;
        this.possuiAtacadores = possuiAtacadores;
        this.cor = cor;
        this.anoLancamento = anoLancamento;
        this.numeroDonos = numeroDonos;
        this.estadoUtilizacao = estadoUtilizacao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isPossuiAtacadores() {
        return possuiAtacadores;
    }

    public void setPossuiAtacadores(boolean possuiAtacadores) {
        this.possuiAtacadores = possuiAtacadores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getNumeroDonos() {
        return numeroDonos;
    }

    public void setNumeroDonos(int numeroDonos) {
        this.numeroDonos = numeroDonos;
    }

    public double getEstadoUtilizacao() {
        return estadoUtilizacao;
    }

    public void setEstadoUtilizacao(double estadoUtilizacao) {
        this.estadoUtilizacao = estadoUtilizacao;
    }

    public double getPrecoFinal() {
        double precoFinal = precoBase - (precoBase / numeroDonos * estadoUtilizacao);
        return precoFinal;
    }


}
