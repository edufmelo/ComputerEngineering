public class Caneta {
    public String modelo;
    private float ponta;
    private boolean tampada;
    private String cor;

    public Caneta(String modelo, float ponta, String cor) {
        this.modelo = modelo;
        this.ponta = ponta;
        this.cor = cor;
    }

    public float getPonta() {
        return ponta;
    }

    public boolean isTampada() {
        return tampada;
    }

    public String getCor() {
        return cor;
    }

    public String getModelo() {
        return modelo;
    }
}
