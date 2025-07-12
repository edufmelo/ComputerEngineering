public class Calculadora {
    private float v1, v2;

    public void setV1(float v1) {
        this.v1 = v1;
    }

    public float getV1() {
        return v1;
    }

    public void setV2(float v2) {
        this.v2 = v2;
    }

    public float getV2() {
        return v2;
    }

    public float soma(float v1, float v2) {
        return v1 + v2;
    }

    public float subtracao(float v1, float v2) {
        return v1 - v2;
    }

    public float multiplicacao(float v1, float v2) {
        return v1 * v2;
    }

    public float divisao(float v1, float v2) {
        if (v2 != 0) {
            return v1 / v2;
        } else {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
    }

    public float exponenciacao(float v1, double elevado) {
        double resultado;
        resultado = Math.pow(v1, elevado);
        return (float) resultado;
    }
}
