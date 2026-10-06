import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

       DateTimeFormatter formato = new DateTimeFormatterBuilder()
        .appendPattern("[dd/MM/yyyy][dd/MM/yy]")
        .toFormatter();

        System.out.println("===== CÁLCULO DE JUROS =====");

        System.out.print("Digite o valor da conta: R$ ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Digite a data de vencimento (dd/MM/yyyy): ");
        String dataVencimentoTexto = scanner.nextLine();

        LocalDate dataVencimento =
                LocalDate.parse(dataVencimentoTexto, formato);

        LocalDate dataHoje = LocalDate.now();

        if (valor <= 0) {
            System.out.println("O valor deve ser maior que zero.");
            scanner.close();
            return;
        }

        if (dataVencimento.isBefore(dataHoje)) {

            long diasAtraso =
                    ChronoUnit.DAYS.between(dataVencimento, dataHoje);

            double percentualDiario = 0.025;

            double juros =
                    valor * percentualDiario * diasAtraso;

            double valorTotal = valor + juros;

            System.out.println("\n===== RESULTADO =====");
            System.out.println("Valor original: R$ " +
                    String.format("%.2f", valor));

            System.out.println("Data de vencimento: " +
                    dataVencimento.format(formato));

            System.out.println("Data atual: " +
                    dataHoje.format(formato));

            System.out.println("Dias em atraso: " + diasAtraso);

            System.out.println("Juros: R$ " +
                    String.format("%.2f", juros));

            System.out.println("Valor total: R$ " +
                    String.format("%.2f", valorTotal));

        } else {

            System.out.println("\nA conta ainda não está atrasada.");
            System.out.println("Valor a pagar: R$ " +
                    String.format("%.2f", valor));
        }

        scanner.close();
    }
}