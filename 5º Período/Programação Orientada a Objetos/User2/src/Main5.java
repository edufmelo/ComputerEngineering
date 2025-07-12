public class Main5 {
    public static void main(String[] args) {
        // exemplo de uso dos 4 construtores (FLEXIBILIDADE)
        User usu1 = new User();
        usu1.setUserName("Beltrana de Tal");
        User usu2 = new User("Fulano de tal");
        usu2.setUserName("fulano.tal");
        User usu3 = new User("Ciclano de tal", "ciclano.tal");
        User usu4 = new User("Felizberto de tal", "felizberto.tal", "CTax#@54");

        System.out.println(usu1); // toString de forma transparente
        System.out.println(usu2);
        System.out.println(usu3);
        System.out.println(usu4);

        System.out.println("Nome: " + usu4.getUserName());
        System.out.println("Login: " + usu4.getUserLogin());
        System.out.println("Senha: " + usu4.getUserPswd());
        usu4.setUserPswd("XC&%uv_98I");
        System.out.println("Senha Alterada: " + usu4.getUserPswd());
    }
}
