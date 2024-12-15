package Animais;

public abstract class Canideo extends Animal {

	protected Canideo(String nome, float peso) {
		super(nome, peso);
	}		
	
	public final void esconderOsso() {
		System.out.println(this.getNome() + " escondendo osso");
	}
	
}
