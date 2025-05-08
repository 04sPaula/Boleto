import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

public class GeradorDeCodDeBarras {
    public static void gerarImagem(String codigo, String caminho) {
        try {
            Barcode barcode = BarcodeFactory.createInt2of5(codigo, true);
            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
            barcode.setDrawingText(false);

            BufferedImage imagem = new BufferedImage(barcode.getWidth(), barcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
            barcode.draw((Graphics2D) imagem.getGraphics(), 0, 0);

            ImageIO.write(imagem, "png", new File(caminho));
        } catch (OutputException | IOException e) {
            e.printStackTrace();
        } catch (BarcodeException e) {
            throw new RuntimeException(e);
        }
    }

    public static String gerarCodigoBase(String banco, String nossoNumero, Calendar vencimento, double valor) {
        String moeda = "9"; // Real
        int fatorVencimento = (int) ((vencimento.getTimeInMillis() - getDataBase().getTimeInMillis()) / (1000 * 60 * 60 * 24));

        DecimalFormat valorFormatado = new DecimalFormat("0000000000");
        String valorBoleto = valorFormatado.format((int) (valor * 100)); // valor em centavos

        String nossoNumeroFormatado = String.format("%011d", Long.parseLong(nossoNumero));

        String campoCompleto = banco + moeda + fatorVencimento + valorBoleto + nossoNumeroFormatado;

        String numeroIdentificacao = "0";

        return banco + moeda + calcularDigitoVerificador(campoCompleto + numeroIdentificacao)
                + fatorVencimento + valor;
    }

    private static Calendar getDataBase() {
        Calendar base = Calendar.getInstance();
        base.set(1997, Calendar.OCTOBER, 7);
        return base;
    }

    private static int calcularDigitoVerificador(String codigo) {
        int soma = 0;
        int peso = 2;

        for (int i = codigo.length() - 1; i >= 0; i--) {
            int num = Character.getNumericValue(codigo.charAt(i));
            soma += num * peso;
            peso++;
            if (peso > 9) peso = 2;
        }

        int resto = soma % 11;
        int dv = 11 - resto;

        if (dv == 0 || dv == 10 || dv == 11) return 1;

        return dv;
    }
}
