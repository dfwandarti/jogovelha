import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Bem vindo ao jogo da velha");
    }
}