package Main;

import Animal.Animal;
import Animal.Felino.*;
import Animal.Canideo.*;
import Animal.Interfaces.*;

import java.util.ArrayList;
import java.util.Collections;

public class Principal {
    public static void main(String[] args) {
        ArrayList<Animal> zoo = new ArrayList<Animal>();
        Animal a1 = new Gato("Garfield", 14.24F);
        Animal a2 = new Cao("Scooby-Doo", 53.98F);
        Cao a3 = new Cao("Tobby", 9.8F);
        Gato a4 = new Gato("Manda-Chuva", 4.7F);
        Leao a5 = new Leao("Simba", 38F);
        Leao a6 = new Leao("Scar", 110F);
        Lobo a7 = new Lobo("Lobo Mau", 77.5F);

        zoo.add(a1);
        zoo.add(a2);
        zoo.add(a3);
        zoo.add(a4);
        zoo.add(a5);
        zoo.add(a6);
        zoo.add(a7);
        zoo.add(new OncaPintada("Juma", 51.8F));
        zoo.add(new LoboGuara("Paranazito", 22.8F));
        zoo.add(new Lobo("Lobisomen", 99.88F));
        zoo.add(new LoboGuara("Caicara", 21.6F));
        zoo.add(new OncaPintada("Pintada", 92.4F));

        Collections.sort(zoo); // vai ordenar pela ordem natural
        // do compareTo feito em Animal.Animal usando o atributo nome
        printZoo(zoo);
    }

    public static void printZoo(ArrayList<Animal> z) {
        for (Animal a: z) {

            // acesso geral como Animal.Animal
            System.out.println();
            System.out.println(a); // aciona toString de modo transparente
            System.out.println(a.fazerSom());
            System.out.println(a.getNome() + " sou " + a.getClass());
            // getNome vem da classe animal            getClasse vem da classe Object

            // testando se é um Animal.Felino.Felino
            if (a instanceof Felino) {
                // fazendo um type casting para tratar o objeto originalmente Animal.Animal como Animal.Felino.Felino
                // foi possivel devido ao teste feito no if é instância de....
                System.out.println(((Felino) a).escalar());

                // aproveitando a hierarquia e colocando o if do Animal.Felino.Gato dentro do bloco do Animal.Felino.Felino
                if (a instanceof Gato) { // apenas True se a for instancia de Animal.Felino.Gato
                    Gato g = (Gato) a; // objeto g é super local, escopo válido apenas dentro do if
                    System.out.println(g.seLamber());
                    System.out.println(g.miar());
                }
            }

            // testando se é um Animal.Canideo.Canideo
            if (a instanceof Canideo){
                // sem variavel local, usando casting
                System.out.println(((Canideo) a).esconderComida());
                System.out.println(((Canideo) a).farejar());
            }

            // pode-se testar INSTANCEOF com INTERFACES
            if (a instanceof AnimalSelvagem){
                System.out.println(((AnimalSelvagem) a).cacar()); // casting de interfaces
                if (a instanceof AnimalExtincao){
                    System.out.println(((AnimalExtincao) a).seProteger());
                }
            }else if (a instanceof AnimalEstimacao){
                System.out.println(((AnimalEstimacao) a).irVeterinario());
                System.out.println(((AnimalEstimacao) a).receberAlimentacao());
            }
        }
    }

}