package Projeto.Models;

import java.time.Year;

public class Sapatilha extends ArtigoBase {
    private int tamanho;
    private boolean temAtacadores;
    private String cor;
    private Year dataLancamentoColecao;
    private boolean isPremium;

    public Sapatilha(String codigo, String descricao, String marca, double precoBase, boolean isNovo, double avaliacaoEstado, int numDonosAnteriores, double desconto, int tamanho, boolean temAtacadores, String cor, Year dataLancamentoColecao, boolean isPremium) {
        super(codigo, descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, desconto);
        this.tamanho = tamanho;
        this.temAtacadores = temAtacadores;
        this.cor = cor;
        this.dataLancamentoColecao = dataLancamentoColecao;
        this.isPremium = isPremium;
    }

    @Override
    public double getPrecoFinal() {
        if (isPremium) {
            // Se for premium, o valor aumenta com o passar dos anos
            int anosDesdeLancamento = Year.now().getValue() - dataLancamentoColecao.getValue();
            return precoBase * (1 + 0.05 * anosDesdeLancamento);
        } else if (!isNovo || tamanho > 45) {
            // Se for usado ou novo com tamanho acima de 45, aplica a fórmula
            return precoBase - (precoBase / numDonosAnteriores * avaliacaoEstado);
        } else {
            // Se for novo e tamanho não acima de 45, retorna o preço base
            return precoBase;
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isTemAtacadores() {
        return temAtacadores;
    }

    public void setTemAtacadores(boolean temAtacadores) {
        this.temAtacadores = temAtacadores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Year getDataLancamentoColecao() {
        return dataLancamentoColecao;
    }

    public void setDataLancamentoColecao(Year dataLancamentoColecao) {
        this.dataLancamentoColecao = dataLancamentoColecao;
    }

    @Override
    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
