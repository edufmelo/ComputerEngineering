package Animal.Canideo;

import Animal.Animal;

public abstract class Canideo extends Animal {

    public Canideo(String nome, float peso) {
        super(nome, peso);
    }

    public final String esconderComida(){
        // precisou chamar getPeso pois peso e nome estão como private
        if (this.getPeso() < 20F)
            return this.getNome() + " escondendo ossinho....";
        return this.getNome() + " escondendo os restos de comida....";
    }

    // todos os 'filhos' devem implementar farejar
    public abstract String farejar();
}
