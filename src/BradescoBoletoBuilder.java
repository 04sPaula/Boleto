import java.util.Calendar;

public class BradescoBoletoBuilder implements BoletoBuilder {
    private String sacado;
    private String cedente;
    private double valor;
    private Calendar vencimento;
    private int nossoNumero;

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
        return new BradescoBoleto(sacado, cedente, valor, vencimento, nossoNumero);
    }
}
