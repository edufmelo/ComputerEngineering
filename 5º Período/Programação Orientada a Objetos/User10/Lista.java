import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

// REFATORACAO: encapsular a lista de usuários que passou a ter um nome
// e os métodos necessários para gerência da lista
// e o métodos que ordenam a lista nos mais diversos critérios
// ordem natural (usando compareTo da interface comparable)
// e outras (usando compare, via classe anônima, da interface comparator)
public class Lista implements Iterable<User>{
    private ArrayList<User> lista;
    private String name; // titulo da lista

    public Lista(String name) {
        lista = new ArrayList<User>(); // cria a lista vazia
        this.name = name;
    }

    public void append(User user){
        // uma estrutura de dados própria do programador
        // teria toda a lógica para decidir e colocar o novo user no ponto certo
        // como aqui usamos um arraylist, basta:
        lista.add(user);
    }

    public void delete(User user){
        lista.remove(user);
    }

    // exemplo de sobrecarga, assinaturas diferentes, mesmo nome
    public void delete(int position){
        lista.remove(position);
    }

    public void sortAZ(){
        // sort é método de ordenação static implementado na classe Collections
        // ele chama o compareTo que compara objetos da mesma classe
        // que é exigido para todas que implementarem a interface Comparable
        Collections.sort(lista); // temos de A - Z ORDEM NATURAL
        // ver sort em: https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
    }

    public void sortZA(){
        // nesta opcao o sort recebe um comparator como 2o. parametro
        // reverseOrder implementado na collection inverte a ordem natural
        // portanto aqui temos Z - A // inverso da ordem natural
        Collections.sort(lista, Collections.reverseOrder());
    }

    public void sortAgeAsc(){
        // usar um compare que implemente método exigido pela interface comparator
        // ver: https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
        // ver: https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
        Collections.sort(lista, new Comparator<User>(){
            @Override
            public int compare(User user1, User user2) {
                return user1.getIdade() - user2.getIdade(); // negativo, 0 ou positivo
                // poderia testar empate (diferença é igual a 0) e dai usar o critério alfabético, por exemplo
            }
        });
    }

    public void sortAgeDesc(){
        Collections.sort(lista, new Comparator<User>(){
            @Override
            public int compare(User user1, User user2) {
                return user2.getIdade() - user1.getIdade(); // negativo, 0 ou positivo
            }
        });
    }
    // redefinição da iterator que é o método exigido pela interface Iterable
    @Override
    public Iterator<User> iterator() {
        // como usamos arraylist como estrutura interna nesta classe para o atribuito lista
        // simplemente devolvemos o iterator já implementado na ArrayList
        return lista.iterator();
    }

    public void startUsersList(){
        // apenas para popular a lista com alguns objetos de teste
        lista.add(new User("Beltrana de Tal", "beltrana.tal", LocalDate.of(2000, 2, 28)));
        lista.add(new User("Fulano de tal", "fulano.tal", 15, 1, 2005));
        lista.add(new User("Ciclano de tal", "ciclano.tal", 26, 3, 2000));
        lista.add(new User("Xilverston de tal", "xilverston.tal", 4, 1, 2010));
        lista.add(new User("Felizberto de tal", "felizberto.tal", 29, 8, 1999));
        lista.add(new User("Alana de Tal", "alana.tal", 24, 12, 2023));
        lista.add(new User("Dagoberto Eugenio", "eugenio.dago", 7, 7, 2001));
    }
}
