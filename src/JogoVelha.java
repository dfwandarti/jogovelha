import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoVelha {
    int[] tabuleiro;
    int jogadorGanhador = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        new JogoVelha().jogar();
    }

    public void jogar() throws IOException, InterruptedException {
        this.tabuleiro = new int[9];
        List<Integer> posicoesLivres = new ArrayList<>(9);
        Scanner s = new Scanner(System.in);
        Random rand = new Random();

        for(int i = 0; i < 9; i++) {
            posicoesLivres.add(i);
        }

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Bem vindo ao jogo da velha");
        System.out.println("Você joga com O, eu jogo com X.\n");

        while (posicoesLivres.size() > 1 && this.jogadorGanhador == 0) {
            System.out.printf(" %c | %c | %c %n", this.pecaJogada(0), this.pecaJogada(1), this.pecaJogada(2));
            System.out.println("---|---|---");
            System.out.printf(" %c | %c | %c %n", this.pecaJogada(3), this.pecaJogada(4), this.pecaJogada(5));
            System.out.println("---|---|---");
            System.out.printf(" %c | %c | %c %n", this.pecaJogada(6), this.pecaJogada(7), this.pecaJogada(8));
            System.out.print("Digite a posição de sua jogada (1-9): ");
            int jogadaPessoa;
            do {
                jogadaPessoa = s.nextInt() - 1;
            } while (!posicoesLivres.contains(jogadaPessoa));
            tabuleiro[jogadaPessoa] = 1;
            posicoesLivres.remove((Integer) jogadaPessoa);

            int randInt = rand.nextInt(posicoesLivres.size() - 1);
            int jogadaMinha = posicoesLivres.get(randInt);
            tabuleiro[jogadaMinha] = 2;
            posicoesLivres.remove(randInt);

            this.verificaGanhador();
        }

        if (this.jogadorGanhador != 0) {
            if (this.jogadorGanhador == 1) {
                System.out.println("Você ganhou. Parabéns!");
            } else {
                System.out.println("Ha, perdeu para um computador com zero inteligência!");
            }
        } else {
            System.out.println("O jogo acabou por falta de jogadas.");
        }
    }

    private void verificaGanhador() {
        this.verificaGanhadorCarreira(0, 1, 2);
        this.verificaGanhadorCarreira(3, 4, 5);
        this.verificaGanhadorCarreira(6, 7, 8);
        this.verificaGanhadorCarreira(0, 4, 8);
        this.verificaGanhadorCarreira(6, 4, 2);
        this.verificaGanhadorCarreira(0, 3, 6);
        this.verificaGanhadorCarreira(2, 4, 7);
        this.verificaGanhadorCarreira(3, 5, 8);
    }

    private void verificaGanhadorCarreira(int pos1, int pos2, int pos3) {
        if (this.tabuleiro[pos1] == 1 && this.tabuleiro[pos2] == 1 && this.tabuleiro[pos3] == 1) {
            this.jogadorGanhador = 1;
        }
        if (this.tabuleiro[pos1] == 2 && this.tabuleiro[pos2] == 2 && this.tabuleiro[pos3] == 2) {
            this.jogadorGanhador = 2;
        }
    }

    public char pecaJogada(int posicao) {
        if (this.tabuleiro[posicao] == 1) return 'O';
        if (this.tabuleiro[posicao] == 2) return 'X';
        return ' ';
    }
}