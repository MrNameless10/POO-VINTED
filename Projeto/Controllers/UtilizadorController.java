package Projeto.Controllers;

import Projeto.Models.Artigo;
import Projeto.Models.Utilizador;

package Projeto.Controllers;

import Projeto.Models.Utilizador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UtilizadorController {
    private List<Utilizador> utilizadores;

    public UtilizadorController() {
        this.utilizadores = new ArrayList<>();
    }

    public void criarUtilizador(Utilizador utilizador) {
        this.utilizadores.add(utilizador);
    }

    public List<Utilizador> obterUtilizadores() {
        return this.utilizadores;
    }


    public Utilizador obterUtilizadorPorEmail(String email) {
        return this.utilizadores.stream()
                .filter(utilizador -> utilizador.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Utilizador obterUtilizadorPorCodigo(String codigoUtilizador) {
        return this.utilizadores.stream()
                .filter(utilizador -> utilizador.getCodigo().equalsIgnoreCase(codigoUtilizador))
                .findFirst()
                .orElse(null);
    }
}

