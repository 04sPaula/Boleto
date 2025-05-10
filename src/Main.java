import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe uma das opções a seguir:\n 1 para Banco do Brasil\n 2 para Itau\n 3 para Bradesco ");
        int banco = scanner.nextInt();
        scanner.nextLine();

        BoletoBuilder boletoBuilder = null;

        switch (banco) {
            case 1:
                boletoBuilder = new BBBoletoBuilder();
                System.out.println("Banco do Brasil selecionado!");
                break;
            case 2:
                boletoBuilder = new ItauBoletoBuilder();
                System.out.println("Itaú selecionado!");
                break;
            case 3:
                boletoBuilder = new BradescoBoletoBuilder();
                System.out.println("Bradesco selecionado!");
                break;
            default:
                System.out.println("Erro, tente novamente!");
                scanner.close();
                return;
        }

        System.out.print("Nome do Sacado: ");
        String sacado = scanner.nextLine();

        String sacadoCad = LeitorUtil.lerCampoComTamanho(scanner, "CPF/CNPJ do Sacado: ", 14);

        System.out.print("Nome do Cedente: ");
        String cedente = scanner.nextLine();

        String cedenteCad = LeitorUtil.lerCampoComTamanho(scanner, "CPF/CNPJ do Cedente: ", 14);

        System.out.print("Valor do boleto em reais (ex: 150.75): ");
        String valorTexto = scanner.nextLine().replace(",", ".");
        double valor = Double.parseDouble(valorTexto);

        System.out.println("Informe ano, mês e dia de vencimento, um por vez!");
        System.out.print("Ano de vencimento (ex. 2025): ");
        int ano = scanner.nextInt();
        System.out.print("Mês de vencimento (ex. 5): ");
        int mes = scanner.nextInt() - 1;
        System.out.print("Dia de vencimento (ex. 31): ");
        int dia = scanner.nextInt();
        scanner.nextLine(); // consumir quebra de linha pendente

        int conta = LeitorUtil.lerInteiroComTamanho(scanner, "Conta (até 10 dígitos): ", 10);
        int agencia = LeitorUtil.lerInteiroComTamanho(scanner, "Agência (até 4 dígitos): ", 4);
        int carteira = LeitorUtil.lerInteiroComTamanho(scanner, "Carteira (até 3 dígitos): ", 3);

        Calendar vencimento = Calendar.getInstance();
        vencimento.set(ano, mes, dia);

        GeradorDeBoleto gerador = new GeradorDeBoleto(
                boletoBuilder, sacado, sacadoCad, cedente, cedenteCad, valor,
                vencimento, agencia, carteira, conta
        );
        Boleto boleto = gerador.geraBoleto();

        try {
            String nomeBanco = boleto.getClass().getSimpleName().replace("Boleto", "");
            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            String nomeArquivo = String.format("./boletos/boleto_%s_%s.pdf", nomeBanco, timestamp);

            GeradorDePdfUtil.gerarPdf(boleto, nomeArquivo);
            System.out.println("PDF gerado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao gerar PDF: " + e.getMessage());
        }
    }
}
