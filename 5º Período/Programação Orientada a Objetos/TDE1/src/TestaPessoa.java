import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;

public class TestaPessoa {

    public static void main(String[] args) {
        Pessoa p1 = new Pessoa("Marie Curie");
        Pessoa p2 = new Pessoa ("Albert Einstein", LocalDate.of(1879, Month.MARCH, 14));
        Pessoa p3 = new Pessoa ("James Maxwell", 13, 6, 1831);

        p1.setDataNasc(LocalDate.of(1867, Month.NOVEMBER, 7));

        System.out.println("--------------------------------------------");
        Pessoa p4 = new Pessoa("Fulano de Tal");
        Endereco e = new Endereco("Av. das Palmeiras", 98, "Curitiba", "PR", "85123-789");
        p4.setEndRes(e);
        System.out.println(p4.getEtiqueta());

        Pessoa[] vet = new Pessoa[5];
        vet[0] = p1;
        vet[1] = p2;
        vet[2] = p3;
        System.out.println("\n--------------------------------------------");
        for (int i = 0; i < vet.length; i++) {
            if (vet[i] != null) {
                System.out.println(vet[i]);
                System.out.println("Nasci em " +vet[i].getDataNascString());
                System.out.println("Eu teria agora " +vet[i].getIdade() + " anos");
                System.out.println("---------------");
            }
        }

        ArrayList<Pessoa> alPessoa = new ArrayList<Pessoa>();
        alPessoa.add(p1);
        alPessoa.add(p2);
        alPessoa.add(p3);
        alPessoa.add(new Pessoa ("Isaac Newton", LocalDate.of(1642, Month.DECEMBER, 25)));
        alPessoa.add(new Pessoa ("Stephen Hawking", LocalDate.of(1942, Month.JANUARY, 8)));
        alPessoa.add(new Pessoa ("Richard Feynman", LocalDate.of(1918, Month.MAY, 11)));
        alPessoa.add(new Pessoa ("Linus Pauling", LocalDate.of(1901, Month.FEBRUARY, 28)));
        alPessoa.add(new Pessoa ("Antoine Lavoisier", LocalDate.of(1743, Month.AUGUST, 26)));
        alPessoa.add(new Pessoa ("Carl Sagan", LocalDate.of(1934, Month.NOVEMBER, 9)));
        alPessoa.add(new Pessoa ("Ernest Rutherford", LocalDate.of(1871, Month.AUGUST, 30)));
        alPessoa.add(new Pessoa ("Dmitri Mendeleiev", LocalDate.of(1834, Month.FEBRUARY, 8)));
        alPessoa.add(new Pessoa("Dudu", LocalDate.of(2004,Month.MARCH,12)));

        mostraLista(alPessoa);

        Collections.sort(alPessoa);
        mostraLista(alPessoa);

        Collections.sort(alPessoa, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa p1, Pessoa p2) {
                return Integer.compare(p1.getIdade(), p2.getIdade());
            }
        });
        mostraLista(alPessoa);

    }

    private static void mostraLista(ArrayList<Pessoa> lista){
        System.out.println("\n--------------------------------------------");
        Iterator<Pessoa> it = lista.iterator();
        while (it.hasNext()) {
            Pessoa pessoa = it.next();
            System.out.println(pessoa.getNome() + " (" + pessoa.getIdade() + ")");
        }
    }
}
