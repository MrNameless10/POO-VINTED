package Projeto.Controllers;

import Projeto.Models.Utilizador;
import Projeto.Models.Artigo;
import Projeto.Models.Encomenda;
import Projeto.Models.Transportadora;

import java.util.HashMap;
import java.util.Map;

public class VintageController {
    private UtilizadorController utilizadorController;
    private TransportadoraController transportadoraController;
    private static double dinheiroGanho;

    public VintageController() {
        this.utilizadorController = new UtilizadorController();
        this.transportadoraController = new TransportadoraController();
        dinheiroGanho = 0;
    }

    public static double getDinheiroGanho() {
        return dinheiroGanho;
    }

    public static void adicionarDinheiroGanho(double valor) {
        dinheiroGanho += valor;
    }

    public UtilizadorController getUtilizadorController() {
        return utilizadorController;
    }

    public TransportadoraController getTransportadoraController() {
        return transportadoraController;
    }
}





