import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class JogoVelha {
    Tabuleiro tabuleiro = new Tabuleiro();
    Jogada jogadorGanhador = null;


    public static void main(String[] args) throws Exception {
        new JogoVelha().jogar();
    }

    public void jogar() throws Exception {
        imprimeBoasVindas();
        loopPrincipal();
        imprimeResultadoFinal();
    }

    private void loopPrincipal() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        while (posicoesLivresSuficientes() && !haJogadorGanhador()) {
            imprimeTabuleiro();
            jogadaPessoa(scanner);
            jogadaComputador(rand);
            verificaGanhador();
        }
    }

    private boolean posicoesLivresSuficientes() {
        return tabuleiro.numPosicoesLivres() > 1;
    }

    private boolean haJogadorGanhador() {
        return this.jogadorGanhador != null;
    }

    private static void imprimeBoasVindas() throws InterruptedException, IOException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Bem vindo ao jogo da velha");
        System.out.println("Você joga com O, eu jogo com X.\n");
    }

    private void imprimeResultadoFinal() {
        imprimeTabuleiro();

        if (this.jogadorGanhador == null) {
            System.out.println("O jogo acabou por falta de jogadas.");
        } else {
            if (this.jogadorGanhador == Jogada.PESSOA) {
                System.out.println("Você ganhou. Parabéns!");
            } else {
                System.out.println("Ha, perdeu para um computador com zero inteligência!");
            }
        }
    }

    private void jogadaComputador(Random rand) throws Exception {
        int randInt = rand.nextInt(this.tabuleiro.numPosicoesLivres() - 1);
        int posicaoJogadaComputador = this.tabuleiro.getPosicoesLivres()[randInt];

        tabuleiro.novaJogada(posicaoJogadaComputador, Jogada.COMPUTADOR);
    }

    private void jogadaPessoa(Scanner scanner) throws Exception {
        System.out.print("Digite a posição de sua jogada (1-9): ");
        int posicaoJogadaPessoa = scanner.nextInt() - 1;

        if (this.tabuleiro.isPosicaoLivre(posicaoJogadaPessoa)) {
            tabuleiro.novaJogada(posicaoJogadaPessoa, Jogada.PESSOA);
        } else {
            System.out.print("Posição invalida! ");
            jogadaPessoa(scanner);
        }
    }

    private void imprimeTabuleiro() {
        System.out.println(" ");
        imprimeLinhaTabuleiro(0);
        imprimeLinhaDivisora();
        imprimeLinhaTabuleiro(1);
        imprimeLinhaDivisora();
        imprimeLinhaTabuleiro(2);
    }

   private static void imprimeLinhaDivisora() {
        System.out.println("---|---|---");
    }
    
    private void imprimeLinhaTabuleiro(int linha) {
        char[] separador = new char[] {'|', '|', '\n'};
        int[] posicoes = new int[] {linha * 3, linha * 3 + 1, linha * 3 + 2};

        for(int i = 0; i < posicoes.length; i++) {
            System.out.printf(" %c %c", this.tabuleiro.getJogada(posicoes[i]).letraParaTabuleiro(), separador[i]);
        }
    }

    private void verificaGanhador() {
        int[][] carreirasValidasParaVerificacao = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {6, 4, 2},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

        for(int[] carreira: carreirasValidasParaVerificacao) {
            this.jogadorGanhador = verificaGanhadorCarreira(carreira[0], carreira[1], carreira[2]);
            if (this.jogadorGanhador != null) return;
        }
    }

    private Jogada verificaGanhadorCarreira(int pos1, int pos2, int pos3) {
        if (jogadorFechouCarreira(Jogada.PESSOA, pos1, pos2, pos3)) return Jogada.PESSOA;
        if (jogadorFechouCarreira(Jogada.COMPUTADOR, pos1, pos2, pos3)) return Jogada.COMPUTADOR;
        return null;
    }

    private boolean jogadorFechouCarreira(Jogada pessoa, int pos1, int pos2, int pos3) {
        return tabuleiro.getJogada(pos1) == pessoa &&
                tabuleiro.getJogada(pos2) == pessoa &&
                tabuleiro.getJogada(pos3) == pessoa;
    }
}
