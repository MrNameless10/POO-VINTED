import Projeto.Models.*;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private enum State {
        PRINCIPAL, LOGIN, REGISTAR, ESTATISTICAS, AVANCAR, SAIR
    }

    private final Scanner scanner;
    private State state = MAIN_MENU;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (this.state != SAIR) {
            switch (this.state) {
                case MAIN_MENU:
                    displayMenu();
                    break;
            }
        }
    }

    public void displayMenu() {
        System.out.println("\nMENU PRINCIPAL - Vintage Marketplace");
        System.out.println("1. Login");
        System.out.println("2. Registar");
        System.out.println("3. Estatisticas");
        System.out.println("4. Avançar o tempo");
        System.out.println("5. Sair");
        System.out.print("Digite a opção desejada: ");
        int input = scanner.nextInt();

        switch (input) {
            case 1:
                this.state = LOGIN;
                break;
            case 2:
                this.state = REGISTAR;
                break;
            case 3:
                this.state = ESTATISTICAS;
                break;
            case 4:
                this.state = AVANCAR;
                break;
            case 5:
                this.state = SAIR;
                break;
            default:
                break;
        }
    }

    public Utilizador displayRegistarSubMenu() {
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
        return new Utilizador(email, nome, morada, nif);
    }

    public int displayUtilizadorMenu() {
        System.out.println("\nMENU UTILIZADOR - Vintage Marketplace");
        System.out.println("1. Gerir artigos");
        System.out.println("2. Comprar artigos");
        System.out.println("3. Ver encomenda");
        System.out.println("4. Finalizar encomenda");
        System.out.println("5. Voltar para o menu principal (Logout)");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public int displayGerirArtigosSubMenu() {
        System.out.println("\n--- Gerir artigos ---");
        System.out.println("1. Adicionar");
        System.out.println("2. Apagar");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public String displayApagarArtigo() {
        System.out.println("\n--- Apagar artigos ---");
        System.out.print("Enter the código do artigo a apagar: ");
        return scanner.nextLine();
    }

    public int displayAdicionarArtigoSubMenu() {
        System.out.println("\n--- Adicionar artigo ---");
        System.out.println("1. Sapatilhas");
        System.out.println("2. T-Shirts");
        System.out.println("3. Malas");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public int displayComprarArtigosSubMenu() {
        System.out.println("\n--- Comprar artigos ---");
        System.out.println("1. Sapatilhas");
        System.out.println("2. T-Shirts");
        System.out.println("3. Malas");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
}

    public int displayVerEncomendaSubMenu() {
        System.out.println("\n--- Ver encomenda ---");
        System.out.println("1. Preço total");
        System.out.println("2. Ver artigos");
        System.out.println("3. Remover artigo");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public int displayEstatisticasSubMenu() {
        System.out.println("\n--- Estatísticas ---");
        System.out.println("1. Vendedor que mais facturou");
        System.out.println("2. Transportadora com maior volume de facturação");
        System.out.println("3. Listar encomendas de um vendedor");
        System.out.println("4. Ordenação dos maiores compradores/vendedores");
        System.out.println("5. Dinheiro ganho pelo Vintage");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public Artigo createSapatilhasArtigo() {
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
            return new Sapatilhas(descricao, marca, precoBase);
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            return new Sapatilhas(descricao, marca, precoBase, false, avaliacaoEstado, numDonosAnteriores);
        }
    }

    public Artigo createTShirtArtigo() {
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
            return new TShirt(descricao, marca, precoBase);
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            return new TShirt(descricao, marca, precoBase, false, avaliacaoEstado, numDonosAnteriores);
        }
    }

    public Artigo createMalaArtigo() {
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
            return new Mala(descricao, marca, precoBase);
        } else {
            // Artigo usado
            System.out.print("Enter the avaliação estado: ");
            double avaliacaoEstado = scanner.nextDouble();
            System.out.print("Enter the número de donos anteriores: ");
            int numDonosAnteriores = scanner.nextInt();
            return new Mala(descricao, marca, precoBase, false, avaliacaoEstado, numDonosAnteriores);
        }
    }

    public Artigo getArtigoToAdd() {
        System.out.println("\n--- Adicionar artigo ---");
        System.out.println("1. Sapatilhas");
        System.out.println("2. T-Shirts");
        System.out.println("3. Malas");
        System.out.print("Digite a opção desejada: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        switch (option) {
            case 1:
                return createSapatilhasArtigo();
            case 2:
                return createTShirtArtigo();
            case 3:
                return createMalaArtigo();
            default:
                throw new IllegalArgumentException("Opção inválida: " + option);
        }
    }


    public void showArtigos(List<Artigo> artigos) {
        System.out.println("\n--- Artigos Disponíveis ---");
        for (Artigo artigo : artigos) {
            System.out.println(artigo.getDescricao() + " - " + artigo.getPrecoFinal());
        }
    }

    public void showEncomenda(List<Artigo> artigos, double precoTotal) {
        System.out.println("\n--- Encomenda ---");
        for (Artigo artigo : artigos) {
            System.out.println(artigo.getDescricao() + " - " + artigo.getPrecoFinal());
        }
        System.out.println("Preço Total: " + precoTotal);
    }


    public void showMessage(String message) {
        System.out.println(message);
    }
}
