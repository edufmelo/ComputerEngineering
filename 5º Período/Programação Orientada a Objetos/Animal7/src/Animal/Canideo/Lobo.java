// esta salvo no diretório Canideo, subpasta de Animal
package Animal.Canideo;
// as classes foram orgnizadas logicamente dentro de packages
// um projeto real tem sua organização impactada pela arquitetura de software adotada (MVC, MVVM, Clean etc)
// além da organização lógica do ecossistema de classes de domínio da aplicação

// classe final não pode ter subclasses
public final class Lobo extends Canideo {
    public Lobo(String nome, float peso) {
        super(nome, peso);
    }

    public String uivar() {
        return this.getNome() + " uivando: auuu, auu, auuuuu...";
    }
    @Override
    public String fazerSom() {
        return uivar();
    }

    @Override
    public String farejar() {
        return this.getNome() + " farejando pistas de um predador inimigo";
    }

    /*
    @Override
    public String toString() {
        return "Fiz besteira, redefini a toString";
    }

    As subclasses de Animal.Animal não podem redefinir toString
    pois foi definido como final
     */
}
