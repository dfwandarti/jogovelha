public enum Jogada {
    NENHUMA,
    PESSOA,
    COMPUTADOR;


    public char letraParaTabuleiro() {
        if (this == NENHUMA) return ' ';
        if (this == PESSOA) return 'O';
        return 'X';
    }
}
