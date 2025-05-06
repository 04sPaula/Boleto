import java.util.Calendar;

public class GeradorDeBoleto {
    private BoletoBuilder boletoBuilder;
    String sacado;
    String cedente;
    double valor;
    Calendar vencimento;

    public GeradorDeBoleto(BoletoBuilder boletoBuilder, String sacado, String cedente, double Valor, Calendar Vencimento) {
        this.boletoBuilder = boletoBuilder;
        this.sacado = sacado;
        this.cedente = cedente;
        this.valor = Valor;
        this.vencimento = Vencimento;
    }
    public Boleto geraBoleto() {
        this.boletoBuilder.buildSacado(sacado);
        this.boletoBuilder.buildCedente(cedente);
        this.boletoBuilder.buildValor(valor);
        this.boletoBuilder.buildVencimento(vencimento);

        int nossoNumero = 15412;
        this.boletoBuilder.buildNossoNumero(nossoNumero);

        this.boletoBuilder.buildCodigoDeBarras();
        this.boletoBuilder.buildLogotipo();

        return this.boletoBuilder.getBoleto();
    }
}
