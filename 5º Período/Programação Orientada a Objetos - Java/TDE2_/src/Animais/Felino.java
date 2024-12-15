package Animais;

public abstract class Felino extends Animal {

	protected int tipo;
	
	final static int DOMESTICADO = 1;
	final static int SELVAGEM = 2;
	
	public Felino () {
		super ();
	}
	
	public Felino (String nome) {
		super (nome);
	}
	
	public Felino (String nome, float peso) {
		super (nome, peso);
	}
	
}
