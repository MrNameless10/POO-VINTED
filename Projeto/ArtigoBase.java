package Projeto;

public abstract class ArtigoBase implements Artigo {
    protected String codigo;
    protected String descricao;
    protected String marca;
    protected double precoBase;

    public ArtigoBase(String codigo, String descricao, String marca, double precoBase) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.marca = marca;
        this.precoBase = precoBase;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}