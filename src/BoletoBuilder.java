import java.util.Calendar;

public interface BoletoBuilder {
    void buildSacado(String sacado, String sacadoCad);
    void buildCedente(String cedente, String cedenteCad);
    void buildValor(double valor);
    void buildVencimento(Calendar vencimento);
    void buildNossoNumero();
    void buildCodigoDeBarras();
    void buildLogotipo();

    Boleto getBoleto();
}
