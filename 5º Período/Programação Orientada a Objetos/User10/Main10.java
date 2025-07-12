import java.time.LocalDate;
import java.util.*;

public class Main10 {

    private static Lista usuarios = new Lista("Lista de amigos do trabalho");
    private static Scanner teclado = new Scanner(System.in);

    // métodos static são métodos de classe
    // podem ser acionados "sem uma instância"
    public static void main(String[] args) {
        int opcao;
        // chamada ao métodos de classe (método static)
        usuarios.startUsersList(); // apenas para popular o ArrayList com instancias de User para teste
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
                    usuarios.append(lerUsuario());
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
                    usuarios.sortAZ();
                    break;
                case 6:
                    usuarios.sortZA();
                    break;
                case 7:
                    usuarios.sortAgeAsc();
                    break;
                case 8:
                    usuarios.sortAgeDesc();
                    break;
            }
        } while (opcao != 0);
        teclado.close(); // fechando o Scanner
    }

        // TO DO LIST----------------------------------------------------
        // 3a. incluir atributo dataNascimento (LocalDate)
        // ao mostrar usuário, calcular a idade (usando data corrente)

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
        System.out.print("Data de nascimento (dd/mm/aaaa): ");
        String dataNasc = teclado.nextLine();
        String dataSplit[] = dataNasc.split("/"); // usa o separador / para obter um vetor de 3 posições
        System.out.print("Nome digitado: " + nome);
        int dia = Integer.valueOf(dataSplit[0]);
        int mes = Integer.valueOf(dataSplit[1]);
        int ano = Integer.valueOf(dataSplit[2]);
        return new User(nome, dia, mes, ano);
    }

    public static User procurarUsuario(){
        System.out.print("Qual nome procurar: ");
        String userName = teclado.nextLine();
        // for each disponivel pois Lista implementa iterable (ou seja, método iterator)
        for(User user: usuarios){
            if (user.getUserName().toUpperCase().contains(userName.toUpperCase())){
                System.out.print("Encontrei: " + user.getUserName());
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
                usuarios.delete(user);
        } else {
            System.out.println("Usuário não cadastrado!");
        }
    }
}


