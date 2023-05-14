package Projeto.View;
import Projeto.Controllers.*;
import Projeto.Exceptions.EncomendaNaoExistenteException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.time.Year;


public class Menu {
    private enum State {
        INICIAL, LOGIN, REGISTAR, PRINCIPAL, GERIR,
        ADICIONAR, APAGAR, COMPRAR, VER_ENCOMENDA, FINALIZAR,
        ESTATISTICAS, AVANCAR, SAIR, TRANSPORTADORA
    }

    ;

    private final Scanner scanner;
    private State state = State.INICIAL;

    private MainController mainController;

    private int count = 0; // Inicializa a variável count com zero

    public Menu() {
        this.scanner = new Scanner(System.in);
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
                case TRANSPORTADORA:
                    displayVerTransportadorasSubMenu();
                    break;
                case FINALIZAR:
                    try {
                    mainController.finalizarEncomenda();
                        System.out.println("Encomenda finalizada com sucesso!");
                } catch (EncomendaNaoExistenteException e) {
                    System.out.println(e.getMessage());
                }
                    this.state = State.PRINCIPAL;
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
        System.out.println("3. Transportadora");
        System.out.println("4. Estatisticas");
        System.out.println("5. Avançar o tempo");
        System.out.println("6. Sair");
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
                this.state = State.TRANSPORTADORA;
                break;
            case 4:
                this.state = State.ESTATISTICAS;
                break;
            case 5:
                this.state = State.AVANCAR;
                break;
            case 6:
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
        System.out.println("4. Voltar");
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
            case 4:
                this.state = State.PRINCIPAL ;
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

    public void displayVerTransportadorasSubMenu() {
        System.out.println("\n--- Ver Transportadoras ---");
        System.out.println("1. Criar uma transportadora");
        System.out.println("2. Listar transportadoras disponíveis");
        System.out.println("3. Detalhes de uma transportadora");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Consumes the newline left-over

        switch (input) {

            case 1:
                criarTransportadora();
                break;
            case 2:
                // Listar transportadoras disponíveis (vindo do controller)
                List<String> transportadorasDisponiveis = mainController.listarTransportadoraDisponiveis();
                if (transportadorasDisponiveis.isEmpty()) {
                    System.out.println("Não há transportadoras disponíveis.");
                } else {
                    System.out.println("Transportadoras disponíveis:");
                    for (String transportadora : transportadorasDisponiveis) {
                        System.out.println(transportadora);
                    }
                }
                break;
            case 3:
                // Detalhes de uma transportadora
                System.out.print("Digite o código da transportadora: ");
                String codigoTransportadora = scanner.nextLine();
                // Obter detalhes da transportadora (vindo do controller)
                String detalhesTransportadora = mainController.obterDetalhesTransportadora(codigoTransportadora);
                if (detalhesTransportadora == null) {
                    System.out.println("Transportadora não encontrada.");
                } else {
                    System.out.println("Detalhes da transportadora:");
                    System.out.println(detalhesTransportadora);
                }
                break;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
                break;
        }
        this.state = State.INICIAL;
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
                double precoTotal = mainController.getPrecoTotalEncomenda();
                System.out.println("Preço total da encomenda: " + precoTotal);
                break;
            case 2:
                // Listar artigos (vindo do controller)
                List<String> artigosEncomenda = mainController.listarArtigosEncomenda();
                if (artigosEncomenda.isEmpty()) {
                    System.out.println("A encomenda não contém artigos.");
                } else {
                    System.out.println("Artigos da encomenda:");
                    for (String artigo : artigosEncomenda) {
                        System.out.println(artigo);
                    }
                }
                break;
            case 3:
                // Remover artigo da encomenda
                System.out.print("Digite o código do artigo a ser removido: ");
                String codigoArtigo = scanner.nextLine();
                mainController.removerArtigoEncomenda(codigoArtigo);
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
        LocalDate dataFim = LocalDate.now();

        switch (input) {
            case 1:
                System.out.print("Digite a data de início (formato: AAAA-MM-DD): ");
                String dataInicioStr = scanner.nextLine();
                LocalDate dataInicio = LocalDate.parse(dataInicioStr);

                String utilizadorMaiorFaturacao = mainController.getVendedorComMaiorFaturacao(dataInicio,dataFim);
                if (!utilizadorMaiorFaturacao.isEmpty()) {
                    System.out.println("Utilizador que mais faturou: " + utilizadorMaiorFaturacao);
                } else {
                    System.out.println("Não há utilizadores com faturação.");
                }
                break;
            case 2:

                System.out.print("Digite a data de início (formato: AAAA-MM-DD): ");
                String dataInicioStr2 = scanner.nextLine();
                LocalDate dataInicio2 = LocalDate.parse(dataInicioStr2);
                String transportadoraMaiorFaturacao = mainController.getTransportadoraComMaiorQuantidadeArtigosFaturados(dataInicio2,dataFim);
                if (!transportadoraMaiorFaturacao.isEmpty()) {
                    System.out.println("Transportadora com maior volume de faturação: " + transportadoraMaiorFaturacao);
                } else {
                    System.out.println("Não há transportadoras com faturação.");
                }
                break;

            case 3:
                System.out.print("Digite o email do utilizador: ");
                String emailUtilizador = scanner.nextLine();

                List<String> encomendasUtilizador = mainController.listarEncomendasUtilizador(emailUtilizador);
                if (!encomendasUtilizador.isEmpty()) {
                    System.out.println("Encomendas do utilizador:");
                    for (String encomenda : encomendasUtilizador) {
                        System.out.println(encomenda);
                    }
                } else {
                    System.out.println("Não há encomendas do utilizador.");
                }
                break;

            case 4:
                List<String> utilizadoresOrdenados = mainController.getUtilizadoresOrdenadosPorFaturacao();
                if (!utilizadoresOrdenados.isEmpty()) {
                    System.out.println("Ordenação dos maiores compradores/vendedores:");
                    for (String utilizador : utilizadoresOrdenados) {
                        System.out.println(utilizador);
                    }
                } else {
                    System.out.println("Não há utilizadores com faturação.");
                }
                break;

            case 5:
                double faturacaoVintage = mainController.calcularFaturacaoVintage();
                System.out.println("Dinheiro ganho pelo Vintage: " + faturacaoVintage);
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

    public void criarTransportadora() {
        // Prompt the user for transportadora details
        System.out.print("Digite o nome da transportadora: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o valor base para itens pequenos: ");
        double valorBasePequeno = scanner.nextDouble();
        System.out.print("Digite o valor base para itens médios: ");
        double valorBaseMedio = scanner.nextDouble();
        System.out.print("Digite o valor base para itens grandes: ");
        double valorBaseGrande = scanner.nextDouble();
        System.out.print("Digite a margem de lucro: ");
        double margemLucro = scanner.nextDouble();
        System.out.print("É uma transportadora premium? (S/N): ");
        String isPremiumStr = scanner.next();
        boolean isPremium = isPremiumStr.equalsIgnoreCase("S");

        // Create the transportadora
        mainController.adicionarTransportadora(nome, valorBasePequeno, valorBaseMedio, valorBaseGrande, margemLucro, isPremium);

        System.out.println("Transportadora criada com sucesso!");
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
            scanner.nextLine();
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
        System.out.println("ADICIONADO COM SUCESSO");
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
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.print("Enter the tamanho (S, M, L, XL): ");
        String tamanho = scanner.nextLine();
        scanner.nextLine();
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
        scanner.nextLine();
        System.out.print("Is it new? (true/false): ");
        boolean isNovo = scanner.nextBoolean();

        List<String> transportadoras = mainController.listarTransportadoraDisponiveis();

        if (transportadoras.isEmpty()) {
            System.out.println("Não existem transportadoras disponíveis.");
        } else {
            for (String transportador : transportadoras) {
                System.out.println(transportador);
            }
            scanner.nextLine(); // Limpar o buffer de entrada
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
        scanner.nextLine(); // Limpar o buffer de entrada
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
        scanner.nextLine(); // Limpar o buffer de entrada
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
