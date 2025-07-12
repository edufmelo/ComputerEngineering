package Animal.Felino;

import Animal.Interfaces.AnimalExtincao;

public class OncaPintada extends Felino implements AnimalExtincao {

    public OncaPintada(String nome, float peso) {
        super(nome, peso);
    }

    public String bramar(){
        return getNome() + " bramando: grrr, roar, grrr, roar!!!";
    }
    @Override
    public String fazerSom() {
        return bramar(); // lembrar que neste caso o this é opcional
    }

    @Override // do método abstrato definido na Animal.Felino.Felino
    public String ronronar() {
        return getNome() + " ronronando forte, sou o maior felino das Americas";
    }

    @Override // da interface Animal.Interfaces.AnimalExtincao
    public String seProteger() {
        return this.getNome() + " procurando abrigo, me escondendo dos cacadores";
    }

    @Override  // da interface animal Selvagem
    public String cacar() {
        return this.getNome() + " cacando outro de medio porte...";
    }
}
