package Projeto.Controllers;

import Projeto.Models.Artigo;
import Projeto.Models.Utilizador;

public class UtilizadorController {
    private Utilizador utilizador;

    public UtilizadorController(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public void adicionarArtigoVenda(Artigo artigo) {
        utilizador.adicionarArtigoVenda(artigo);
    }

    public void removerArtigoVenda(Artigo artigo) {
        utilizador.removerArtigoVenda(artigo);
    }

    public void adicionarArtigoComprado(Artigo artigo) {
        utilizador.adicionarArtigoComprado(artigo);
    }

    public void removerArtigoComprado(Artigo artigo) {
        utilizador.removerArtigoComprado(artigo);
    }

    public void adicionarVenda(Venda venda) {
        utilizador.adicionarVenda(venda);
    }

    public void removerVenda(Venda venda) {
        utilizador.removerVenda(venda);
    }
}
