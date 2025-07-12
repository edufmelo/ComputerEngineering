package Animais;

public abstract class Roedor extends Animal {

	public Roedor(String nome) {
		super(nome);
	}

	public void fazerBarulho() {
		System.out.println(getNome() + " chiando: quic quic quir rr.");
	}

}
