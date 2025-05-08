import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BBBoleto implements Boleto {
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
    private String codigoDeBarras;

    public BBBoleto(String sacado, String sacadoCad, String cedente, String cedenteCad, double valor, Calendar vencimento,
                    int nossoNumero) {
        this.sacado = sacado;
        this.sacadoCad = sacadoCad;
        this.cedente = cedente;
        this.cedenteCad = cedenteCad;
        this.valor = valor;
        this.vencimento = vencimento;
        this.nossoNumero = nossoNumero;
    }

    @Override
    public String getSacado() {
        return this.sacado;
    }

    @Override
    public String getSacadoCad() {
        return this.sacadoCad;
    }

    @Override
    public String getCedente() {
        return this.cedente;
    }

    @Override
    public String getCedenteCad() {
        return this.cedenteCad;
    }

    @Override
    public double getValor() {
        return this.valor;
    }

    @Override
    public Calendar getVencimento() {
        return this.vencimento;
    }

    @Override
    public int getNossoNumero() {
        return this.nossoNumero;
    }

    @Override
    public String getCodigoDeBarras() {
        String codigoEscrito = GeradorDeCodDeBarras.gerarCodigoBase("001", vencimento, valor) + nossoNumero + agencia + conta + carteira;
        GeradordeCodDeBarras.gerarImagem(codigoEscrito, "./imagens/CodigoDeBarrasCompleto.png");
        return "./imagens/CodigoDeBarrasCompleto.png";
    };

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Boleto BB" + "\n");
        stringBuilder.append("Sacado: " + this.sacado + "\n");
        stringBuilder.append("CPF/CNPJ do sacado: " + this.sacadoCad + "\n");
        stringBuilder.append("Cedente: " + this.cedente + "\n");
        stringBuilder.append("CPF/CNPJ do cedente: " + this.cedenteCad + "\n");
        stringBuilder.append("Valor: " + this.valor + "\n");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        stringBuilder.append("Vencimento: " + sdf.format(vencimento.getTime()) + "\n");

        stringBuilder.append("NossoNumero: " + this.nossoNumero + "\n");
        return stringBuilder.toString();
    }
}
