package Projeto.Controllers;

import Projeto.Models.*;
import java.util.ArrayList;
import java.util.List;

public class ArtigoController {
    private List<Artigo> artigos;
    private EncomendaController encomendaController;


    public ArtigoController(EncomendaController encomendaController) {
        this.artigos = new ArrayList<>();
        this.encomendaController = encomendaController;
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

    public void apagarArtigo(Artigo artigo) {
        this.artigos.remove(artigo);
    }

    public void adicionarArtigoEncomenda(String codigoArtigo) {
        Artigo artigo = obterArtigoPorCodigo(codigoArtigo);
        if (artigo != null && !artigo.isVendido()) {
            this.encomendaController.adicionarArtigo(artigo);
            artigo.setisVendido(true);
        }
    }

    public List<Sapatilha> obterSapatilhasDisponiveis() {
        return this.artigos.stream()
                .filter(artigo -> !artigo.isVendido() && artigo instanceof Sapatilha)
                .map(artigo -> (Sapatilha) artigo)
                .toList();
    }

    public List<TShirt> obterTshirtsDisponiveis() {
        return this.artigos.stream()
                .filter(artigo -> !artigo.isVendido() && artigo instanceof TShirt)
                .map(artigo -> (TShirt) artigo)
                .toList();
    }

    public List<Mala> obterMalasDisponiveis() {
        return this.artigos.stream()
                .filter(artigo -> !artigo.isVendido() && artigo instanceof Mala)
                .map(artigo -> (Mala) artigo)
                .toList();
    }




}
