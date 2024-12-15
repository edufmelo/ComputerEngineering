public class ContaBanco {
    // atributos
    public int numConta;
    protected String tipo;
    private String dono;
    private float saldo;
    private boolean status;
    // metodos
    public void currentState(){
        System.out.println("------------------------------");
        System.out.println("Conta: " + this.getNumConta());
        System.out.println("Tipo: " + this.getTipo());
        System.out.println("Dono: " + this.getDono());
        System.out.println("Saldo: " + this.getSaldo());
        System.out.println("Status: " + this.getStatus());
    }
    public void abrirContar(String t) {
        this.setTipo(t);
        this.setStatus(true);
        if (t == "CC"){
            this.setSaldo(50);
        } else if (t == "CP") {
            this.setSaldo(150);
        }
        System.out.println("Conta aberta com sucesso!");
    }
    public void fecharConta(){
        if(this.getSaldo() > 0){
            System.out.println("Conta nao pode ser fechada, pois ainda tem dinheiro!");
        } else if (this.getSaldo() < 0) {
            System.out.println("Conta nao pode ser fechada, pois tem debito!");
        } else {
            this.setStatus(false);
            System.out.println("Conta fechada com sucesso!");
        }
    }
    public void depositar(float v){
        if(this.getStatus()) {
            this.setSaldo(getSaldo() + v);
            System.out.println("Deposito realizado de " + v + " na conta de " + this.getDono());
        } else {
            System.out.println("Impossivel depositar em uma conta fechada!");
        }
    }
    public void sacar(float v){
        if (this.getStatus()){
            if(this.getSaldo() >= v) {
                this.setSaldo(this.getSaldo() - v);
                System.out.println("Saque realizado de " + v + " na conta de " + this.getDono());
            } else {
                System.out.println("Saldo insuficiente para saque de " + v);
            }
        } else {
            System.out.println("Impossivel sacar com conta fechada.");
        }
    }
    public void pagarMensal(){
        int v = 0;
        if (this.getTipo() == "CC"){
            v = 12;
        } else if (this.getTipo() == "CP") {
            v = 20;
        }
        if (this.getStatus()) {
            this.setSaldo(this.getSaldo() - v);
            System.out.println("Mensalidade paga com sucesso por " + this.getDono());
        } else System.out.println("Impossivel pagar com uma conta fechada!");
    }

    public ContaBanco() {
        this.setSaldo(0);
        this.setStatus(false);
    }
    public void setNumConta(int n){
        this.numConta = n;
    }
    public int getNumConta(){
        return this.numConta;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
