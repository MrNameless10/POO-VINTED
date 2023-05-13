package Projeto.Models;

public interface Artigo {
    String getCodigo();
    String getDescricao();
    String getMarca();
    double getPrecoBase();
    double getPrecoFinal();
    boolean isPremium();

    boolean isVendido();
}
