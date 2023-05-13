import Projeto.Models.Artigo;
import Projeto.Models.Utilizador;

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
        System.out.println("3. Criar Transportadora");
        System.out.println("4. Estatisticas");
        System.out.println("5. Avançar o tempo");
        System.out.println("6. Executar simulação automática");
        System.out.println("7. Sair");
        System.out.print("Digite a opção desejada: ");
        return scanner.nextInt();
    }

    public int displayLoginSubMenu() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter the e-mail: ");
        String name = scanner.nextLine();
        System.out.print("Enter the número Fiscal: ");
        String name = scanner.nextLine();
        return scanner.nextInt();
    }

    public int displayRegistarSubMenu() {
        System.out.println("\n--- Registar ---");
        System.out.print("Enter the e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Enter the nome: ");
        String name = scanner.nextLine();
        System.out.print("Enter the morada: ");
        String morada = scanner.nextLine();
        System.out.print("Enter the número Fiscal: ");
        String nif= scanner.nextLine();
        return new Utilizador(email, name, morada, nif);
    }


    public void showMessage(String message) {
        System.out.println(message);
    }
}
