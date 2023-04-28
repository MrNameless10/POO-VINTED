package Projeto;

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

    public double getPrecoFinal() {
        double precoFinal = precoBase - (precoBase / numeroDonos * estadoUtilizacao);
        return precoFinal;
    }
}