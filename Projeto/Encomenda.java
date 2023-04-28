package Projeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encomenda {
    private static int count = 1; // Counter to generate unique IDs for each order
    private int id;
    private LocalDate dataCriacao;
    private List<Artigo> artigos;
    private String dimensaoEmbalagem;
    private double precoFinal;
    private double taxaSatisfacao;
    private double custosExpedicao;
    private String estado;
    private Transportadora transportadora;

    public Encomenda(List<Artigo> artigos, String dimensaoEmbalagem, Transportadora transportadora) {
        this.id = count++;
        this.dataCriacao = LocalDate.now();
        this.artigos = new ArrayList<>(artigos);
        this.dimensaoEmbalagem = dimensaoEmbalagem;
        this.precoFinal = 0.0;
        this.taxaSatisfacao = 0.0;
        this.custosExpedicao = 0.0;
        this.estado = "Pendente";
        this.transportadora = transportadora;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public String getDimensaoEmbalagem() {
        return dimensaoEmbalagem;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public double getTaxaSatisfacao() {
        return taxaSatisfacao;
    }

    public double getCustosExpedicao() {
        return custosExpedicao;
    }

    public String getEstado() {
        return estado;
    }

    public void adicionarArtigo(Artigo artigo) {
        artigos.add(artigo);
    }

    public void removerArtigo(Artigo artigo) {
        artigos.remove(artigo);
    }

    public void calcularPrecoFinal() {
        double total = 0.0;
        for (Artigo artigo : artigos) {
            total += artigo.getPrecoFinal();
        }
        this.precoFinal = total + (0.5 * artigos.size()); // Add service satisfaction fee per new article
    }

    public void calcularCustosExpedicao() {
        int numArtigos = artigos.size();
        boolean isPremium = false;
        for (Artigo artigo : artigos) {
            if (artigo.isPremium()) {
                isPremium = true;
                break;
            }
        }

        this.custosExpedicao = transportadora.calcularPrecoExpedicao(numArtigos, isPremium);
    }

    public void finalizarEncomenda() {
        this.estado = "Finalizada";
    }

    public void expedirEncomenda() {
        this.estado = "Expedida";
    }

    public void devolverEncomenda(int prazoDevolucao) {
        LocalDate dataEntrega = dataCriacao.plusDays(prazoDevolucao);
        if (LocalDate.now().isBefore(dataEntrega)) {
            this.estado = "Devolvida";
        } else {
            System.out.println("O prazo para devolução expirou.");
        }
    }

}