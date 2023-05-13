package Projeto.Controllers;

import Projeto.Models.Transportadora;

public class TransportadoraController {
    private Transportadora transportadora;

    public TransportadoraController(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public String getNomeTransportadora() {
        return this.transportadora.getNome();
    }

    public double getValorBaseExpedicaoPequena() {
        return this.transportadora.getValorBaseExpedicaoPequena();
    }

    public double getValorBaseExpedicaoMedia() {
        return this.transportadora.getValorBaseExpedicaoMedia();
    }

    public double getValorBaseExpedicaoGrande() {
        return this.transportadora.getValorBaseExpedicaoGrande();
    }

    public double getFactorImpostos() {
        return this.transportadora.getFactorImpostos();
    }

    public double getMargemLucro() {
        return this.transportadora.getMargemLucro();
    }

    public boolean isTransportadoraPremium() {
        return this.transportadora.isTransportadoraPremium();
    }

    public double calcularPrecoExpedicao(int numArtigos, boolean isPremium) {
        return this.transportadora.calcularPrecoExpedicao(numArtigos, isPremium);
    }
}
