package Projeto.View;
import Projeto.Controllers.*;
import Projeto.Models.*;

import java.util.List;
import java.util.Scanner;

import java.time.Year;


public class Menu {
    private enum State {
        INICIAL, LOGIN, REGISTAR, PRINCIPAL, GERIR,
        ADICIONAR, APAGAR, COMPRAR, VER_ENCOMENDA, FINALIZAR,
        ESTATISTICAS, AVANCAR, SAIR
    };

    private final Scanner scanner;
    private State state = State.INICIAL;

    private UtilizadorController utilizadorController;
    private ArtigoController artigoController;

    private EncomendaController encomendaController;

    private TransportadoraController transportadoraController;

    private int count = 0; // Inicializa a variável count com zero

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.utilizadorController = new UtilizadorController();
        this.encomendaController = new EncomendaController();
        this.artigoController = new ArtigoController(this.encomendaController);
        this.transportadoraController = new TransportadoraController();
        this.encomendaController.criarEncomenda("someCodigo");
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

        Utilizador novoUtilizador = utilizadorController.obterUtilizadorPorEmail(email);
        if (novoUtilizador != null) {
            System.out.println("O utilizador já existe. Por favor, tente novamente com um e-mail diferente.");
        } else {
            System.out.print("Enter the nome: ");
            String nome = scanner.nextLine();
            System.out.print("Enter the morada: ");
            String morada = scanner.nextLine();
            System.out.print("Enter the número Fiscal: ");
            String nif = scanner.nextLine();

            utilizadorController.adicionarUtilizador(email, nome, morada, nif);
            System.out.println("Utilizador criado com sucesso!");
        }

        this.state = State.INICIAL;
    }


    public void displayLoginSubMenu() {

        System.out.print("Enter email: ");
        String emailLogin = scanner.next();
        Utilizador utilizador = utilizadorController.obterUtilizadorPorEmail(emailLogin);
        if (utilizador != null) {
            utilizadorController.setUtilizadorAtual(utilizador);
            System.out.println("Login efetuado com sucesso!");
            this.state = State.PRINCIPAL;
        } else {
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
        Utilizador utilizadorAtual = utilizadorController.getUtilizadorAtual();
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
                List<Artigo> artigosDoUtilizador = artigoController.listarArtigosDoUtilizador(utilizadorAtual.getCodigo());
                if (artigosDoUtilizador.isEmpty()) {
                    System.out.println("Você não tem artigos.");
                } else {
                    for (Artigo artigo : artigosDoUtilizador) {
                        System.out.println(artigo);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }


    public void displayApagarArtigo() {
        System.out.println("\n--- Apagar artigos ---");

        Utilizador utilizadorAtual = utilizadorController.getUtilizadorAtual();

        // Obter os artigos do utilizador atual.
        List<Artigo> artigosDoUtilizador = utilizadorController.obterArtigosDoUtilizador(utilizadorAtual);

        // Mostrar os artigos do utilizador.
        for (Artigo artigo : artigosDoUtilizador) {
            System.out.println(artigo);
        }

        System.out.print("Enter the código do artigo a apagar: ");

        String codigoArtigo = scanner.nextLine();

        // Encontrar o artigo a ser apagado.
        Artigo artigoAApagar = artigoController.obterArtigoPorCodigo(codigoArtigo);


        if (artigoAApagar != null) {
            // Apagar artigo no controller.
            artigoController.apagarArtigo(artigoAApagar);

            // Remover artigo do utilizador.
            utilizadorController.removerArtigoDoUtilizador(utilizadorAtual, artigoAApagar);

            System.out.println("Artigo apagado com sucesso.");
        } else {
            System.out.println("Artigo não encontrado.");
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
                double precoTotal = encomendaController.getPrecoTotal();
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
                createSapatilhasArtigo(artigoController, utilizadorController);
                break;
            case 2:
                createTShirtArtigo(artigoController, utilizadorController);
                break;
            case 3:
                createMalaArtigo(artigoController, utilizadorController);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        this.state = State.PRINCIPAL;
    }

    public void createSapatilhasArtigo(ArtigoController artigoController, UtilizadorController utilizadorController) {
        Utilizador utilizadorAtual = utilizadorController.getUtilizadorAtual();
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

        // Supondo que temos um código gerado automaticamente para cada sapatilha
        String codigo = "S" + (++count);

        if (isNovo) {
            // Sapatilha nova
            Sapatilha novaSapatilha = new Sapatilha(codigo, descricao, marca, precoBase, isNovo, false, 0, 0, 0, tamanho, temAtacadores, cor, dataLancamentoColecao, isPremium, utilizadorAtual.getCodigo());
            artigoController.criarArtigo(novaSapatilha);
        } else {
            // Sapatilha usada
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            Sapatilha novaSapatilha = new Sapatilha(codigo, descricao, marca, precoBase, isNovo, false, avaliacaoEstado, numDonosAnteriores, 0, tamanho, temAtacadores, cor, dataLancamentoColecao, isPremium, utilizadorAtual.getCodigo());
            artigoController.criarArtigo(novaSapatilha);
        }
    }



    public void createTShirtArtigo(ArtigoController artigoController, UtilizadorController utilizadorController) {
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
        TShirt.Tamanho tamanho = TShirt.Tamanho.valueOf(scanner.next().toUpperCase());
        System.out.print("Enter the padrão (LISO, RISCAS, PALMEIRAS): ");
        TShirt.Padrao padrao = TShirt.Padrao.valueOf(scanner.next().toUpperCase());

        // Supondo que temos um código gerado automaticamente para cada t-shirt
        String codigo = "T" + (++count);
        String dono = utilizadorController.getUtilizadorAtual().getEmail();
        if (isNovo) {
            // TShirt nova
            TShirt novaTShirt = new TShirt(codigo, descricao, marca, precoBase, isNovo, 0, 0, 0, false, tamanho, padrao, dono);
            // Adicionar a nova t-shirt no controller
            artigoController.criarArtigo(novaTShirt);
        } else {
            // TShirt usada
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            TShirt novaTShirt = new TShirt(codigo, descricao, marca, precoBase, isNovo, (int) avaliacaoEstado, numDonosAnteriores, 0, false, tamanho, padrao, dono);
            // Adicionar a nova t-shirt no controller
            artigoController.criarArtigo(novaTShirt);
        }
    }


    public void createMalaArtigo(ArtigoController artigoController, UtilizadorController utilizadorController) {
        Utilizador utilizadorAtual = utilizadorController.getUtilizadorAtual();
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

        // Supondo que temos um código gerado automaticamente para cada mala
        String codigo = "M" + (++count);

        if (isNovo) {
            // Mala nova
            Mala novaMala = new Mala(codigo, descricao, marca, precoBase, isNovo, 0, 0, 0, false, dimensao, material, anoColecao, isPremium, valorizacaoAnual, utilizadorAtual.getCodigo());
            // Adicionar a nova mala no controller
            artigoController.criarArtigo(novaMala);
        } else {
            // Mala usada
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            Mala novaMala = new Mala(codigo, descricao, marca, precoBase, isNovo, avaliacaoEstado, numDonosAnteriores, 0, false, dimensao, material, anoColecao, isPremium, valorizacaoAnual, utilizadorAtual.getCodigo());
            // Adicionar a nova mala no controller
            artigoController.criarArtigo(novaMala);
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
        List<Sapatilha> sapatilhasDisponiveis = artigoController.obterSapatilhasDisponiveis();
        for (Sapatilha sapatilha : sapatilhasDisponiveis) {
            System.out.println(sapatilha);
        }

        System.out.print("Enter the código do artigo a comprar: ");
        String codigoArtigo = scanner.nextLine();

        // Adicionar artigo à encomenda no controller
        artigoController.adicionarArtigoEncomenda(codigoArtigo);
    }

    public void displayComprarTshirtArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Comprar Tshirt ---");

        // Listar todas as tshirts disponíveis para comprar (vindas do controller como string)
        List<TShirt> tshirtsDisponiveis = artigoController.obterTshirtsDisponiveis();
        for (TShirt tshirt : tshirtsDisponiveis) {
            System.out.println(tshirt);
        }

        System.out.print("Enter the código do artigo a comprar: ");
        String codigoArtigo = scanner.nextLine();

        // Adicionar artigo à encomenda no controller
        artigoController.adicionarArtigoEncomenda(codigoArtigo);
    }


    public void displayComprarMalaArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Comprar Mala ---");

        // Listar todas as malas disponíveis para comprar (vindas do controller como string)
        List<Mala> malasDisponiveis = artigoController.obterMalasDisponiveis();
        for (Mala mala : malasDisponiveis) {
            System.out.println(mala);
        }

        System.out.print("Enter the código do artigo a comprar: ");
        String codigoArtigo = scanner.nextLine();

        // Adicionar artigo à encomenda no controller
        artigoController.adicionarArtigoEncomenda(codigoArtigo);
    }

}
