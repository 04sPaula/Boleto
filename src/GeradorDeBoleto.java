import java.util.Calendar;

public class GeradorDeBoleto {
    private final BoletoBuilder boletoBuilder;
    String sacado;
    String sacadoCad;
    String cedente;
    String cedenteCad;
    double valor;
    Calendar vencimento;

    public GeradorDeBoleto(BoletoBuilder boletoBuilder, String sacado, String sacadoCad, String cedente, String cedenteCad, double Valor, Calendar Vencimento) {
        this.boletoBuilder = boletoBuilder;
        this.sacado = sacado;
        this.sacadoCad = sacadoCad;
        this.cedente = cedente;
        this.cedenteCad = cedenteCad;
        this.valor = Valor;
        this.vencimento = Vencimento;
    }
    public Boleto geraBoleto() {
        this.boletoBuilder.buildSacado(sacado, sacadoCad);
        this.boletoBuilder.buildCedente(cedente, cedenteCad);
        this.boletoBuilder.buildValor(valor);
        this.boletoBuilder.buildVencimento(vencimento);
        this.boletoBuilder.buildNossoNumero();

        this.boletoBuilder.buildCodigoDeBarras();
        this.boletoBuilder.buildLogotipo();

        return this.boletoBuilder.getBoleto();
    }
}
