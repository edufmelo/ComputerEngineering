public class Main2 {
    public static void main(String[] args) {
        // declaração e construtor na mesma linha
        // o java decide qual método construtor chamar
        // verificando a assinatura
        User usu1 = new User();
        User usu2 = new User("Fulano de tal");
        System.out.println(usu1.toString());
        // pode omitir toString, pois é chamado de forma transparente
        System.out.println(usu2);

    }
}
