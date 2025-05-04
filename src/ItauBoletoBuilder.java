import java.util.Calendar;

public class ItauBoletoBuilder implements BoletoBuilder{
    private String sacado;
    private String cedente;
    private double valor;
    private Calendar vencimento;
    private int nossoNumero;

    public ItauBoletoBuilder(String sacado, String cedente, double valor, Calendar vencimento,
                           int nossoNumero) {
        this.sacado = sacado;
        this.cedente = cedente;
        this.valor = valor;
        this.vencimento = vencimento;
        this.nossoNumero = nossoNumero;
    }

    @Override
    public void buildSacado(String sacado) {

    }

    @Override
    public void buildCedente(String cedente) {

    }

    @Override
    public void buildValor(double valor) {

    }

    @Override
    public void buildVencimento(Calendar vencimento) {

    }

    @Override
    public void buildNossoNumero(int nossoNumero) {

    }

    @Override
    public void buildCodigoDeBarras() {

    }

    @Override
    public void buildLogotipo() {

    }

    @Override
    public Boleto getBoleto() {
        return null;
    }
}
