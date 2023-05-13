package Projeto.Controllers;
import Projeto.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportadoraController {
    private List<Transportadora> transportadoras;

    public TransportadoraController() {
        this.transportadoras = new ArrayList<>();
    }

    public void adicionarTransportadora(Transportadora transportadora) {
        transportadoras.add(transportadora);
    }

    public void removerTransportadora(Transportadora transportadora) {
        transportadoras.remove(transportadora);
    }

    public Transportadora encontrarTransportadoraPorNome(String nome) {
        for (Transportadora transportadora : transportadoras) {
            if (transportadora.getNome().equalsIgnoreCase(nome)) {
                return transportadora;
            }
        }
        return null;
    }

    public Transportadora criarTransportadora() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Criar Transportadora");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Valor Base Pequeno: ");
        double valorBasePequeno = scanner.nextDouble();

        System.out.print("Valor Base Médio: ");
        double valorBaseMedio = scanner.nextDouble();

        System.out.print("Valor Base Grande: ");
        double valorBaseGrande = scanner.nextDouble();

        System.out.print("Margem de Lucro: ");
        double margemLucro = scanner.nextDouble();

        return new Transportadora(nome, valorBasePequeno, valorBaseMedio, valorBaseGrande, margemLucro);
    }

    public double calcularPrecoExpedicao(Transportadora transportadora, Encomenda encomenda) {
        if (transportadora == null) {
            throw new IllegalArgumentException("Transportadora inválida.");
        }
        return transportadora.calcularPrecoExpedicao(encomenda);
    }


}
