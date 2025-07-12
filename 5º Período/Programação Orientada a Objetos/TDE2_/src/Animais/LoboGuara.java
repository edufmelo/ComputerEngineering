package Animais;

import Interfaces.AnimalExtincao;

public class LoboGuara extends Canideo implements AnimalExtincao {

	public LoboGuara(String nome, float peso) {
		super(nome, peso);
	}

	public void fazerBarulho() {
		System.out.println(getNome() + " uivando: uuuuUUUUuuuUUUU!");
	}

	public void salve_me() {
		System.out.println("Sou um lobo-guara, estou em extincao, ajude uma ONG a me salvar!");
	}

	public void atacar(Animal animal) {
		if (animal instanceof Roedor) {
			System.out.println("Atacando um roedor...");
			animal.fazerBarulho();
			System.out.println("Matando e comendo o roedor...");
		} else {
			System.out.println("Nao posso com o " +animal.getNome() + ", um "+ animal.euSouUm());
		}
		
	}

}
