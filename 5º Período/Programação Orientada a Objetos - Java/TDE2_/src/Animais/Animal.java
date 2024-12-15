package Animais;

public abstract class Animal {

	private String nome;
	private float peso;
	private boolean acordado;
	
	protected Animal() {
		super();
		nome = "Sem Nome";
		peso = 0;
		acordado  = false;
	}
	
	protected Animal(String nome) {
		super();
		this.nome = nome;
		peso = 0;
		acordado  = false;
	}	
	
	protected Animal(String nome, float peso) {
		super();
		this.nome = nome;
		this.peso = peso;
		acordado  = false;
	}	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public void dormir() {
		acordado = false;
		System.out.println(nome + " esta dormindo");
	}
	
	public void acordar() {
		acordado = true;
		System.out.println(nome + " esta acordado");
	}
	
	public boolean isAcordado() {
		return acordado;
	}
	
	public String toString() {
		return "Animal [nome=" + nome + "]";
	}
	
	public String euSouUm () {
		return this.getClass().getSimpleName();
	}
	
	public abstract void fazerBarulho();
	
}
