package Animal.Canideo;

import Animal.Interfaces.AnimalEstimacao;

public final class Cao extends Canideo implements AnimalEstimacao {
    // classe final não pode ter subclasses

    public Cao(String nome, float peso) {
        super(nome, peso);
    }

    public String latir() {
        return this.getNome() + " latindo: au, au, au...";
    }

    @Override
    public String farejar() {
        return this.getNome() + " farejando algo diferente pela casa";
    }

    @Override
    public String fazerSom() {
        return latir();
    }

    @Override // do método exigido na Interface Animal.Interfaces.AnimalEstimacao
    public String irVeterinario() {
        return this.getNome() + " indo ao veterinário....";
    }

    @Override  // pois Animal.Canideo.Cao é um Animal.Interfaces.AnimalDomestico
    public String receberAlimentacao() {
        return this.getNome() + " recebendo alimentação";
    }
}
