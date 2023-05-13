package Projeto.Models;

public class Venda {
    private static int count = 1; // Counter to generate unique IDs for each sale
    private int id;
    private Utilizador vendedor;
    private Utilizador comprador;
    private Artigo artigo;
    private double valor;
    private String estado;

    public Venda(Utilizador vendedor, Utilizador comprador, Artigo artigo, double valor) {
        this.id = count++;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.artigo = artigo;
        this.valor = valor;
        this.estado = "Pendente";
    }

    public int getId() {
        return id;
    }

    public Utilizador getVendedor() {
        return vendedor;
    }

    public Utilizador getComprador() {
        return comprador;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public double getValor() {
        return valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setValor(double valor) {
        this.valor = valor;
        
    }
}
