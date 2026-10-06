import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Produto {
    int codigoProduto;
    String descricaoProduto;
    int estoque;

    public Produto(int codigoProduto, String descricaoProduto, int estoque) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.estoque = estoque;
    }
}

class Movimentacao {
    int id;
    String descricao;
    String tipo;
    int codigoProduto;
    int quantidade;

    public Movimentacao(int id, String descricao, String tipo,
                        int codigoProduto, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<>();

        // Produtos fornecidos no JSON
        produtos.add(new Produto(101, "Caneta Azul", 150));
        produtos.add(new Produto(102, "Caderno Universitário", 75));
        produtos.add(new Produto(103, "Borracha Branca", 200));
        produtos.add(new Produto(104, "Lápis Preto HB", 320));
        produtos.add(new Produto(105, "Marcador de Texto Amarelo", 90));

        // Identificador único das movimentações
        int proximoId = 1;

        System.out.println("===== MOVIMENTAÇÃO DE ESTOQUE =====");

        System.out.println("\nProdutos disponíveis:");

        for (Produto produto : produtos) {
            System.out.println(
                    produto.codigoProduto + " - " +
                    produto.descricaoProduto +
                    " | Estoque: " + produto.estoque
            );
        }

        System.out.print("\nDigite o código do produto: ");
        int codigoProduto = scanner.nextInt();
        scanner.nextLine();

        // Procurar o produto
        Produto produtoSelecionado = null;

        for (Produto produto : produtos) {
            if (produto.codigoProduto == codigoProduto) {
                produtoSelecionado = produto;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            scanner.close();
            return;
        }

        System.out.print("Digite o tipo da movimentação (ENTRADA/SAIDA): ");
        String tipo = scanner.nextLine().toUpperCase();

        if (!tipo.equals("ENTRADA") && !tipo.equals("SAIDA")) {
            System.out.println("Tipo de movimentação inválido.");
            scanner.close();
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            scanner.close();
            return;
        }

        System.out.print("Digite a descrição da movimentação: ");
        String descricao = scanner.nextLine();

        // Verificação para evitar estoque negativo
        if (tipo.equals("SAIDA") && quantidade > produtoSelecionado.estoque) {
            System.out.println("Erro: quantidade de saída maior que o estoque disponível.");
            scanner.close();
            return;
        }

        // Atualização do estoque
        if (tipo.equals("ENTRADA")) {
            produtoSelecionado.estoque += quantidade;
        } else {
            produtoSelecionado.estoque -= quantidade;
        }

        // Criar movimentação
        Movimentacao movimentacao = new Movimentacao(
                proximoId,
                descricao,
                tipo,
                codigoProduto,
                quantidade
        );

        // Resultado
        System.out.println("\n===== MOVIMENTAÇÃO REALIZADA =====");
        System.out.println("ID da movimentação: " + movimentacao.id);
        System.out.println("Produto: " + produtoSelecionado.descricaoProduto);
        System.out.println("Código: " + produtoSelecionado.codigoProduto);
        System.out.println("Tipo: " + movimentacao.tipo);
        System.out.println("Descrição: " + movimentacao.descricao);
        System.out.println("Quantidade movimentada: " + movimentacao.quantidade);
        System.out.println("Estoque final: " + produtoSelecionado.estoque);

        scanner.close();
    }
}