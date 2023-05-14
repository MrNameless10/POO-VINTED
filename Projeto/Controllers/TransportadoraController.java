package Projeto.Controllers;

import Projeto.Models.Transportadora;
import Projeto.Models.Encomenda;

import java.util.*;

public class TransportadoraController {
    private Map<Transportadora, List<Encomenda>> transportadorasEEncomendas;

    public TransportadoraController() {
        this.transportadorasEEncomendas = new HashMap<>();
    }

    public void adicionarTransportadora(String nome, double valorBasePequeno, double valorBaseMedio,
                                        double valorBaseGrande, double margemLucro, boolean isPremium) {
        Transportadora novaTransportadora = new Transportadora(nome, valorBasePequeno, valorBaseMedio,
                valorBaseGrande, margemLucro, isPremium);
        this.transportadorasEEncomendas.put(novaTransportadora, new ArrayList<>());
    }

    public List<Encomenda> obterEncomendasDaTransportadora(Transportadora transportadora) {
        return this.transportadorasEEncomendas.get(transportadora);
    }

    public void adicionarEncomendaATransportadora(Transportadora transportadora, Encomenda encomenda) {
        List<Encomenda> encomendasDaTransportadora = this.transportadorasEEncomendas.get(transportadora);
        if (encomendasDaTransportadora != null) {
            encomendasDaTransportadora.add(encomenda);
        }
    }

    public List<Transportadora> obterTransportadoras() {
        return new ArrayList<>(transportadorasEEncomendas.keySet());
    }

    public Transportadora getTransportadoraComMaiorVolumeDeFaturacao() {
        return transportadorasEEncomendas.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().stream()
                        .mapToDouble(encomenda -> entry.getKey().calcularCustoExpedicao(encomenda)).sum()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
