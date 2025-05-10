import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeradorDePdfUtil {
    public static void gerarPdf(Boleto boleto, String caminhoPdf) throws Exception {
        new File("./boletos/").mkdirs();

        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, Files.newOutputStream(Paths.get(caminhoPdf)));
        doc.open();

        // Logo e título
        Image logo = Image.getInstance(boleto.getLogotipo());
        logo.scaleToFit(100, 50);
        logo.setAlignment(Image.ALIGN_LEFT);
        doc.add(logo);

        Font fonteTitulo = new Font(Font.HELVETICA, 10, Font.BOLD);
        Font fontePadrao = new Font(Font.HELVETICA, 8);
        Font fonteNegrito = new Font(Font.HELVETICA, 8, Font.BOLD);
        Font fonteDigitavel = new Font(Font.COURIER, 12, Font.BOLD);

        Paragraph titulo = new Paragraph("BOLETO BANCÁRIO - BANCO " + boleto.getClass().getSimpleName().replace("Boleto", "").toUpperCase(), fonteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        doc.add(titulo);
        doc.add(new Paragraph(" "));

        // Recibo do sacado
        PdfPTable recibo = new PdfPTable(6);
        recibo.setWidthPercentage(100);
        recibo.setWidths(new float[]{2.5f, 3f, 2.5f, 2.5f, 3f, 2.5f});

        recibo.addCell(celula("Cedente", fonteNegrito));
        recibo.addCell(celula(boleto.getCedente(), fontePadrao));
        recibo.addCell(celula("Agência/Código Cedente", fonteNegrito));
        recibo.addCell(celula(boleto.getAgencia() + "/" + boleto.getConta(), fontePadrao));
        recibo.addCell(celula("Data do Documento", fonteNegrito));
        recibo.addCell(celula(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), fontePadrao));

        recibo.addCell(celula("Sacado", fonteNegrito));
        recibo.addCell(celula(boleto.getSacado(), fontePadrao));
        recibo.addCell(celula("Nosso Número", fonteNegrito));
        recibo.addCell(celula(String.valueOf(boleto.getNossoNumero()), fontePadrao));
        recibo.addCell(celula("Carteira", fonteNegrito));
        recibo.addCell(celula(String.valueOf(boleto.getCarteira()), fontePadrao));

        recibo.addCell(celula("CPF/CNPJ Cedente", fonteNegrito));
        recibo.addCell(celula(boleto.getCedenteCad(), fontePadrao));
        recibo.addCell(celula("CPF/CNPJ Sacado", fonteNegrito));
        recibo.addCell(celula(boleto.getSacadoCad(), fontePadrao));
        recibo.addCell(celula("Vencimento", fonteNegrito));
        recibo.addCell(celula(new SimpleDateFormat("dd/MM/yyyy").format(boleto.getVencimento().getTime()), fontePadrao));

        recibo.addCell(celula("Valor Documento", fonteNegrito));
        recibo.addCell(celula("R$ " + String.format("%.2f", boleto.getValor()), fontePadrao));
        recibo.addCell(celula("Uso do Banco", fonteNegrito));
        recibo.addCell(celula(" ", fontePadrao));
        recibo.addCell(celula("Espécie", fonteNegrito));
        recibo.addCell(celula("R$", fontePadrao));

        doc.add(recibo);
        doc.add(new Paragraph(" "));

        // Linha de corte
        Paragraph corte = new Paragraph("Corte aqui ─────────────────────────────────────────────────────────────────────────────────", fontePadrao);
        corte.setAlignment(Element.ALIGN_CENTER);
        doc.add(corte);
        doc.add(new Paragraph(" "));

        // Linha digitável
        Paragraph linhaDigitavel = new Paragraph(boleto.getCodigoDeBarrasDigitavel(), fonteDigitavel);
        linhaDigitavel.setAlignment(Element.ALIGN_CENTER);
        doc.add(linhaDigitavel);
        doc.add(new Paragraph(" "));

        // Ficha de compensação (repete os campos)
        PdfPTable ficha = new PdfPTable(6);
        ficha.setWidthPercentage(100);
        ficha.setWidths(new float[]{2.5f, 3f, 2.5f, 2.5f, 3f, 2.5f});

        ficha.addCell(celula("Cedente", fonteNegrito));
        ficha.addCell(celula(boleto.getCedente(), fontePadrao));
        ficha.addCell(celula("Agência/Código Cedente", fonteNegrito));
        ficha.addCell(celula(boleto.getAgencia() + "/" + boleto.getConta(), fontePadrao));
        ficha.addCell(celula("Nosso Número", fonteNegrito));
        ficha.addCell(celula(String.valueOf(boleto.getNossoNumero()), fontePadrao));

        ficha.addCell(celula("Vencimento", fonteNegrito));
        ficha.addCell(celula(new SimpleDateFormat("dd/MM/yyyy").format(boleto.getVencimento().getTime()), fontePadrao));
        ficha.addCell(celula("Valor Documento", fonteNegrito));
        ficha.addCell(celula("R$ " + String.format("%.2f", boleto.getValor()), fontePadrao));
        ficha.addCell(celula("Carteira", fonteNegrito));
        ficha.addCell(celula(String.valueOf(boleto.getCarteira()), fontePadrao));

        doc.add(ficha);
        doc.add(new Paragraph(" "));

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

    // Célula padrão com borda
    private static PdfPCell celula(String conteudo, Font fonte) {
        PdfPCell cell = new PdfPCell(new Phrase(conteudo, fonte));
        cell.setBorder(Rectangle.BOX);
        cell.setPadding(5f);
        return cell;
    }

}