import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Venda> vendas = Arrays.asList(
            new Venda("João Silva", 1200.50),
            new Venda("João Silva", 950.75),
            new Venda("João Silva", 1800.00),
            new Venda("João Silva", 1400.30),
            new Venda("João Silva", 1100.90),
            new Venda("João Silva", 1550.00),
            new Venda("João Silva", 1700.80),
            new Venda("João Silva", 250.30),
            new Venda("João Silva", 480.75),
            new Venda("João Silva", 320.40),

            new Venda("Maria Souza", 2100.40),
            new Venda("Maria Souza", 1350.60),
            new Venda("Maria Souza", 950.20),
            new Venda("Maria Souza", 1600.75),
            new Venda("Maria Souza", 1750.00),
            new Venda("Maria Souza", 1450.90),
            new Venda("Maria Souza", 400.50),
            new Venda("Maria Souza", 180.20),
            new Venda("Maria Souza", 90.75),

            new Venda("Carlos Oliveira", 800.50),
            new Venda("Carlos Oliveira", 1200.00),
            new Venda("Carlos Oliveira", 1950.30),
            new Venda("Carlos Oliveira", 1750.80),
            new Venda("Carlos Oliveira", 1300.60),
            new Venda("Carlos Oliveira", 300.40),
            new Venda("Carlos Oliveira", 500.00),
            new Venda("Carlos Oliveira", 125.75),

            new Venda("Ana Lima", 1000.00),
            new Venda("Ana Lima", 1100.50),
            new Venda("Ana Lima", 1250.75),
            new Venda("Ana Lima", 1400.20),
            new Venda("Ana Lima", 1550.90),
            new Venda("Ana Lima", 1650.00),
            new Venda("Ana Lima", 75.30),
            new Venda("Ana Lima", 420.90),
            new Venda("Ana Lima", 315.40)
        );

        Map<String, Double> comissoes = new HashMap<>();

        for (Venda venda : vendas) {

            double percentual;

            if (venda.getValor() < 100) {
                percentual = 0;
            } else if (venda.getValor() < 500) {
                percentual = 0.01;
            } else {
                percentual = 0.05;
            }

            double comissao = venda.getValor() * percentual;

            comissoes.put(
                venda.getVendedor(),
                comissoes.getOrDefault(venda.getVendedor(), 0.0) + comissao
            );
        }

        System.out.println("Comissão por vendedor:");

        for (Map.Entry<String, Double> entrada : comissoes.entrySet()) {
            System.out.printf(
                "%s: R$ %.2f%n",
                entrada.getKey(),
                entrada.getValue()
            );
        }
    }
}

class Venda {

    private String vendedor;
    private double valor;

    public Venda(String vendedor, double valor) {
        this.vendedor = vendedor;
        this.valor = valor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public double getValor() {
        return valor;
    }
}