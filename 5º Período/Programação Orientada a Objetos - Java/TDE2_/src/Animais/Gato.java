package Animais;

import Interfaces.AnimalEstimacao;

public class Gato extends Felino implements AnimalEstimacao {

	public Gato() {
		super();
		tipo = Felino.DOMESTICADO; 
	}

	public Gato(String nome) {
		super (nome);
		tipo = Felino.DOMESTICADO; 		
	}

	public void fazerBarulho() {
		System.out.println(getNome() +  " miando: miau, miau, miiiaauu...");
	}

	public void irPetShop() {
		System.out.println(getNome()+ " vai ao Petshop.");
	}	

}
