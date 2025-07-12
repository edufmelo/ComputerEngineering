import java.util.ArrayList;

public class Main6 {
    public static void main(String[] args) {
        User usu1 = new User("Beltrana de Tal", "beltrana.tal");
        User usu2 = new User("Fulano de tal", "fulano.tal");
        User usu3 = new User("Ciclano de tal", "ciclano.tal");
        User usu4 = new User("Felizberto de tal", "felizberto.tal", "CTax#@54");

        ArrayList<User> usuarios = new ArrayList<User>();
        usuarios.add(usu1);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(0).getUserName());

        usuarios.add(usu2);
        System.out.println(usuarios.size()); //size >> tamanho do array
        System.out.println(usuarios.get(1).getUserLogin());

        usuarios.add(new User("Dagoberto Eugenio", "eugenio.dago"));
        System.out.println(usuarios.size()); //size >> tamanho do array
        System.out.println(usuarios.get(2).getUserLogin());
    }
}
