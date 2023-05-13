package Projeto.Controllers;

import Projeto.Models.Artigo;
import Projeto.Models.Encomenda;
import Projeto.Models.Transportadora;

import java.util.List;

public class EncomendaController {
    public Encomenda criarEncomenda(List<Artigo> artigos, String dimensaoEmbalagem, Transportadora transportadora) {
        Encomenda encomenda = new Encomenda(artigos, dimensaoEmbalagem, transportadora);
        encomenda.calcularPrecoFinal();
        encomenda.calcularCustosExpedicao();
        return encomenda;
    }

    public void adicionarArtigoEncomenda(Encomenda encomenda, Artigo artigo) {
        encomenda.adicionarArtigo(artigo);
        encomenda.calcularPrecoFinal();
        encomenda.calcularCustosExpedicao();
    }

    public void removerArtigoEncomenda(Encomenda encomenda, Artigo artigo) {
        encomenda.removerArtigo(artigo);
        encomenda.calcularPrecoFinal();
        encomenda.calcularCustosExpedicao();
    }

    public void finalizarEncomenda(Encomenda encomenda) {
        encomenda.finalizarEncomenda();
    }

    public void expedirEncomenda(Encomenda encomenda) {
        encomenda.expedirEncomenda();
    }

    public void devolverEncomenda(Encomenda encomenda, int prazoDevolucao) {
        encomenda.devolverEncomenda(prazoDevolucao);
    }
}
