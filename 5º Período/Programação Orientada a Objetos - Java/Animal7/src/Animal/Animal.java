package Animal;

// classe abstrata não pode ser instanciada
public abstract class Animal implements Comparable<Animal>{
    private String nome;
    private float peso;
    // poderia mudar para protected, liberando acesso direto para as subclasse
    // optar por deixar encapsulado

    // mesmo sendo uma classe abstrata, permanece o construtor para atender
    // ao super dos construtores das suas subclasses
    public Animal(String nome, float peso) {
        this.nome = nome;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    // este método não consegue ser específico o suficiente
    // não dá para fazer um som genérico válido para todos os animais da hierarquia
    // public String fazerSom(){
    //    return nome + " emitindo som";
    // }
    // porém é importante que todos os animais fossem obrigados a emitir som
    // o que é feito mediante a definição de um método abstrato
    public abstract String fazerSom();
    // método abstrato nao tem corpo, serve para obrigar a subclasse a fazer override

    // para próxima classes: https://www.dicio.com.br/sons-de-animais/


    @Override
    public int compareTo(Animal animal) {
        return this.nome.compareToIgnoreCase(animal.nome);
    }

    @Override
    public final String toString() {
        // método final não pode ser redefinido nas subclasse
        return "Sou o '" + nome + "' e peso " + peso + " kg.";
    }
}
