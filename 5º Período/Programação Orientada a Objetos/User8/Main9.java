import java.time.LocalDate;
import java.util.*;

public class Main9 {

    // usuários é uma variável de CLASSE (a mesma para todas instâncias > "variável global")
    private static ArrayList<User> usuarios = new ArrayList<User>();
    private static Scanner teclado = new Scanner(System.in);

    // métodos static são métodos de classe
    // podem ser acionados "sem uma instância"
    public static void main(String[] args) {
        int opcao;
        // chamada ao métodos de classe (método static)
        iniciarUsuarios(); // apenas para popular o ArrayList com instancias de User para teste
        do {
            System.out.println("\n\n----- Menu principal -----\n");
            System.out.println("0 - sair");
            System.out.println("1 - incluir usuario");
            System.out.println("2 - consultar usuario");
            System.out.println("3 - listar usuarios");
            System.out.println("4 - remover usuario");
            System.out.println("5 - ordenar lista, nomes A-Z");
            System.out.println("6 - ordenar lista, nomes Z-A");
            System.out.println("7 - ordenar lista por idade ascendente");
            System.out.println("8 - ordenar lista por idade descendente");
            System.out.print("\nOpcao? ");
            opcao = teclado.nextInt();
            teclado.nextLine(); // remover entre do buffer de teclado
            switch (opcao){
                case 1:
                    usuarios.add(lerUsuario());
                    break;
                case 2:
                    consultarUsuario();
                    break;
                case 3:
                    imprimirUsuarios();
                    break;
                case 4:
                    removerUsuario();
                    break;
                case 5:
                    // sort é método de ordenação static implementado na classe Collections
                    // ele chama o compareTo que compara objetos da mesma classe
                    // que é exigido para todas que implementarem a interface Comparable
                    Collections.sort(usuarios); // temos de A - Z ORDEM NATURAL
                    // ver sort em: https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
                    break;
                case 6:
                    // nesta opcao o sort recebe um comparator como 2o. parametro
                    // reverseOrder implementado na collection inverte a ordem natural
                    // portanto aqui temos Z - A // inverso da ordem natural
                    Collections.sort(usuarios, Collections.reverseOrder());
                    break;
                case 7:
                    // usar um compare que implemente método exigido pela interface comparator
                    // ver: https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html
                    // ver: https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
                    Collections.sort(usuarios, new Comparator<User>(){
                        @Override
                        public int compare(User user1, User user2) {
                            return user1.getIdade() - user2.getIdade(); // negativo, 0 ou positivo
                            // poderia testar empate (diferença é igual a 0) e dai usar o critério alfabético, por exemplo
                        }
                    });
                    break;
                case 8:
                    Collections.sort(usuarios, new Comparator<User>(){
                        @Override
                        public int compare(User user1, User user2) {
                            return user2.getIdade() - user1.getIdade(); // negativo, 0 ou positivo
                        }
                    });
                    break;
            }
        } while (opcao != 0);
        teclado.close(); // fechando o Scanner
    }

    // TO DO LIST----------------------------------------------------
    // 3a. incluir atributo dataNascimento (LocalDate)
    // ao mostrar usuário, calcular a idade (usandod ata corrente)


    public static void iniciarUsuarios(){
        // apenas para popular a lista com alguns objetos de teste
        usuarios.add(new User("Beltrana de Tal", "beltrana.tal", LocalDate.of(2000, 2, 28)));
        usuarios.add(new User("Fulano de tal", "fulano.tal", 15, 1, 2005));
        usuarios.add(new User("Ciclano de tal", "ciclano.tal", 26, 3, 2000));
        usuarios.add(new User("Xilverston de tal", "xilverston.tal", 4, 1, 2010));
        usuarios.add(new User("Felizberto de tal", "felizberto.tal", 29, 8, 1999));
        usuarios.add(new User("Alana de Tal", "alana.tal", 24, 12, 2023));
        usuarios.add(new User("Dagoberto Eugenio", "eugenio.dago", 7, 7, 2001));

    }

    public static void imprimirUsuarios(){
        System.out.println("\n\n>> Listar usuarios <<\n");
        // também podemos usar o Iterator: https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
        // explicação do design patter: https://refactoring.guru/pt-br/design-patterns/iterator
        Iterator<User> iterator = usuarios.iterator();
        // devolve um iterator para objetos User constantes no arraylist usuario
        while (iterator.hasNext()){
            User user = iterator.next();
            System.out.println("Usuario: "+ user);
            // para apagar >> iterator.remove();
        }
    }

    public static User lerUsuario() {
        System.out.println("\n\n>> Incluir usuario <<\n");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        User user = new User(nome);
        System.out.print("Nome digitado: " + nome);
        return user;
    }

    public static User procurarUsuario(){
        System.out.print("Qual nome procurar: ");
        String userName = teclado.nextLine();
        for(User user: usuarios){
            if (user.getUserName().toUpperCase().contains(userName.toUpperCase())){
                System.out.print("Encontrei: "+user.getUserName());
                System.out.print("\nUsuario procurado <S/N>: ");
                String opcao = teclado.nextLine();
                if (opcao.toUpperCase().charAt(0) == 'S')
                    return user;
            }
        }
        return null;
    }

    public static void consultarUsuario(){
        System.out.println("\n\n>> Consultar usuario <<\n");
        User user = procurarUsuario();
        if (user != null) {
            System.out.println("Nome........: " + user.getUserName());
            System.out.println("Login name..: " + user.getUserLogin());
        } else {
            System.out.println("Usuário não cadastrado!");
        }
    }

    public static void removerUsuario(){
        System.out.println("\n\n>> Remover usuario <<\n");
        User user = procurarUsuario();
        if (user != null) {
            System.out.print("Quer remover o contato " + user.getUserName() + " <S/N>: ");
            String opcao = teclado.nextLine();
            if (opcao.toUpperCase().charAt(0) == 'S')
                usuarios.remove(user);
        } else {
            System.out.println("Usuário não cadastrado!");
        }
    }
}


