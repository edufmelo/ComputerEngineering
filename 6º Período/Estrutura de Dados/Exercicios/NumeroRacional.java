public class NumeroRacional {

    private int num;
    private int den;

    public NumeroRacional() {
        this.num = 0;
        this.den = 1;
    }
    public NumeroRacional(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominador não pode ser zero.");
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
    private int mdc(int numerador, int denominador) {
        while (denominador != 0) {
            int resto = numerador % denominador;
            numerador = denominador;
            denominador = resto;
        }
        return numerador;
    }

    public void simplifica() {
        int divisor = mdc(Math.abs(num), Math.abs(den));
        num = num / divisor;
        den = den / divisor;

        if (den < 0) {
            num = -num;
            den = -den;
        }
    }
}
