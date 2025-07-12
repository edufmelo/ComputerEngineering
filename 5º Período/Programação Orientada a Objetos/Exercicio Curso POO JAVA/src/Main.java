public class Main {
    public static void main(String[] args){
        Pessoa[] p = new Pessoa[2];
        Livro[] l = new Livro[3];

        p[0] = new Pessoa("Pedro",22,"M");
        p[1] = new Pessoa("Maria",25,"F");

        l[0] = new Livro("Aprendendo Java","José da Silva", 300, p[0]);
        l[1] = new Livro("POO para iniciantes","Pedrin Zika do baile", 500, p[1]);
        l[2] = new Livro("Java Avançado","Mariazinha zika", 800, p[0]);


        l[0].abrir();
        l[0].folhear(100);
        l[0].avancarPag();
        l[1].abrir();
        l[1].folhear(200);
        l[1].voltarPag();
        System.out.println(l[0].detalhes());
        System.out.println(l[1].detalhes());
    }
}
