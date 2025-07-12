package Animal.Felino;

import Animal.Interfaces.AnimalSelvagem;

// classe final não pode ter subclasses
public final class Leao extends Felino implements AnimalSelvagem {
    public Leao(String nome, float peso) {
        super(nome, peso);
        this.tamanho = Felino.GRANDE; // GRANDE é atributo de CLASSE
    }

    public String rugir() {
        return this.getNome() + " rugindo: roar, grrr, roar, grrr...";
    }
    @Override
    public String fazerSom() {
        return rugir();
    }

    @Override // do método abstrato definido na Animal.Felino.Felino
    public String ronronar() {
        return this.getNome() + " ronronando alto e ameaçador, sou o maior felino!!!";
    }

    @Override  // da interface animal Selvagem
    public String cacar() {
        return this.getNome() + " cacando outro animal, qualquer um ta valendo...";
    }
}
