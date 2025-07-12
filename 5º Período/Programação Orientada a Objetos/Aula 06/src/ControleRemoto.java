public class ControleRemoto implements Controlador {
    private int volume;
    private boolean ligado;
    private boolean tocando;

    public ControleRemoto() {
        this.volume = 50;
        this.ligado = true;
        this.tocando = false;
    }

    public boolean getLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public boolean getTocando() {
        return tocando;
    }

    public void setTocando(boolean tocando) {
        this.tocando = tocando;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    // METODOS ABSTRATOS
    @java.lang.Override
    public void ligar() {
        this.setLigado(true);
    }

    @java.lang.Override
    public void desligar() {
        this.setLigado(false);
    }

    @java.lang.Override
    public void abrirMenu() {
        System.out.println("----- MENU -----");
        System.out.println("Está ligado? " + this.getLigado());
        System.out.println("Está tocando? " + this.getTocando());
        System.out.println("Volume: " + this.getVolume());
        for (int i = 10; i <= this.getVolume(); i+=10) {
            System.out.print("|");
        }
    }


    @java.lang.Override
    public void fecharMenu() {
        System.out.println("\nFechando Menu...");
    }

    @java.lang.Override
    public void maisVolume() {
        if(this.getLigado()) {
            this.setVolume(this.getVolume()+5);
        }
    }

    @java.lang.Override
    public void menosVolume() {
        if(this.getLigado()) {
            this.setVolume(this.getVolume()-5);
        }
    }

    @java.lang.Override
    public void ligarMudo() {
        if(this.getLigado() && this.getVolume() > 0) {
            this.setVolume(0);
        }
    }

    @java.lang.Override
    public void desligarMudo() {
        if (this.getLigado() && this.getVolume() == 0) {
            this.setVolume(50);
        }
    }

    @java.lang.Override
    public void play() {
        if (this.getLigado() && !(this.getTocando())) {
            this.setTocando(true);
        }
    }

    @java.lang.Override
    public void pause() {
        if (this.getLigado() && this.getTocando()){
            this.setTocando(false);
        }
    }


}
