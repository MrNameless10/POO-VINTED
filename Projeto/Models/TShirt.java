package Projeto.Models;

public class TShirt extends ArtigoBase {
    private String tamanho;
    private String padrao;
    private boolean usada;

    public TShirt(String codigo, String descricao, String marca, double precoBase, String tamanho, String padrao, boolean usada) {
        super(codigo, descricao, marca, precoBase);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.usada = usada;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getPadrao() {
        return padrao;
    }

    public void setPadrao(String padrao) {
        this.padrao = padrao;
    }

    public boolean isUsada() {
        return usada;
    }

    public void setUsada(boolean usada) {
        this.usada = usada;
    }

    public double getPrecoFinal() {
        if (!padrao.equals("liso") && usada) {
            return precoBase * 0.5;
        } else {
            return precoBase;
        }
    }
}