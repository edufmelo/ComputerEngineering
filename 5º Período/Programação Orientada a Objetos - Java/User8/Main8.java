import java.util.ArrayList;
import java.util.Scanner;

public class Main8 {

    // usuários é uma variável de CLASSE ("variável global")
    private static ArrayList<User> usuarios = new ArrayList<User>();

    // métodos static são métodos de classe
    // podem ser acionados "sem uma instância"
    public static void main(String[] args) {
        // chamada aos métodos de classe (static)
        iniciarUsuarios();
        imprimirUsuarios();

        // jogar para dentro do incluir usuário
        Scanner teclado = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = teclado.nextLine();
        System.out.println("Nome digitado: " + nome);
        // ler os atributo individuais
        // criar o objeto User
        // fazer o add no usuarios


        // 1a. parte: criar um menu de opções e implementar a função adequada
        // 0 - sair
        // 1 - incluir usuario (para ler usar classe Scanner)
        // 2 - consultar usuário (fazendo procura por parte do nome)
        // 3 - listar todos usuarios OK
        // 4 - remover usuário (usando a função de procura)

        // 2a. parte: trabalha com Collections sort (comparator e comparable)
        // 5 - ordenar lista por ordem Alfabética A-Z
        // 6 - ordenar lista por ordem Alfabética Z-A

        // 3a. incluir atributo dataNascimento (LocalDate)
        // ao mostrar usuário, calcular a idade (usandod ata corrente)
        // 7 - ordenar lista por ordem cronológica ascendente
        // 8 - ordenar lista por ordem cronológica descendente
    }

    public static void iniciarUsuarios(){
        // apenas para popular a lista com alguns objetos de teste
        usuarios.add(new User("Beltrana de Tal", "beltrana.tal"));
        usuarios.add(new User("Fulano de tal", "fulano.tal"));
        usuarios.add(new User("Ciclano de tal", "ciclano.tal"));
        usuarios.add(new User("Felizberto de tal", "felizberto.tal", "CTax#@54"));
        usuarios.add(new User("Dagoberto Eugenio", "eugenio.dago"));
    }

    public static void imprimirUsuarios(){
        for (int i=0; i<usuarios.size(); i++) {
            System.out.println("Usuario "+i+": "+ usuarios.get(i));
        }
    }
}


