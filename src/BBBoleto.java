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
                    int nossoNumero, int agencia, int conta, int carteira) {
        this.sacado = sacado;
        this.sacadoCad = sacadoCad;
        this.cedente = cedente;
        this.cedenteCad = cedenteCad;
        this.valor = valor;
        this.vencimento = vencimento;
        this.nossoNumero = nossoNumero;
        this.agencia = agencia;
        this.conta = conta;
        this.carteira = carteira;
        this.codigoDeBarras = getCodigoDeBarras();
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
    public int getAgencia() {
        return this.agencia;
    }

    @Override
    public int getConta() {
        return this.conta;
    }

    @Override
    public int getCarteira() {
        return this.carteira;
    }

    @Override
    public String getCodigoDeBarras() {
        String codigoEscrito = getCodigoDeBarrasDigitavel();
        String caminhoFinal = GeradorDeCodDeBarras.gerarImagem(codigoEscrito, "./imagens/CodigoDeBarrasCompleto.png");
        return caminhoFinal;
    };

    public String getCodigoDeBarrasDigitavel() {
        String campoLivre = String.format("%08d", nossoNumero) + String.format("%04d", agencia) +
                String.format("%08d", conta) + String.format("%03d", carteira);

        return GeradorDeCodDeBarras.gerarCodigoBase("001", vencimento, valor) + campoLivre;
    }

    public String getLogotipo() {
        return "./imagens/logotipos/BB.png";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Boleto BB" + "\n");
        stringBuilder.append("Sacado: " + this.sacado + "\n");
        stringBuilder.append("CPF/CNPJ do sacado: " + this.sacadoCad + "\n");
        stringBuilder.append("Cedente: " + this.cedente + "\n");
        stringBuilder.append("CPF/CNPJ do cedente: " + this.cedenteCad + "\n");
        stringBuilder.append("Caminho do código de barras: " + codigoDeBarras + "\n");
        stringBuilder.append("Valor: " + this.valor + "\n");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        stringBuilder.append("Vencimento: " + sdf.format(vencimento.getTime()) + "\n");

        stringBuilder.append("NossoNumero: " + this.nossoNumero + "\n");
        return stringBuilder.toString();
    }
}
