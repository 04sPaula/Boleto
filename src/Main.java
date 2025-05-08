import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do Sacado: ");
        String sacado = scanner.nextLine();

        System.out.print("CPF/CNPJ do Sacado: ");
        String sacadoCad = scanner.nextLine();

        System.out.print("Nome do Cedente: ");
        String cedente = scanner.nextLine();

        System.out.print("CPF/CNPJ do Cedente: ");
        String cedenteCad = scanner.nextLine();

        System.out.print("Valor do boleto (ex: 150.75): ");
        String valorTexto = scanner.nextLine().replace(",", ".");
        double valor = Double.parseDouble(valorTexto);

        System.out.println("Informe ano, mês e dia de vencimento, um por vez!");
        System.out.print("Ano de vencimento (ex. 2025): ");
        int ano = scanner.nextInt();
        System.out.print("Mês de vencimento (ex. 5): ");
        int mes = scanner.nextInt() - 1;
        System.out.print("Dia de vencimento (ex. 31): ");
        int dia = scanner.nextInt();

        Calendar vencimento = Calendar.getInstance();
        vencimento.set(ano, mes, dia);

        BoletoBuilder boletoBuilder = new BBBoletoBuilder();
        GeradorDeBoleto gerador = new GeradorDeBoleto(boletoBuilder, sacado, sacadoCad, cedente, cedenteCad, valor, vencimento);
        Boleto boleto = gerador.geraBoleto();

        System.out.println(boleto);
    }
}
