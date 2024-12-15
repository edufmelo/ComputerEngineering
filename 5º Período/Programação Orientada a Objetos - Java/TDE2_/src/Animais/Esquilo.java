package Animais;

public class Esquilo extends Roedor {

    public Esquilo(String nome) {
        super(nome);
    }

    @Override
    public void fazerBarulho() {
        System.out.println(getNome() + " fazendo barulho: squick squick...");
    }
}
