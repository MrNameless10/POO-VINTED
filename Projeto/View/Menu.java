import java.util.List;
import java.util.Scanner;

public class Menu {
    private enum State {
        INICIAL, LOGIN, REGISTAR, PRINCIPAL, GERIR,
        ADICIONAR, APAGAR, COMPRAR, VER_ENCOMENDA, FINALIZAR,
        ESTATISTICAS, AVANCAR, SAIR
    };

    private final Scanner scanner;
    private State state = State.INICIAL;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (this.state != State.SAIR) {
            switch (this.state) {
                case State.INICIAL:
                    displayMenu();
                    break;
                case State.REGISTAR:
                    displayRegistarSubMenu();
                    break;
                case State.LOGIN:
                    displayLoginSubMenu();
                    break;
                case State.PRINCIPAL:
                    displayUtilizadorMenu();
                    break;
                case State.GERIR:
                    displayGerirArtigosSubMenu();
                    break;
                case State.ADICIONAR:
                    displayAdicionarArtigoSubMenu();
                    break;
                case State.APAGAR:
                    displayApagarArtigo();
                    break;
                case State.COMPRAR:
                    displayComprarArtigosSubMenu();
                    break;
                case State.VER_ENCOMENDA:
                    displayVerEncomendaSubMenu();
                    break;
                case State.FINALIZAR:
                    // TODO - Finalizar a encomenda no controller
                    break;
                case State.ESTATISTICAS:
                    displayEstatisticasSubMenu();
                    break;
                case State.AVANCAR:
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
        System.out.print("Enter the nome: ");
        String nome = scanner.nextLine();
        System.out.print("Enter the morada: ");
        String morada = scanner.nextLine();
        System.out.print("Enter the número Fiscal: ");
        String nif = scanner.nextLine();
        
        // TODO - Adicionar utilizador no controller
        
        // TODO - Verificar se utilizador foi criado com sucesso (email nao se pode repetir)
        this.state = State.INICIAL;
    }

    public void displayLoginSubMenu() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Login ---");
        System.out.print("Enter the e-mail: ");
        String email = scanner.nextLine();

        // TODO - Verificar se utilizador existe
        this.state = State.PRINCIPAL;
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
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                this.state = State.ADICIONAR;
                break;
            case 2:
                this.state = State.APAGAR;
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public void displayApagarArtigo() {
        System.out.println("\n--- Apagar artigos ---");
        
        // TODO - Mostrar os artigos do utilizador (vindos em string do controller)

        System.out.print("Enter the código do artigo a apagar: ");

        String artigo = scanner.nextLine();

        // TODO - Apagar artigo no controller

        this.state = State.PRINCIPAL;
    }

    public void displayVerEncomendaSubMenu() {
        System.out.println("\n--- Ver encomenda ---");
        System.out.println("1. Preço total");
        System.out.println("2. Ver artigos");
        System.out.println("3. Remover artigo");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                // TODO - Mostrar preço total (vindo do controller)
                break;
            case 2:
                // TODO - Listar artigos (vindo do controller)
                break;
            case 3:
                // TODO - Listar artigos (vindo do controller)
                System.out.print("Enter the código do artigo a apagar: ");

                String artigo = scanner.nextLine();

                // TODO - Remover artigo da encomenda no controller
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        this.state = State.PRINCIPAL;
    }

    public void displayEstatisticasSubMenu() {
        System.out.println("\n--- Estatísticas ---");
        System.out.println("1. Vendedor que mais facturou");
        System.out.println("2. Transportadora com maior volume de facturação");
        System.out.println("3. Listar encomendas de um vendedor");
        System.out.println("4. Ordenação dos maiores compradores/vendedores");
        System.out.println("5. Dinheiro ganho pelo Vintage");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                // TODO - Controller
                break;
            case 2:
                // TODO - Controller
                break;
            case 3:
                // TODO - Controller
                break;
            case 4:
                // TODO - Controller
                break;
            case 5:
                // TODO - Controller
                break;
            default:
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

        if (isNovo) {
            // Artigo novo
            // TODO - Adicionar o novo artigo no controller
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            // TODO - Adicionar o novo artigo no controller
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

        if (isNovo) {
            // Artigo novo
            // TODO - Adicionar o novo artigo no controller
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            // TODO - Adicionar o novo artigo no controller
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
        System.out.print("Is it new? (true/false): ");
        boolean isNovo = scanner.nextBoolean();

        if (isNovo) {
            // Artigo novo
            // TODO - Adicionar o novo artigo no controller
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            // TODO - Adicionar o novo artigo no controller
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

        // TODO - Listar todas as sapatilhas disponiveis para comprar (vindas do controller como string)
        
        System.out.print("Enter the código do artigo a apagar: ");

        String artigo = scanner.nextLine();

        // TODO - Adicionar artigo à encomenda no controller
    }

    public void displayComprarTshirtArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Comprar Tshirt ---");

        // TODO - Listar todas as tshirts disponiveis para comprar (vindas do controller como string)
        
        System.out.print("Enter the código do artigo a apagar: ");

        String artigo = scanner.nextLine();

        // TODO - Adicionar artigo à encomenda no controller
    }

    public void displayComprarMalaArtigo() {
        scanner.nextLine(); // Limpar o buffer de entrada
        System.out.println("\n--- Comprar Mala ---");

        // TODO - Listar todas as malas disponiveis para comprar (vindas do controller como string)
        
        System.out.print("Enter the código do artigo a apagar: ");

        String artigo = scanner.nextLine();

        // TODO - Adicionar artigo à encomenda no controller
    }
}
