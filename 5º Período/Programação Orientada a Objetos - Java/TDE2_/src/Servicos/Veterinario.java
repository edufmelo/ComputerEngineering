package Servicos;

import Animais.Animal;
import Interfaces.AnimalSelvagem;

public final class Veterinario {
	
	private String nome;

	public Veterinario(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void atender (Animal animal) {
		if (animal instanceof AnimalSelvagem) {
			System.out.println(this.nome + " atende apenas Pets, infelizmente não posso receber o " + animal.getNome());
		} else {
			System.out.println(this.nome + " atendendo um "+ animal.euSouUm());
			System.out.println("Vacinando " + animal.getNome());
			animal.fazerBarulho();
		}
	}
    
}
