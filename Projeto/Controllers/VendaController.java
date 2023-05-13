package Projeto.Controllers;

import Projeto.Models.Venda;

public class VendaController {
    private Venda venda;

    public VendaController(Venda venda) {
        this.venda = venda;
    }

    public Venda getVenda() {
        return this.venda;
    }

    public void setEstado(String estado) {
        this.venda.setEstado(estado);
    }

    public void setValor(double valor) {
        this.venda.setValor(valor);
    }

    public String getEstado() {
        return this.venda.getEstado();
    }

    public double getValor() {
        return this.venda.getValor();
    }
}
