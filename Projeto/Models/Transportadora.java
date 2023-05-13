package Projeto.Models;


public class Transportadora {
    private String nome;
    private String formulaCalculo;
    private double valorBasePequena;
    private double valorBaseMedia;
    private double valorBaseGrande;
    private double imposto;
    private boolean especializadaPremium;

    public Transportadora(String nome, String formulaCalculo, double valorBasePequena, double valorBaseMedia,
                          double valorBaseGrande, double imposto, boolean especializadaPremium) {
        this.nome = nome;
        this.formulaCalculo = formulaCalculo;
        this.valorBasePequena = valorBasePequena;
        this.valorBaseMedia = valorBaseMedia;
        this.valorBaseGrande = valorBaseGrande;
        this.imposto = imposto;
        this.especializadaPremium = especializadaPremium;
    }

    public String getNome() {
        return nome;
    }

    public String getFormulaCalculo() {
        return formulaCalculo;
    }

    public void setFormulaCalculo(String formulaCalculo) {
        this.formulaCalculo = formulaCalculo;
    }

    public double getValorBase(Encomenda.Dimensao dimensao) {
        switch (dimensao) {
            case PEQUENO:
                return valorBasePequena;
            case MEDIO:
                return valorBaseMedia;
            case GRANDE:
                return valorBaseGrande;
            default:
                throw new IllegalArgumentException("Dimensão desconhecida: " + dimensao);
        }
    }

    public double calcularPrecoExpedicao(Encomenda encomenda) {
        double valorBase = getValorBase(encomenda.getDimensao());
        int numArtigos = encomenda.getArtigos().size();
        double margemLucro = calcularMargemLucro();

        double precoExpedicao = (valorBase * margemLucro * (1 + imposto)) * 0.9;

        if (especializadaPremium && encomenda.isPremium()) {
            // Aplicar regra de cálculo específica para artigos premium
            precoExpedicao *= 1.5;
        }

        return precoExpedicao * numArtigos;
    }

    private double calcularMargemLucro() {
        // Implemente aqui a lógica para calcular a margem de lucro
        // Pode ser um valor fixo ou obtido de alguma forma específica
        // Neste exemplo, vamos retornar 0.2 como margem de lucro de 20%
        return 0.2;
    }
}
