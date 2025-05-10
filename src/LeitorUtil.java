import java.util.Scanner;

public class LeitorUtil {

    public static String lerCampoComTamanho(Scanner scanner, String mensagem, int tamanhoMaximo) {
        String input;
        while (true) {
            System.out.print(mensagem);
            input = scanner.nextLine().replaceAll("[^0-9]", ""); // mantém só números
            if (input.length() <= tamanhoMaximo && input.length() > 0) {
                break;
            } else {
                System.out.println("Entrada inválida. Digite até " + tamanhoMaximo + " dígitos.");
            }
        }
        return input;
    }

    public static int lerInteiroComTamanho(Scanner scanner, String mensagem, int tamanhoMaximo) {
        String input = lerCampoComTamanho(scanner, mensagem, tamanhoMaximo);
        return Integer.parseInt(input);
    }
}
