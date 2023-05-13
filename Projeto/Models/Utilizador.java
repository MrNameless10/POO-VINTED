package Projeto.Models;
import java.util.ArrayList;
import java.util.List;

public class Utilizador {
    private static int count = 1; // Counter to generate unique IDs for each user
    private int codigo;
    private String email;
    private String nome;
    private String morada;
    private String numeroFiscal;
    private List<Artigo> artigosVenda;
    private List<Artigo> artigosComprados;
    private List<Venda> vendas;

    public Utilizador(String email, String nome, String morada, String numeroFiscal) {
        this.codigo = count++;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numeroFiscal = numeroFiscal;
        this.artigosVenda = new ArrayList<>();
        this.artigosComprados = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getNumeroFiscal() {
        return numeroFiscal;
    }

    public List<Artigo> getArtigosVenda() {
        return artigosVenda;
    }

    public List<Artigo> getArtigosComprados() {
        return artigosComprados;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void adicionarArtigoVenda(Artigo artigo) {
        artigosVenda.add(artigo);
    }

    public void removerArtigoVenda(Artigo artigo) {
        artigosVenda.remove(artigo);
    }

    public void adicionarArtigoComprado(Artigo artigo) {
        artigosComprados.add(artigo);
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
    }

    public void removerArtigoComprado(Artigo artigo) {
        artigosComprados.remove(artigo);
    }

    public void removerVenda(Venda venda) {
        vendas.remove(venda);
    }

    public List<Artigo> obterArtigosDisponiveis() {
        List<Artigo> artigosDisponiveis = new ArrayList<>();
        for (Artigo artigo : artigosVenda) {
            if (!artigo.isVendido()) {
                artigosDisponiveis.add(artigo);
            }
        }
        return artigosDisponiveis;
    }

    public List<Venda> obterVendasPendentes() {
        List<Venda> vendasPendentes = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getEstado().equals("Pendente")) {
                vendasPendentes.add(venda);
            }
        }
        return vendasPendentes;
    }

    public double obterTotalVendas() {
        double totalVendas = 0.0;
        for (Venda venda : vendas) {
            totalVendas += venda.getValor();
        }
        return totalVendas;
    }


}