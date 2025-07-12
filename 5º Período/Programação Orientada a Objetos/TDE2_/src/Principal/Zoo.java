package Principal;

import java.util.ArrayList;
import java.util.Iterator;

import Animais.*;

import Servicos.Veterinario;

import Interfaces.AnimalExtincao;
import Interfaces.AnimalSelvagem;

public class Zoo {

	public static void main(String[] args) {
		ArrayList<Animal> zoo = new ArrayList<Animal>();

		Cao cao1 = new Cao("Pluto", 40.8F); 		
		Cao cao2 = new Cao("Scooby-Doo", 53.9F);
		Cao cao3 = new Cao ("Snoopy", 21F);
		Gato gato1 = new Gato("Manda-chuva");
		Gato gato2 = new Gato("Garfield");
		Leao leao1 = new Leao("Symba");
		LoboGuara lg1 = new LoboGuara ("Feioso", 35F);
		
		Leao leao2 = new Leao("Scar");
		leao2.setPeso(87F);
		Animal esquilo = new Esquilo("Esquilinho fofo");
		
		zoo.add(cao1);
		zoo.add(cao2);
		zoo.add(cao3);
		zoo.add(gato1);
		zoo.add(gato2);
		zoo.add(leao2);
		zoo.add(lg1);

		int tamanho =  zoo.size();
		System.out.println("O zoo neste momento tem " + tamanho + " animais");

		// loop 1
		System.out.println("\n==> Loop 1, for com size e get:");
		for (int i = 0; i < tamanho; i++) {
			System.out.println(zoo.get(i).toString());
		}

		// loop 2
		Veterinario vet1 = new Veterinario("Dr. Pet");
		Iterator<Animal> it = zoo.iterator();
		System.out.println("\n==> Loop 2, while com iterator:");		
		while (it.hasNext()) {
			Animal a = it.next();
			System.out.println("-------");
			System.out.println("Sou o "+a.getNome() + " da classe " + a.euSouUm());
			System.out.println("Estou indo ao consultorio do " + vet1.getNome());
			vet1.atender(a);
			System.out.println("Consulta encerrada!");	
			System.out.println(a.getNome() + " retornado pro seu cantinho.");
			a.dormir();
		}
		System.out.println("-------\n");
		
		// loop 3
		System.out.println("==> Loop 3, for each:");
		for (Animal a : zoo) {
			if (a instanceof AnimalExtincao) {
				AnimalExtincao ae = (AnimalExtincao)a;
				ae.salve_me();
			}
			if (a instanceof AnimalSelvagem) {
				AnimalSelvagem as = (AnimalSelvagem)a;
				as.atacar(esquilo);
				as.atacar(leao1);
			}
		}	
	}
}







