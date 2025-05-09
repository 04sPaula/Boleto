import java.util.Calendar;

public class ItauBoletoBuilder implements BoletoBuilder{
    private String sacado;
    private String sacadoCad;
    private String cedente;
    private String cedenteCad;
    private double valor;
    private Calendar vencimento;
    private int nossoNumero;
    private int agencia;
    private int conta;
    private int carteira;

    @Override
    public void buildSacado(String sacado, String sacadoCad) {
        this.sacado = sacado;
        this.sacadoCad = sacadoCad;
    }

    @Override
    public void buildCedente(String cedente, String cedenteCad) {
        this.cedente = cedente;
        this.cedenteCad = cedenteCad;
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
    public void buildAgencia(int agencia) {
        this.agencia = agencia;
    }

    @Override
    public void buildConta(int conta) {
        this.conta = conta;
    }

    @Override
    public void buildCarteira(int carteira) {
        this.carteira = carteira;
    }

    @Override
    public Boleto getBoleto() {
        return new ItauBoleto(sacado, sacadoCad, cedente, cedenteCad, valor, vencimento, nossoNumero,
                agencia, conta, carteira);
    }
}
