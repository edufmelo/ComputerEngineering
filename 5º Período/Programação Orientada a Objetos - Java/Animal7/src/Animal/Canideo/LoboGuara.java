package Animal.Canideo;

import Animal.Interfaces.AnimalExtincao;

public final class LoboGuara extends Canideo implements AnimalExtincao {

    public LoboGuara(String nome, float peso) {
        super(nome, peso);
    }

    public String uivar() {
        return this.getNome() + " uivando especial: auu, auu!!!!!";
    }
    @Override
    public String fazerSom() {
        return uivar();
    }

    @Override
    public String farejar() {
        return this.getNome() + " farejando um novo caminho para me proteger";
    }

    @Override // da interface Animal.Interfaces.AnimalExtincao
    public String seProteger() {
        return this.getNome() + " procurando abrigo, me escondendo dos predadores naturais e humanos";
    }

    @Override  // da interface animal Selvagem
    public String cacar() {
        return this.getNome() + " cacando outro animal pequeno, preferencialmente uma lebre...";
    }
}
