package Projeto;

import java.util.ArrayList;
import java.util.List;

public class VintageMarketplace {

    public static void main(String[] args) {
        List<Artigo> artigos = new ArrayList<>();

        // Exemplo de adição de artigos à lista
        artigos.add(new Sapatilha("S001", "Nike Air Force 1", "Nike", 150.0, 42, true, "branco", 2020, 1, 0.95));
        artigos.add(new TShirt("T001", "Supreme Box Logo", "Supreme", 200.0, "M", "estampado", false));
        artigos.add(new Mala("M001", "Chanel Classic Flap", "Chanel", 5000.0, "pequena", "couro", 2019, true, 0.05));

        // Exemplo de impressão dos artigos e preços finais
        for (Artigo artigo : artigos) {
            System.out.println("Código: " + artigo.getCodigo());
            System.out.println("Descrição: " + artigo.getDescricao());
            System.out.println("Marca: " + artigo.getMarca());
            System.out.println("Preço Base: " + artigo.getPrecoBase());
            System.out.println("Preço Final: " + artigo.getPrecoFinal());
            System.out.println("---------------------------");
        }
    }
}
