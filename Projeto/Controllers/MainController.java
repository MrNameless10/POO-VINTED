package Projeto.Controllers;
import Projeto.Exceptions.*;
import Projeto.Models.*;
import jdk.jshell.execution.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.Year;


public class MainController implements Serializable {
    private List<Utilizador> utilizadores;
    private List<Encomenda> encomendas;
    private List<Artigo> artigos;

    private List<Transportadora> transportadoras;

    private Utilizador utilizadorAtual;
    private Encomenda encomendaAtual;


    public MainController() {
        this.utilizadores = new ArrayList<>();
        this.encomendas = new ArrayList<>();
        this.artigos = new ArrayList<>();
    }

    public void adicionarUtilizador(String email, String nome, String morada, String numeroFiscal) throws UtilizadorExistenteException {
        boolean utilizadorExistente = utilizadores.stream()
                .anyMatch(utilizador -> utilizador.getEmail().equals(email));
        if(utilizadorExistente){
            throw new UtilizadorExistenteException("Utilizador com o email '" + email + "' já foi criado.");
        }
        Utilizador novoUtilizador = new Utilizador(email, nome, morada, numeroFiscal);
        utilizadores.add(novoUtilizador);
    }

    public Utilizador realizarLogin(String email) {
        for (Utilizador utilizador : utilizadores) {
            if (utilizador.getEmail().equals(email)) {
                utilizadorAtual = utilizador;
                return utilizador; // Login bem-sucedido, retorna o utilizador
            }
        }
        return null; // Login falhou, utilizador não encontrado ou senha incorreta
    }

    public void adicionarSapatilhaAoUtilizador(String descricao, String marca, double precoBase, boolean isNovo,
                                               double avaliacaoEstado , int numDonosAnteriores, int tamanho, boolean temAtacadores,
                                               String cor, Year dataLancamentoColecao, boolean isPremium, String transportadora) {

        Transportadora transportadoraAtual = null;
        for (Transportadora transportadoraItem : transportadoras) {
            if (transportadoraItem.getNome().equals(transportadora)) {
                transportadoraAtual = transportadoraItem;
                break;
            }
        }

        if (transportadoraAtual == null) {
            // handle the case where the transportadora is not found
            return;
        }


        Sapatilha novaSapatilha = new Sapatilha(descricao, marca, precoBase, isNovo,false, avaliacaoEstado, numDonosAnteriores, 0,tamanho, temAtacadores, cor, dataLancamentoColecao, isPremium, utilizadorAtual.getCodigo(), transportadoraAtual);
        artigos.add(novaSapatilha);
    }

    public void adicionarTShirtAoUtilizador(String descricao, String marca, double precoBase, boolean isNovo, int avaliacaoEstado, int numDonosAnteriores, String tamanho, String padrao, String transportadora) {

        Transportadora transportadoraAtual = null;
        for (Transportadora transportadoraItem : transportadoras) {
            if (transportadoraItem.getNome().equals(transportadora)) {
                transportadoraAtual = transportadoraItem;
                break;
            }
        }

        if (transportadoraAtual == null) {
            // handle the case where the transportadora is not found
            return;
        }

        TShirt novaTShirt = new TShirt(descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, 0, false, tamanho, padrao, utilizadorAtual.getCodigo(), transportadoraAtual);
        artigos.add(novaTShirt);
    }

    public void adicionarMalaAoUtilizador(String descricao, String marca, double precoBase, boolean isNovo, double avaliacaoEstado, int numDonosAnteriores, int dimensao, String material, int anoColecao, boolean isPremium, double valorizacaoAnual, String transportadora) {

        Transportadora transportadoraAtual = null;
        for (Transportadora transportadoraItem : transportadoras) {
            if (transportadoraItem.getNome().equals(transportadora)) {
                transportadoraAtual = transportadoraItem;
                break;
            }
        }

        if (transportadoraAtual == null) {
            // handle the case where the transportadora is not found
            return;
        }

        Mala novaMala = new Mala(descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, 0, false, dimensao, material, anoColecao, isPremium, valorizacaoAnual, utilizadorAtual.getCodigo(), transportadoraAtual);
        artigos.add(novaMala);
    }


    public List<String> listarArtigosDoUtilizador() {
        List<String> listaArtigos = new ArrayList<>();
        for (Artigo artigo : artigos) {
            if (artigo.getDono().equals(utilizadorAtual.getCodigo())) {
                listaArtigos.add(artigo.toString());
            }
        }
        return listaArtigos;
    }


    public void apagarArtigo(String codigoArtigo) {
        artigos.removeIf(artigo -> artigo.getCodigo().equals(codigoArtigo));
    }


    public List<String> listarSapatilhasDisponiveis() {
        List<String> sapatilhasDisponiveis = new ArrayList<>();

        for (Artigo artigo : artigos) {
            if (artigo instanceof Sapatilha && !artigo.isVendido()) {
                sapatilhasDisponiveis.add(artigo.toString());
            }
        }

        return sapatilhasDisponiveis;
    }

    public void adicionarArtigoEncomenda(String codigoArtigo) {
        for (Artigo artigo : artigos) {
            if (artigo.getCodigo().equals(codigoArtigo)) {
                encomendaAtual.adicionarArtigo(artigo);
                artigo.setisVendido(true);
                return;
            }
        }

    }

    public List<String> listarTshirtsDisponiveis() {
        List<String> tshirtsDisponiveis = new ArrayList<>();

        for (Artigo artigo : artigos) {
            if (artigo instanceof TShirt && !artigo.isVendido()) {
                tshirtsDisponiveis.add(artigo.toString());
            }
        }

        return tshirtsDisponiveis;
    }

    public List<String> listarMalasDisponiveis() {
        List<String> malasDisponiveis = new ArrayList<>();

        for (Artigo artigo : artigos) {
            if (artigo instanceof Mala && !artigo.isVendido()) {
                malasDisponiveis.add(artigo.toString());
            }
        }

        return malasDisponiveis;
    }


    public List<String> listarTransportadoraDisponiveis() {
        List<String> transportadorasDisponiveis = new ArrayList<>();

        for (Transportadora transportadora : transportadoras) {
            transportadorasDisponiveis.add(transportadora.toString());
        }
        return transportadorasDisponiveis;
    }

    public double getPrecoTotalEncomenda() {
        if (encomendaAtual == null) {
            return 0;
        }

        double totalPrecoArtigos = 0;

        for (Artigo artigo : encomendaAtual.getArtigos()) {
            totalPrecoArtigos += artigo.getPrecoFinal() + artigo.getTransportadora().calcularCustoExpedicao(encomendaA);
        }

        Transportadora transportadora = encomendaAtual.getTransportadora();
        double custoExpedicao = transportadora.calcularCustoExpedicao(encomendaAtual);

        return totalPrecoArtigos + custoExpedicao;
    }



}