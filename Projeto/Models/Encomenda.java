package Projeto.Models;

import Projeto.Exceptions.PrazoExpiradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encomenda {
    public enum Dimensao {
        GRANDE, MEDIO, PEQUENO
    }

    public enum Estado {
        PENDENTE, FINALIZADA, EXPEDIDA, DEVOLVIDA
    }

    private String codigo;
    private List<Artigo> artigos;
    private Dimensao dimensao;
    private double precoFinal;
    private double custosExpedicao;
    private double taxaSatisfacaoServico;
    private Estado estado;
    private LocalDate dataCriacao;

    public Encomenda(String codigo, Dimensao dimensao) {
        this.codigo = codigo;
        this.artigos = new ArrayList<>();
        this.dimensao = dimensao;
        this.estado = Estado.PENDENTE;
        this.dataCriacao = LocalDate.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public double getCustosExpedicao() {
        return custosExpedicao;
    }

    public double getTaxaSatisfacaoServico() {
        return taxaSatisfacaoServico;
    }

    public void setTaxaSatisfacaoServico(double taxaSatisfacaoServico) {
        this.taxaSatisfacaoServico = taxaSatisfacaoServico;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void adicionarArtigo(Artigo artigo) {
        this.artigos.add(artigo);
        calcularPrecoFinal();
    }

    public void removerArtigo(Artigo artigo) {
        this.artigos.remove(artigo);
        calcularPrecoFinal();
    }

    public void calcularPrecoFinal() {
        double total = 0;
        this.taxaSatisfacaoServico = 0;
        for (Artigo artigo : artigos) {
            total += artigo.getPrecoFinal();
            if (artigo.isNovo()) {
                this.taxaSatisfacaoServico += 0.5;
            } else {
                this.taxaSatisfacaoServico += 0.25;
            }
        }
        total += this.custosExpedicao + this.taxaSatisfacaoServico;
        this.precoFinal = total;
    }

    public void finalizarEncomenda() {
        if (this.estado == Estado.PENDENTE) {
            this.estado = Estado.FINALIZADA;
        }
    }

    public void expedirEncomenda() {
        if (this.estado == Estado.FINALIZADA) {
            this.estado = Estado.EXPEDIDA;
        }
    }

    public void setCustosExpedicao(double custosExpedicao) {
        this.custosExpedicao = custosExpedicao;
        calcularPrecoFinal();
    }



    public boolean podeDevolver() {
        LocalDate prazoDevolucao = this.dataCriacao.plusDays(30);
        return LocalDate.now().isBefore(prazoDevolucao);
    }

    public void devolverEncomenda() throws PrazoExpiradoException{
        if (podeDevolver()) {
            this.estado = Estado.DEVOLVIDA;
        } else {
            throw new PrazoExpiradoException("O prazo para devolução expirou.");
        }
    }

}