package Projeto.Controllers;

import Projeto.Models.Artigo;
import Projeto.Models.Encomenda;

import java.util.ArrayList;
import java.util.List;

public class EncomendaController {
    private Encomenda encomendaAtual;

    public EncomendaController() {
        this.encomendaAtual = new Encomenda();
    }

    public void adicionarArtigo(Artigo artigo) {
        this.encomendaAtual.getArtigos().add(artigo);
    }

    public void removerArtigo(String codigoArtigo) {
        this.encomendaAtual.getArtigos().removeIf(artigo -> artigo.getCodigo().equals(codigoArtigo));
    }

    public List<Artigo> listarArtigos() {
        return new ArrayList<>(this.encomendaAtual.getArtigos());
    }

    public double getPrecoTotal() {
        return this.encomendaAtual.getArtigos().stream().mapToDouble(Artigo::getPrecoBase).sum();
    }

    public Encomenda getEncomendaAtual() {
        return encomendaAtual;
    }

    public void setEncomendaAtual(Encomenda encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }
}
