package Projeto.View;
import Projeto.Controllers.*;

import java.util.List;
import java.util.Scanner;

import java.time.Year;


public class Menu {
    private enum State {
        INICIAL, LOGIN, REGISTAR, PRINCIPAL, GERIR,
        ADICIONAR, APAGAR, COMPRAR, VER_ENCOMENDA, FINALIZAR,
        ESTATISTICAS, AVANCAR, SAIR
    }

    ;

    private final Scanner scanner;
    private State state = State.INICIAL;

    private UtilizadorController utilizadorController;
    private ArtigoController artigoController;

    private EncomendaController encomendaController;

    private TransportadoraController transportadoraController;

    private MainController mainController;

    private int count = 0; // Inicializa a variável count com zero

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.utilizadorController = new UtilizadorController();
        this.encomendaController = new EncomendaController();
        this.artigoController = new ArtigoController(this.encomendaController);
        this.transportadoraController = new TransportadoraController();
        this.encomendaController.criarEncomenda("someCodigo");
        this.mainController = new MainController();
    }


    public void run() {
        while (this.state != State.SAIR) {
            switch (this.state) {
                case INICIAL:
                    displayMenu();
                    break;
                case REGISTAR:
                    displayRegistarSubMenu();
                    break;
                case LOGIN:
                    displayLoginSubMenu();
                    break;
                case PRINCIPAL:
                    displayUtilizadorMenu();
                    break;
                case GERIR:
                    displayGerirArtigosSubMenu();
                    break;
                case ADICIONAR:
                    displayAdicionarArtigoSubMenu();
                    break;
                case APAGAR:
                    displayApagarArtigo();
                    break;
                case COMPRAR:
                    displayComprarArtigosSubMenu();
                    break;
                case VER_ENCOMENDA:
                    displayVerEncomendaSubMenu();
                    break;
                case FINALIZAR:
                    // TODO - Finalizar a encomenda no controller
                    break;
                case ESTATISTICAS:
                    displayEstatisticasSubMenu();
                    break;
                case AVANCAR:
                    // TODO - Avançar tempo no controller
                    break;
            }
        }
    }

    public void displayMenu() {
        System.out.println("\nMENU INICIAL - Vintage Marketplace");
        System.out.println("1. Login");
        System.out.println("2. Registar");
        System.out.println("3. Estatisticas");
        System.out.println("4. Avançar o tempo");
        System.out.println("5. Sair");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                this.state = State.LOGIN;
                break;
            case 2:
                this.state = State.REGISTAR;
                break;
            case 3:
                this.state = State.ESTATISTICAS;
                break;
            case 4:
                this.state = State.AVANCAR;
                break;
            case 5:
                this.state = State.SAIR;
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void displayRegistarSubMenu() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Registar ---");
        System.out.print("Enter the e-mail: ");
        String email = scanner.nextLine();

        try {
            System.out.print("Enter the nome: ");
            String nome = scanner.nextLine();
            System.out.print("Enter the morada: ");
            String morada = scanner.nextLine();
            System.out.print("Enter the número Fiscal: ");
            String nif = scanner.nextLine();


            mainController.adicionarUtilizador(email, nome, morada, nif);
            System.out.println("Utilizador criado com sucesso!");
        } catch (Exception e) {
            System.out.println("E-mail já existe!");
        }

        this.state = State.INICIAL;
    }


    public void displayLoginSubMenu() {

        try {
            System.out.print("Enter email: ");
            String emailLogin = scanner.next();

            mainController.realizarLogin(emailLogin);
            System.out.println("Login efetuado com sucesso!");
            this.state = State.PRINCIPAL;
        } catch (Exception e) {
            System.out.println("Email não encontrado.");
            this.state = State.INICIAL;
        }

    }

    public void displayUtilizadorMenu() {
        System.out.println("\nMENU UTILIZADOR - Vintage Marketplace");
        System.out.println("1. Gerir artigos");
        System.out.println("2. Comprar artigos");
        System.out.println("3. Ver encomenda");
        System.out.println("4. Finalizar encomenda");
        System.out.println("5. Voltar para o menu principal (Logout)");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                this.state = State.GERIR;
                break;
            case 2:
                this.state = State.COMPRAR;
                break;
            case 3:
                this.state = State.VER_ENCOMENDA;
                break;
            case 4:
                this.state = State.FINALIZAR;
                break;
            case 5:
                this.state = State.INICIAL;
                utilizadorController.setUtilizadorAtual(null);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void displayGerirArtigosSubMenu() {

        System.out.println("\n--- Gerir artigos ---");
        System.out.println("1. Adicionar");
        System.out.println("2. Apagar");
        System.out.println("3. Listar meus artigos");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consumes the newline left-over

        switch (input) {
            case 1:
                this.state = State.ADICIONAR;
                break;
            case 2:
                this.state = State.APAGAR;
                break;
            case 3:
                // Listar artigos do utilizador atual
                List<String> lista = mainController.listarArtigosDoUtilizador();
                for (String artigoString : lista) {
                    System.out.println(artigoString);
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }


    public void displayApagarArtigo() {
        System.out.println("\n--- Apagar artigos ---");
        List<String> lista = mainController.listarArtigosDoUtilizador();

        if (lista.isEmpty()) {
            System.out.println("A lista de artigos está vazia.");
        } else {
            for (String artigoString : lista) {
                System.out.println(artigoString);
            }

            boolean artigoExists = false;

            System.out.print("Enter the código do artigo a apagar: ");
            String codigoArtigo = scanner.nextLine();


            for (String artigoString : lista) {
                if (artigoString.contains(codigoArtigo)) {
                    artigoExists = true;
                    mainController.apagarArtigo(codigoArtigo);
                    System.out.println("Artigo com código '" + codigoArtigo + "' foi apagado.");
                    break;
                }
            }

            if (!artigoExists) {
                System.out.println("Artigo com código '" + codigoArtigo + "' não encontrado. Tente de novo.");
            }
        }


        this.state = State.PRINCIPAL;
    }


    public void displayVerEncomendaSubMenu() {
        System.out.println("\n--- Ver encomenda ---");
        System.out.println("1. Preço total");
        System.out.println("2. Ver artigos");
        System.out.println("3. Remover artigo");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consumes the newline left-over

        switch (input) {
            case 1:
                // Mostrar preço total (vindo do controller)
                double precoTotal = mainController.getPrecoTotal();
                System.out.println("Preço total da encomenda: " + precoTotal);
                break;
            case 2:
                // Listar artigos (vindo do controller)
                List<Artigo> artigos = encomendaController.listarArtigos();
                for (Artigo artigo : artigos) {
                    System.out.println(artigo);
                }
                break;
            case 3:
                // Listar artigos (vindo do controller)
                System.out.print("Enter the código do artigo a apagar: ");
                String codigoArtigo = scanner.nextLine();

                // Remover artigo da encomenda no controller
                boolean sucesso = encomendaController.removerArtigo(codigoArtigo);
                if (sucesso) {
                    System.out.println("Artigo removido com sucesso.");
                } else {
                    System.out.println("Artigo não encontrado.");
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        this.state = State.PRINCIPAL;
    }

    public void displayEstatisticasSubMenu() {
        System.out.println("\n--- Estatísticas ---");
        System.out.println("1. Utilizador que mais facturou");
        System.out.println("2. Transportadora com maior volume de facturação");
        System.out.println("3. Listar encomendas de um utilizador");
        System.out.println("4. Ordenação dos maiores compradores/vendedores");
        System.out.println("5. Dinheiro ganho pelo Vintage");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        switch (input) {
            case 1:
                System.out.println("Utilizador que mais facturou: " + utilizadorController.getUtilizadorQueMaisFaturou());
                break;
            case 2:
                System.out.println("Transportadora com maior volume de facturação: " + transportadoraController.getTransportadoraComMaiorVolumeDeFaturacao());
                break;
            case 3:
                System.out.print("Digite o email do utilizador: ");
                String emailUtilizador = scanner.nextLine();
                Utilizador utilizador = utilizadorController.obterUtilizadorPorEmail(emailUtilizador);
                if (utilizador != null) {
                    System.out.println("Encomendas do utilizador: " + utilizadorController.listarEncomendasDeUtilizador(utilizador));
                } else {
                    System.out.println("Utilizador não encontrado.");
                }
                break;
            case 4:
                System.out.println("Ordenação dos maiores compradores/vendedores: " + utilizadorController.ordenarUtilizadoresPorVendas());
                break;
            case 5:
                System.out.println("Dinheiro ganho pelo Vintage: " + VintageController.getDinheiroGanho());
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }


    public void displayAdicionarArtigoSubMenu() {

        System.out.println("\n--- Adicionar artigo ---");
        System.out.println("1. Sapatilhas");
        System.out.println("2. T-Shirts");
        System.out.println("3. Malas");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                createSapatilhasArtigo();
                break;
            case 2:
                createTShirtArtigo();
                break;
            case 3:
                createMalaArtigo();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        this.state = State.PRINCIPAL;
    }

    public void createSapatilhasArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Enter the marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter the preço base: ");
        double precoBase = scanner.nextDouble();
        System.out.print("Is it new? (true/false): ");
        boolean isNovo = scanner.nextBoolean();
        System.out.print("Enter the tamanho: ");
        int tamanho = scanner.nextInt();
        System.out.print("Does it have atacadores? (true/false): ");
        boolean temAtacadores = scanner.nextBoolean();
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the cor: ");
        String cor = scanner.nextLine();
        System.out.print("Enter the data de lançamento da coleção (YYYY): ");
        Year dataLancamentoColecao = Year.of(scanner.nextInt());
        System.out.print("Is it premium? (true/false): ");
        boolean isPremium = scanner.nextBoolean();

        List<String> transportadoras = mainController.listarTransportadoraDisponiveis();

        if (transportadoras.isEmpty()) {
            System.out.println("Não existem transportadoras disponíveis.");
        } else {
            for (String transportador : transportadoras) {
                System.out.println(transportador);
            }
            System.out.print("Enter o código da transportadora: ");
            String transportadora = scanner.nextLine();

            if (isNovo) {
                // Sapatilha nova
                mainController.adicionarSapatilhaAoUtilizador(descricao, marca, precoBase, isNovo, 0, 0, tamanho, temAtacadores, cor, dataLancamentoColecao, isPremium, transportadora);
            } else {
                // Sapatilha usada
                System.out.print("Enter the avaliação estado: ");
                double avaliacaoEstado = scanner.nextDouble();
                System.out.print("Enter the número de donos anteriores: ");
                int numDonosAnteriores = scanner.nextInt();
                mainController.adicionarSapatilhaAoUtilizador(descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, tamanho, temAtacadores, cor, dataLancamentoColecao, isPremium, transportadora);
            }
        }
    }


    public void createTShirtArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Enter the marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter the preço base: ");
        double precoBase = scanner.nextDouble();
        System.out.print("Is it new? (true/false): ");
        boolean isNovo = scanner.nextBoolean();
        System.out.print("Enter the tamanho (S, M, L, XL): ");
        String tamanho = scanner.nextLine();
        System.out.print("Enter the padrão (LISO, RISCAS, PALMEIRAS): ");
        String padrao = scanner.nextLine();


        List<String> transportadoras = mainController.listarTransportadoraDisponiveis();

        if (transportadoras.isEmpty()) {
            System.out.println("Não existem transportadoras disponíveis.");
        } else {
            for (String transportador : transportadoras) {
                System.out.println(transportador);
            }
            System.out.print("Enter o código da transportadora: ");
            String transportadora = scanner.nextLine();
            if (isNovo) {
                // Tshirt nova
                mainController.adicionarTShirtAoUtilizador(descricao, marca, precoBase, isNovo, 0, 0, tamanho, padrao, transportadora);
            } else {
                // Tshirt usada
                System.out.print("Enter the avaliação estado: ");
                double avaliacaoEstado = scanner.nextDouble();
                System.out.print("Enter the número de donos anteriores: ");
                int numDonosAnteriores = scanner.nextInt();
                mainController.adicionarTShirtAoUtilizador(descricao, marca, precoBase, isNovo, (int)avaliacaoEstado, numDonosAnteriores, tamanho, padrao, transportadora);
            }
        }
    }


    public void createMalaArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Enter the marca: ");
        String marca = scanner.nextLine();
        System.out.print("Enter the preço base: ");
        double precoBase = scanner.nextDouble();
        System.out.print("Enter the dimensão: ");
        int dimensao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the material: ");
        String material = scanner.nextLine();
        System.out.print("Enter the ano da coleção: ");
        int anoColecao = scanner.nextInt();
        System.out.print("Is it premium? (true/false): ");
        boolean isPremium = scanner.nextBoolean();
        System.out.print("Enter the valorização anual: ");
        double valorizacaoAnual = scanner.nextDouble();
        System.out.print("Is it new? (true/false): ");
        boolean isNovo = scanner.nextBoolean();

        List<String> transportadoras = mainController.listarTransportadoraDisponiveis();

        if (transportadoras.isEmpty()) {
            System.out.println("Não existem transportadoras disponíveis.");
        } else {
            for (String transportador : transportadoras) {
                System.out.println(transportador);
            }
            System.out.print("Enter o código da transportadora: ");
            String transportadora = scanner.nextLine();

            if (isNovo) {
                mainController.adicionarMalaAoUtilizador(descricao, marca, precoBase, isNovo, 0, 0, dimensao, material, anoColecao, isPremium, valorizacaoAnual, transportadora);
            } else {
                // Mala usada
                System.out.print("Enter the avaliação estado: ");
                double avaliacaoEstado = scanner.nextDouble();
                System.out.print("Enter the número de donos anteriores: ");
                int numDonosAnteriores = scanner.nextInt();
                mainController.adicionarMalaAoUtilizador(descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, dimensao, material, anoColecao, isPremium, valorizacaoAnual, transportadora);
            }
        }
    }





    public void displayComprarArtigosSubMenu() {
        System.out.println("\n--- Comprar artigos ---");
        System.out.println("1. Sapatilhas");
        System.out.println("2. T-Shirts");
        System.out.println("3. Malas");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                displayComprarSapatilhasArtigo();
                break;
            case 2:
                displayComprarTshirtArtigo();
                break;
            case 3:
                displayComprarMalaArtigo();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        this.state = State.PRINCIPAL;
    }

    public void displayComprarSapatilhasArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Comprar Sapatilhas ---");

        // Listar todas as sapatilhas disponíveis para comprar (vindas do controller como string)
        List<String> sapatilhas = mainController.listarSapatilhasDisponiveis();

        if (sapatilhas.isEmpty()) {
            System.out.println("Não existem sapatilhas disponíveis para comprar.");
        } else {
            for (String sapatilha : sapatilhas) {
                System.out.println(sapatilha);
            }

            System.out.print("Enter the código do artigo a comprar: ");
            String codigoArtigo = scanner.nextLine();

            // Add the selected Sapatilha to the Encomenda
            mainController.adicionarArtigoEncomenda(codigoArtigo);
            System.out.println("Sapatilha com código '" + codigoArtigo + "' adicionada à encomenda.");
        }
    }

    public void displayComprarTshirtArtigo() {
        System.out.println("\n--- Comprar Tshirt ---");

        // Assuming you have a method to get all available Tshirts from mainController
        List<String> tshirts = mainController.listarTshirtsDisponiveis();

        if (tshirts.isEmpty()) {
            System.out.println("Não existem tshirts disponíveis para comprar.");
        } else {
            for (String tshirt : tshirts) {
                System.out.println(tshirt);
            }

            System.out.print("Enter the código do artigo a comprar: ");
            String codigoArtigo = scanner.nextLine();

            // Add the selected Tshirt to the Encomenda
            mainController.adicionarArtigoEncomenda(codigoArtigo);
            System.out.println("Tshirt com código '" + codigoArtigo + "' adicionada à encomenda.");
        }
    }

    public void displayComprarMalaArtigo() {
        System.out.println("\n--- Comprar Mala ---");

        // Assuming you have a method to get all available Malas from mainController
        List<String> malas = mainController.listarMalasDisponiveis();

        if (malas.isEmpty()) {
            System.out.println("Não existem malas disponíveis para comprar.");
        } else {
            for (String mala : malas) {
                System.out.println(mala);
            }

            System.out.print("Enter the código do artigo a comprar: ");
            String codigoArtigo = scanner.nextLine();

            // Add the selected Mala to the Encomenda
            mainController.adicionarArtigoEncomenda(codigoArtigo);
            System.out.println("Mala com código '" + codigoArtigo + "' adicionada à encomenda.");
        }
    }


}
