import Projeto.Models.Artigo;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("\nMENU VIEW - Vintage Marketplace");
        System.out.println("1. Login");
        System.out.println("2. Registar");
        System.out.println("3. Estatisticas");
        System.out.println("4. Avançar o tempo");
        System.out.println("5. Sair");
        System.out.println("6. Executar simulação automática");
        System.out.println("7. Sair");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public int displayArtigoSubMenu() {
        System.out.println("\n--- Gerir Artigo ---");
        System.out.println("1. Criar Artigo");
        System.out.println("2. Listar Artigos");
        System.out.println("3. Atualizar Artigo");
        System.out.println("4. Deletar Artigo");
        System.out.println("5. Retornar ao Menu Principal");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    public Artigo getArtigoDetails() {
        System.out.println("\n--- Add Artigo ---");
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the price: ");
        double price = scanner.nextDouble();
        return new Artigo(name, description, price);
    }

    public void displayArtigos(List<Artigo> artigos) {
        System.out.println("\n--- List Artigos ---");
        for (Artigo artigo : artigos) {
            System.out.println(artigo);
        }
    }

    public int getArtigoId(String action) {
        System.out.println("\n--- " + action + " Artigo ---");
        System.out.print("Enter the Artigo ID: ");
        return scanner.nextInt();
    }

    public Artigo updateArtigoDetails(Artigo artigo) {
        System.out.println("\n--- Update Artigo ---");
        scanner.nextLine(); // Consume newline
        System.out.println("Current Name: " + artigo.getName());
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.println("Current Description: " + artigo.getDescription());
        System.out.print("Enter new description: ");
        String description = scanner.nextLine();
        System.out.println("Current Price: " + artigo.getPrice());
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        return new Artigo(name, description, price);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
