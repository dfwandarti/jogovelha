import java.util.Arrays;
import java.util.stream.IntStream;

public class Tabuleiro {
    Jogada[] tabuleiro;

    public Tabuleiro() {
        tabuleiro = new Jogada[9];
        Arrays.fill(tabuleiro, Jogada.NENHUMA);
    }

    public void novaJogada(int posicao, Jogada jogada) throws Exception {
        if (tabuleiro[posicao] != Jogada.NENHUMA) throw new Exception("Jogada em posicao já preenchida.");

        this.tabuleiro[posicao] = jogada;
    }

    public Jogada getJogada(int posicao) {
        return this.tabuleiro[posicao];
    }

    public int numPosicoesLivres() {
        return getPosicoesLivres().length;
    }

    public int[] getPosicoesLivres() {
        return IntStream.range(0, 9).filter(pos -> tabuleiro[pos] == Jogada.NENHUMA).toArray();
    }

    public boolean isPosicaoLivre(int posicao) {
        return this.tabuleiro[posicao] == Jogada.NENHUMA;
    }
}
