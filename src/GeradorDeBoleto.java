import java.util.Calendar;

public class GeradorDeBoleto {
    private BoletoBuilder boletoBuilder;

    public GeradorDeBoleto(BoletoBuilder boletoBuilder) {
        this.boletoBuilder = boletoBuilder;
    }
    public Boleto geraBoleto() {
        String sacado = "Marcelo Martins";
        this.boletoBuilder.buildSacado(sacado);

        String cedente = "K19 Treinamentos";
        this.boletoBuilder.buildCedente(cedente);

        double valor = 100.54;
        this.boletoBuilder.buildValor(valor);

        Calendar vencimento = Calendar.getInstance();
        vencimento.set(2025, Calendar.MAY, 31);
        this.boletoBuilder.buildVencimento(vencimento);

        int nossoNumero = 15412;
        this.boletoBuilder.buildNossoNumero(nossoNumero);

        this.boletoBuilder.buildCodigoDeBarras();
        this.boletoBuilder.buildLogotipo();

        return this.boletoBuilder.getBoleto();
    }
}
