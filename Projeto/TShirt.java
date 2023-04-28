package Projeto;

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

    public double getPrecoFinal() {
        if (!padrao.equals("liso") && usada) {
            return precoBase * 0.5;
        } else {
            return precoBase;
        }
    }
}