package Animais;

public class Cao extends Canideo {

	public Cao(String nome, float peso) {
		super(nome, peso);
	}

	public void latir() {
		System.out.println(getNome() + " latindo: au au au...");
	}

	public void fazerBarulho() {
		latir(); 
	}

}
