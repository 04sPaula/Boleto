import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class GeradorDeCodDeBarras {
    public static String gerarImagem(String codigo, String caminho) {
        try {
            codigo = codigo.replaceAll("\\D", "");

            if (codigo.length() % 2 != 0) {
                codigo = "0" + codigo;
            }
            if (codigo.length() % 2 != 0) {
                throw new RuntimeException("Erro: O código gerado não possui número par de caracteres.");
            }

            Barcode barcode = BarcodeFactory.createInt2of5(codigo, false);
            barcode.setBarWidth(2);
            barcode.setBarHeight(60);
            barcode.setDrawingText(false);

            BufferedImage imagem = new BufferedImage(barcode.getWidth(), barcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
            barcode.draw((Graphics2D) imagem.getGraphics(), 0, 0);

            new File(caminho).getParentFile().mkdirs();

            String timestamp = String.valueOf(System.currentTimeMillis());
            String novoCaminho = caminho.replace(".png", "_" + timestamp + ".png");
            ImageIO.write(imagem, "png", new File(novoCaminho));

            return novoCaminho;
        } catch (OutputException | IOException e) {
            e.printStackTrace();
            return null;
        } catch (BarcodeException e) {
            throw new RuntimeException(e);
        }
    }

    public static String gerarCodigoBase(String banco, Calendar vencimento, double valor) {
        String moeda = "9"; // Real
        int fatorVencimento = (int) ((vencimento.getTimeInMillis() - getDataBase().getTimeInMillis()) / (1000 * 60 * 60 * 24));

        long valorCentavos = Math.round(valor * 100);
        String valorFormatado = String.format("%010d", valorCentavos); // valor em centavos

        String campoSemDV = banco + moeda + fatorVencimento + valorFormatado;

        return banco + moeda + calcularDigitoVerificador(campoSemDV)
                + String.format("%04d", fatorVencimento) + valorFormatado;
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
