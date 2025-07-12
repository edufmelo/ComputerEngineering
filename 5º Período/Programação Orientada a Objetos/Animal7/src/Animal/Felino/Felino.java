package Animal.Felino;

import Animal.Animal;

// o fato de Animal.Felino.Felino ser abstrata faz com que o método
// fazerSom não precise ser implementado aqui,
// (re)passa a responsabilidade para as subclasses concretas
public abstract class Felino extends Animal {
    // porém, precisa de construtor compatível com superclasse Animal.Animal

    protected int tamanho; // sera Grande ou Pequeno, protected pode se usado na subclasse
    // opcao 1:
    //    tamanho é private e o construtor de Animal.Felino.Leao/Animal.Felino.Gato mandariam o argumento na chamada super
    // opcao 2 (foi a escolhida neste exemplo):
    //    declara tamanho como protected, portanto subclasses podem alterar diretamente o atributo em seus respectivos construtores


    // duas CONSTANTES de classe para usar com a variável tamanho
    protected final static int GRANDE = 0; // final para virar uma CONSTANTE
    protected final static int PEQUENO = 1; // static para ser atributo de CLASSE


    public Felino(String nome, float peso) {
        super(nome, peso);
    }

    // opcao 1:
    //   poderia fazer o método escalar ser método Abstrato de Animal.Felino.Felino
    //   portanto, usando override, Animal.Felino.Gato e Animal.Felino.Leao teriam comportamento diferentes
    // opcao 2:
    //   foi feito aqui apenas para ILUSTRAR um exemplo de método concreto válido para todas as subclasses
    //   porém customizando pela variável de instância
    public final String escalar(){
        String retorno = this.getNome();
        switch (tamanho) {
            case GRANDE:
                retorno += " escalando arvore super alta!!!";
                break;
            case PEQUENO:
                retorno += " escalando arvore pequena!!!";
                break;
        }
        return retorno;
    }

    // todos as subclasses devem implementar ronronar
    // esta é opção 1
    public abstract String ronronar();

}
