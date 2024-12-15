public enum Avaliacao {
    RUIM(1),
    REGULAR(2),
    BOM(3),
    MUITO_BOM(4),
    EXCELENTE(5);

    private int nota;

    Avaliacao(int nota) {
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}


