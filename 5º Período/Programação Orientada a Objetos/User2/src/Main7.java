import java.util.ArrayList;
import java.util.Iterator;

public class Main7 {
    public static void main(String[] args) {
        User usu1 = new User("Beltrana de Tal", "beltrana.tal");
        User usu2 = new User("Fulano de tal", "fulano.tal");
        User usu3 = new User("Ciclano de tal", "ciclano.tal");
        User usu4 = new User("Felizberto de tal", "felizberto.tal", "CTax#@54");

        ArrayList<User> usuarios = new ArrayList<User>();
        usuarios.add(usu1);
        usuarios.add(usu2);
        usuarios.add(usu3);
        usuarios.add(usu4);
        usuarios.add(new User("Dagoberto Eugenio", "eugenio.dago"));

        // loop para imprimir todos os usuarios incluidos na lista
        // ABORDAGEM 1: loop tradicional com contador
        System.out.println("Loop tradicional");
        for (int i=0; i<=usuarios.size()-1; i++) {
            System.out.println(usuarios.get(i));
        }

        // ABORDAGEM 2: loop foreach, viável pois ArrayList implementa a interface Iterable
        System.out.println("Loop for each");
        for (User user:usuarios) {
            System.out.println(user);
        }

        // ABORDAGEM 3: loop while usando métodos do iterator
        Iterator<User> iterator = usuarios.iterator();
        System.out.println("Loop usando iterator");
        while (iterator.hasNext()){
            User user = iterator.next();
            System.out.println(user);
        }
    }
}


