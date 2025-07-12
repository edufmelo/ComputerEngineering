package Principal;

import Animais.*;
import Interfaces.AnimalSelvagem;
import Servicos.Veterinario;
 
public class TestaAnimal {

	public static void main(String[] args) {

		Cao cao2 = new Cao("Scooby-Doo", 53.9F);
		System.out.println("\n" + cao2.toString());
		cao2.acordar();
		
		Cao cao3 = new Cao ("Snoopy", 8.5F);
		System.out.println("\n" + cao3.toString());
		cao3.acordar();
		cao3.latir();
		
		Gato gato1 = new Gato();
		gato1.setNome("Manda-chuva");
		System.out.println("\n" + gato1.toString());
		gato1.acordar();
		gato1.irPetShop();
		
		Gato gato2 = new Gato("Garfield");
		System.out.println("\n" + gato2.toString());
		gato2.dormir();
		gato2.fazerBarulho();
	
		Leao leao1 = new Leao("Symba");
		System.out.println("\n" + leao1.toString());
		leao1.acordar();
		leao1.fazerBarulho();
		
		Animal[] zoo = new Animal[5];
		zoo[0] = cao2;
		zoo[1] = cao3;
		zoo[2] = gato1;
		zoo[3] = leao1;
		zoo[4] = new Leao("Mufaza");		
		
		System.out.println("\n\nVetor de tamanho "+ zoo.length);
		for (int i = 0; i < zoo.length; i++) {
			System.out.println("\n" + zoo[i].toString());
			zoo[i].fazerBarulho();	
			if (zoo[i] instanceof Canideo) {
				System.out.println("Tambem gosto de osso!");
			    Canideo c = (Canideo)zoo[i];
			    c.esconderOsso();
			}			
		}	
		

	}
}















