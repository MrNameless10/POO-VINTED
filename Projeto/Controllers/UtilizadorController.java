package Projeto.Controllers;

import Projeto.Models.Artigo;
import Projeto.Models.Utilizador;
import Projeto.Models.Encomenda;

import java.util.*;
import java.util.stream.Collectors;

public class UtilizadorController {
    private Map<Utilizador, List<Artigo>> utilizadoresEArtigos;
    private Map<Utilizador, List<Encomenda>> utilizadoresEEncomendas;
    private Utilizador utilizadorAtual;

    public UtilizadorController() {
        this.utilizadoresEArtigos = new HashMap<>();
        this.utilizadoresEEncomendas = new HashMap<>();
    }

    public void adicionarUtilizador(String email, String nome, String morada, String numeroFiscal) {
        Utilizador novoUtilizador = new Utilizador(email, nome, morada, numeroFiscal);
        this.utilizadoresEArtigos.put(novoUtilizador, new ArrayList<>());
    }

    public List<Artigo> obterArtigosDoUtilizador(Utilizador utilizador) {
        return this.utilizadoresEArtigos.get(utilizador);
    }

    public void adicionarArtigoAoUtilizador(Utilizador utilizador, Artigo artigo) {
        List<Artigo> artigosDoUtilizador = this.utilizadoresEArtigos.get(utilizador);
        if (artigosDoUtilizador != null) {
            artigosDoUtilizador.add(artigo);
        }
    }

    public void removerArtigoDoUtilizador(Utilizador utilizador, Artigo artigo) {
        List<Artigo> artigosDoUtilizador = this.utilizadoresEArtigos.get(utilizador);
        if (artigosDoUtilizador != null) {
            artigosDoUtilizador.remove(artigo);
        }
    }

    public List<Utilizador> obterUtilizadores() {
        return new ArrayList<>(utilizadoresEArtigos.keySet());
    }

    public Utilizador obterUtilizadorPorEmail(String email) {
        return this.obterUtilizadores().stream()
                .filter(utilizador -> utilizador.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public boolean verificarUtilizador(String email) {
        return this.obterUtilizadores().stream()
                .anyMatch(utilizador -> utilizador.getEmail().equalsIgnoreCase(email));
    }

    public Utilizador getUtilizadorAtual() {
        return utilizadorAtual;
    }

    public void setUtilizadorAtual(Utilizador utilizadorAtual) {
        this.utilizadorAtual = utilizadorAtual;
    }

    public Utilizador getUtilizadorQueMaisFaturou() {
        return utilizadoresEArtigos.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().stream()
                        .mapToDouble(Artigo::getPrecoBase).sum()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Encomenda> listarEncomendasDeUtilizador(Utilizador utilizador) {
        return utilizadoresEEncomendas.getOrDefault(utilizador, new ArrayList<>());
    }

    public List<Utilizador> ordenarUtilizadoresPorVendas() {
        return utilizadoresEArtigos.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(List::size)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void listarArtigosDoUtilizador(Utilizador utilizador) {
        List<Artigo> artigosDoUtilizador = utilizadoresEArtigos.getOrDefault(utilizador, new ArrayList<>());
        System.out.println("\n--- Artigos do Utilizador ---");
        if (artigosDoUtilizador.isEmpty()) {
            System.out.println("O utilizador não possui artigos.");
        } else {
            for (Artigo artigo : artigosDoUtilizador) {
                System.out.println(artigo.toString());
            }
        }
    }

}
