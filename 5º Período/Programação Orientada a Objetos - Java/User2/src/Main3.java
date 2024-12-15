public class Main3 {
    public static void main(String[] args) {
        User usu1 = new User();
        User usu2 = new User("Fulano de tal");
        System.out.println(usu1);
        System.out.println(usu2);

        // usando um SETTER para ALTERAR o nome de usu1
        usu1.setUserName("Beltrana de Tal");
        // usando GETTER para CONSULTAR um atributo
        System.out.println(usu1.getUserName());
        System.out.println(usu2.getUserName());
    }
}
