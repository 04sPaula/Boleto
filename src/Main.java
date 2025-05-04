import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar vencimento = Calendar.getInstance();
        vencimento.set(2025, Calendar.MAY, 10);

        BoletoBuilder boletoBuilder = new BBBoletoBuilder();
        GeradorDeBoleto gerador = new GeradorDeBoleto(boletoBuilder);
        Boleto boleto = gerador.geraBoleto();

        System.out.println(boleto);
    }
}