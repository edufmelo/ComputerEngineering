import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        // Criando uma instância da classe Calculadora
        Calculadora calc = new Calculadora();

        // Chamando os métodos e imprimindo os resultados
        System.out.println("1 - Soma");
        System.out.println("2 - Subtração");
        System.out.println("3 - Multiplicação");
        System.out.println("4 - Divisão");
        System.out.println("5 - Exponenciação");

        System.out.println("Digite uma opção do menu:");
        int opcao_menu = teclado.nextInt();
        float v1, v2;
        int elevado;

        switch (opcao_menu) {
            case 1:
                System.out.println("Digite o primeiro valor: ");
                v1 = teclado.nextFloat();
                System.out.println("Digite o segundo valor: ");
                v2 = teclado.nextFloat();
                System.out.println("Soma = " + calc.soma(v1, v2));
                break;

            case 2:
                System.out.println("Digite o primeiro valor: ");
                v1 = teclado.nextFloat();
                System.out.println("Digite o segundo valor: ");
                v2 = teclado.nextFloat();
                System.out.println("Subtração = " + calc.subtracao(v1, v2));
                break;

            case 3:
                System.out.println("Digite o primeiro valor: ");
                v1 = teclado.nextFloat();
                System.out.println("Digite o segundo valor: ");
                v2 = teclado.nextFloat();
                System.out.println("Multiplicação = " + calc.multiplicacao(v1, v2));
                break;

            case 4:
                System.out.println("Digite o primeiro valor: ");
                v1 = teclado.nextFloat();
                System.out.println("Digite o segundo valor: ");
                v2 = teclado.nextFloat();
                System.out.println("Divisão = " + calc.divisao(v1, v2));
                break;

            case 5:
                System.out.println("Digite o valor da base: ");
                v1 = teclado.nextFloat();
                System.out.println("Digite o valor do expoente: ");
                elevado = teclado.nextInt();
                System.out.println("Exponenciação = " + calc.exponenciacao(v1, elevado));
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }
}
