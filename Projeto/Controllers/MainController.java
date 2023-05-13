package Projeto.Controllers;

import Projeto.Models.*;
import java.io.*;
import java.util.*;

public class MainController {
    private UtilizadorController utilizadorController;
    private ArtigoController artigoController;
    private VendaController vendaController;
    private TransportadoraController transportadoraController;

    public MainController() {
        this.utilizadorController = new UtilizadorController(new Utilizador("email@example.com", "Nome", "Morada", "123456789"));
        this.artigoController = new ArtigoController(new Artigo("Artigo1", "Descrição do Artigo", 20, "Categoria", false));
        this.vendaController = new VendaController(new Venda(1, "Pendente", 20));
        this.transportadoraController = new TransportadoraController(new Transportadora("Transportadora1", 10, 15, 20, 0.2, 1.5, true));
    }

    public UtilizadorController getUtilizadorController() {
        return this.utilizadorController;
    }

    public ArtigoController getArtigoController() {
        return this.artigoController;
    }

    public VendaController getVendaController() {
        return this.vendaController;
    }

    public TransportadoraController getTransportadoraController() {
        return this.transportadoraController;
    }

    public void start() {

    }
        public void simular(String caminhoFicheiro) {
            try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoFicheiro))) {
                String linha;
                while ((linha = leitor.readLine()) != null) {
                    String[] partes = linha.split(",\\s*");
                    String data = partes[0];
                    String utilizador = partes[1];
                    String artigo = partes[2];
                    String acao = partes[3];

                    switch (acao) {
                        case "comprar":
                            double valor = Double.parseDouble(partes[4]);
                            // O método 'comprar' deve ser implementado no UtilizadorController
                            this.utilizadorController.comprar(utilizador, artigo, valor);
                            break;
                        case "alteraValorValorizacao":
                            double novoValor = Double.parseDouble(partes[4]);
                            // O método 'alteraValorValorizacao' deve ser implementado no ArtigoController
                            this.artigoController.alteraValorValorizacao(artigo, novoValor);
                            break;
                        // ... (outras ações possíveis)
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
            }
        }
}


