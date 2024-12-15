public class Main1 {
    public static void main(String[] args) {
        // programa principal, aqui começa a execução do projeto
        User usu1; // declarou uma variável local da classe User

        // ao chamar NEW estamos acionado o CONSTRUTOR da classe
        usu1 = new User(); // criando a instância da classe
        // se a classe NAO possuir construtor, o java fornece um vazio

        System.out.println("Usuario: " + usu1); // + está concatenando
        // é igual a fazer, imprimir objeto é chamar toString de forma transparente
        System.out.println("Usuario: " + usu1.toString());
    }
}
