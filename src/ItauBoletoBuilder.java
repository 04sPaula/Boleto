import java.util.Calendar;

public class ItauBoletoBuilder implements BoletoBuilder{
    private String sacado;
    private String cedente;
    private double valor;
    private Calendar vencimento;
    private int nossoNumero;

    @Override
    public void buildSacado(String sacado) {
        this.sacado = sacado;
    }

    @Override
    public void buildCedente(String cedente) {
        this.cedente = cedente;
    }

    @Override
    public void buildValor(double valor) {
        this.valor = valor;
    }

    @Override
    public void buildVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    @Override
    public void buildNossoNumero() {
        nossoNumero = 341;
    }

    @Override
    public void buildCodigoDeBarras() {

    }

    @Override
    public void buildLogotipo() {

    }

    @Override
    public Boleto getBoleto() {
        return new ItauBoleto(sacado, cedente, valor, vencimento, nossoNumero);
    }
}
