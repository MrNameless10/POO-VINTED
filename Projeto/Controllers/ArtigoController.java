package Projeto.Controllers;

import Projeto.Models.Artigo;
import java.util.ArrayList;
import java.util.List;

public class ArtigoController {
    private List<Artigo> artigos;

    public ArtigoController() {
        this.artigos = new ArrayList<>();
    }

    public void criarArtigo(Artigo artigo) {
        this.artigos.add(artigo);
    }

    public List<Artigo> obterArtigos() {
        return this.artigos;
    }

    public List<Artigo> obterArtigosDisponiveis() {
        return this.artigos.stream()
                .filter(artigo -> !artigo.isVendido())
                .toList();
    }

    public List<Artigo> obterArtigosPorMarca(String marca) {
        return this.artigos.stream()
                .filter(artigo -> artigo.getMarca().equalsIgnoreCase(marca))
                .toList();
    }

    public void venderArtigo(String codigoArtigo) {
        Artigo artigo = obterArtigoPorCodigo(codigoArtigo);
        if (artigo != null && !artigo.isVendido()) {
            artigo.setisVendido(true);
        }
    }

    public Artigo obterArtigoPorCodigo(String codigoArtigo) {
        return this.artigos.stream()
                .filter(artigo -> artigo.getCodigo().equalsIgnoreCase(codigoArtigo))
                .findFirst()
                .orElse(null);
    }
}
