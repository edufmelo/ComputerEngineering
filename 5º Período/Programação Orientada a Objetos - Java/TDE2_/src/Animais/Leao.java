package Animais;

import Interfaces.AnimalSelvagem;

public class Leao extends Felino implements AnimalSelvagem {

	public Leao (String nome) {
		super (nome);
		tipo = Felino.SELVAGEM; 		
	}

	public void fazerBarulho() {
         System.out.println(getNome() + " rugindo: Rrrr RRrrr rrrr!");
	}

	public void atacar(Animal animal) {
		System.out.println("Sou "+ this.getNome() + ", um Leao, rei da floresta...");
		if (this.getPeso() > animal.getPeso()) {
			System.out.println("Avistei um " + animal.euSouUm());
			System.out.println("Atacando...");
			animal.fazerBarulho();
			System.out.println(animal.getNome()+ " morreu. RIP.");	
			comer();
		} else {
			System.out.println("Vou deixar " + animal.getNome() + " quieto por hoje, mais forte do que eu...");
		}
	}	

	 private void comer () {
		System.out.println("Comendo...");
	}
}
