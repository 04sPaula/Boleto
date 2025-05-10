import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class GeradorDePdfUtil {

    public static void gerarPdf(Boleto boleto, String caminhoPdf) throws Exception {
        new File("./boletos/").mkdirs();

        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, Files.newOutputStream(Paths.get(caminhoPdf)));
        doc.open();

        Font fonteTitulo = new Font(Font.HELVETICA, 10, Font.BOLD);
        Font fontePadrao = new Font(Font.HELVETICA, 8);

        doc.add(new Paragraph("BOLETO BANCÁRIO - BANCO " + boleto.getClass().getSimpleName().replace("Boleto", "").toUpperCase(), fonteTitulo));
        doc.add(new Paragraph(" "));

        PdfPTable tabela = new PdfPTable(2);
        tabela.setWidthPercentage(100);
        tabela.setSpacingAfter(10);

        tabela.addCell(celula("Cedente: ", fontePadrao));
        tabela.addCell(celula(boleto.getCedente() + " - " + boleto.getCedenteCad(), fontePadrao));

        tabela.addCell(celula("Sacado: ", fontePadrao));
        tabela.addCell(celula(boleto.getSacado() + " - " + boleto.getSacadoCad(), fontePadrao));

        tabela.addCell(celula("Valor: ", fontePadrao));
        tabela.addCell(celula("R$ " + String.format("%.2f", boleto.getValor()), fontePadrao));

        tabela.addCell(celula("Vencimento: ", fontePadrao));
        tabela.addCell(celula(new java.text.SimpleDateFormat("dd/MM/yyyy").format(boleto.getVencimento().getTime()), fontePadrao));

        tabela.addCell(celula("Nosso Número: ", fontePadrao));
        tabela.addCell(celula(String.valueOf(boleto.getNossoNumero()), fontePadrao));

        tabela.addCell(celula("Agência/Conta: ", fontePadrao));
        tabela.addCell(celula(boleto.getAgencia() + " / " + boleto.getConta(), fontePadrao));

        tabela.addCell(celula("Carteira: ", fontePadrao));
        tabela.addCell(celula(String.valueOf(boleto.getCarteira()), fontePadrao));

        doc.add(tabela);

        // Código de barras
        BufferedImage barcodeImg = ImageIO.read(new File(boleto.getCodigoDeBarras()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(barcodeImg, "png", baos);
        Image codigoImg = Image.getInstance(baos.toByteArray());
        codigoImg.scalePercent(80);
        codigoImg.setAlignment(Image.ALIGN_CENTER);
        doc.add(codigoImg);

        doc.close();
    }

    private static PdfPCell celula(String conteudo, Font fonte) {
        PdfPCell cell = new PdfPCell(new Phrase(conteudo, fonte));
        cell.setBorder(Rectangle.BOX);
        return cell;
    }
}