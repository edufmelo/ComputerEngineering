import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ArrayList<Gafanhoto> g = new ArrayList<Gafanhoto>();
        Video v[] = new Video[3];
        v[0] = new Video("Aula 1 de POO");
        v[1] = new Video("Aula 2 de POO");
        v[2] = new Video("Aula 3 de POO");

        Gafanhoto g1 = new Gafanhoto("Dudu",20,"M","dudu.melo");
        Gafanhoto g2 = new Gafanhoto("Gustavo",20,"M","gusgayvsf");
        g.add(g1);
        g.add(g2);

        Visualizacao vis[] = new Visualizacao[5];
        vis[0] = new Visualizacao(g1, v[2]);
        vis[0].avaliar();
        System.out.println("\n" + vis[0].toString());
        vis[1] = new Visualizacao(g1, v[1]);
        vis[0].avaliar(87.0f);
        System.out.println("\n" + vis[1].toString());

//        System.out.println("\n---- VIDEOS ----\n");
//        System.out.println(v[0].toString());
//        System.out.println(v[1].toString());
//        System.out.println(v[2].toString());
//        System.out.println("\n---- GAFANHOTOS ----\n");
//        for (Gafanhoto gafanhoto: g){
//            System.out.println(gafanhoto.toString());
//        }
    }
}