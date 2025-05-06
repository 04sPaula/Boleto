import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BradescoBoleto implements Boleto {
    private String sacado;
    private String cedente;
    private double valor;
    private Calendar vencimento;
    private int nossoNumero;

    public BradescoBoleto(String sacado, String cedente, double valor, Calendar vencimento,
                    int nossoNumero) {
        this.sacado = sacado;
        this.cedente = cedente;
        this.valor = valor;
        this.vencimento = vencimento;
        this.nossoNumero = nossoNumero;
    }

    @Override
    public String getSacado() {
        return this.sacado;
    }

    @Override
    public String getCedente() {
        return this.cedente;
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Boleto BB" + "\n");
        stringBuilder.append("Sacado: " + this.sacado + "\n");
        stringBuilder.append("Cedente: " + this.cedente + "\n");
        stringBuilder.append("Valor: " + this.valor + "\n");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        stringBuilder.append("Vencimento: " + sdf.format(vencimento.getTime()) + "\n");

        stringBuilder.append("NossoNumero: " + this.nossoNumero + "\n");
        return stringBuilder.toString();
    }
}
