import java.util.Calendar;

public interface Boleto {
    String getSacado();
    String getSacadoCad();
    String getCedente();
    String getCedenteCad();
    double getValor();
    Calendar getVencimento();
    int getNossoNumero();
    int getAgencia;
    int getConta;
    int getCarteira;
    String getCodigoDeBarras();
    String toString();

}
