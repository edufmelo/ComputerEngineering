public class NumeroRacional {

    private int num;
    private int den;

    public NumeroRacional() {
        this.num = 0;
        this.den = 1;  // Default para evitar divisão por zero
    }

    // Construtor com numerador e denominador
    public NumeroRacional(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominador não pode ser zero."); // Lancar erro caso denominador = 0
        }
        this.num = num;
        this.den = den;
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    public NumeroRacional soma(NumeroRacional other) {
        int novoNum = this.num * other.den + other.num * this.den;
        int novoDen = this.den * other.den;
        return new NumeroRacional(novoNum, novoDen);
    }

    public NumeroRacional subtrai(NumeroRacional other) {
        int novoNum = this.num * other.den - other.num * this.den;
        int novoDen = this.den * other.den;
        return new NumeroRacional(novoNum, novoDen);
    }

    public NumeroRacional multiplica(NumeroRacional other) {
        int novoNum = this.num * other.num;
        int novoDen = this.den * other.den;
        return new NumeroRacional(novoNum, novoDen);
    }

    public NumeroRacional divide(NumeroRacional other) {
        if (other.num == 0) {
            throw new ArithmeticException("ERRO! Não é possível realizar divisão por zero.");
        }
        int novoNum = this.num * other.den;
        int novoDen = this.den * other.num;
        return new NumeroRacional(novoNum, novoDen);
    }

    // Método para encontrar o Máximo Divisor Comum (MDC)
    private int mdc(int numerador, int denominador) {
        while (denominador != 0) {
            int resto = numerador % denominador;
            numerador = denominador;
            denominador = resto;
        }
        return numerador;
    }

    // Método para simplificar a fração
    public void simplifica() {
        int divisor = mdc(Math.abs(num), Math.abs(den));
        num = num / divisor;
        den = den / divisor;

        // Garante que o denominador seja positivo (mesmo realizado em PY)
        if (den < 0) {
            num = -num;
            den = -den;
        }
    }
}
