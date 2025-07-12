package Animal.Felino;

import Animal.Interfaces.AnimalEstimacao;

// Animal.Felino.Gato é uma classe filha, ela é subclasse de Animal.Animal
// extends indica o processo de HERANCA
public final class Gato extends Felino implements AnimalEstimacao {
    // classe final não pode ter subclasses

    public Gato(String nome, float peso) {
        // aciona o construtor da superclasse
        // que é o construtor da Animal.Animal
        super(nome, peso);
        this.tamanho = Felino.PEQUENO; // tamanho, declarado na superclasse Animal.Felino.Felino
        // é protected, portanto Animal.Felino.Gato e Animal.Felino.Leao podem alterar
    }

    @Override // do método abstrato definido na Animal.Felino.Felino
    public String ronronar() {
        return this.getNome() + " ronronando de leve, sou felino de pequeno porte!";
    }

    public String miar() {
        return this.getNome() + " miando: miau, miau, miau...";
    }

    @Override
    public String fazerSom() {
        return miar();
    }

    public String seLamber() {
        return this.getNome() + " se lambendo...!";
    }

    @Override // pois Animal.Felino.Gato é um Animal.Interfaces.AnimalEstimacao
    public String irVeterinario() {
        return this.getNome() + " indo ao veterinario....";
    }

    @Override  // pois Animal.Felino.Gato é um Animal.Interfaces.AnimalDomestico
    public String receberAlimentacao() {
        return this.getNome() + " recebendo alimentacao";
    }
}
