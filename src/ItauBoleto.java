import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ItauBoleto implements Boleto {
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

    public ItauBoleto(String sacado, String sacadoCad, String cedente, String cedenteCad, double valor, Calendar vencimento,
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
        return GeradorDeCodDeBarras.gerarImagem(getCodigoDeBarrasDigitavel(),
                "../imagens/CodigoDeBarrasCompleto.png");
    };

    public String getCodigoDeBarrasDigitavel() {
        String numero = String.valueOf(carteira) + nossoNumero;

        int soma = 0;
        int multiplicador = 2;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            int produto = digito * multiplicador;

            if (produto > 9) {
                produto = (produto / 10) + (produto % 10);
            }

            soma += produto;

            multiplicador = (multiplicador == 2) ? 1 : 2;
        }

        int resto = soma % 10;
        int dac = (resto == 0) ? 0 : (10 - resto);

        String campoLivre = String.format("%03d", carteira) + String.format("%08d", nossoNumero) +
                String.format("%04d", agencia) + String.format("%05d", conta) + dac + "0000";

        return GeradorDeCodDeBarras.gerarCodigoBase("341", vencimento, valor) + campoLivre;
    }

    public String getLogotipo() {
        return "imagens/logotipos/ITAU.png";
    }


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
