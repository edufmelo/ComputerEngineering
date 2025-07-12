import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// implements é para implementar uma interface
// uma interface é uma lista de métodos abstratos que devem ser implementados
// mantem-se o mesmo cabeçalho para ocorrer redefinição
// ver sobre interfaces em: https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
// ver sobre comparable em: https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
public class User implements Comparable<User> {
    // atibutos non-static são variáveis de INSTANCIA
    private String userName; // nome do usuário
    private String userLogin; // login do usuário
    private LocalDate dataNasc; // data de nascimento do usuario, não tem 1/1/1900
    // ver: https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html

    public User() {
        // this. diferencia o identificador do parâmetro do identificador do atributo
        // this() chama o construtor da própria classe
        // portanto, a sobrecarga de construtor pode empregar chamadas internas
        this("Sem nome","Sem login");
        // algo mais de inicialização se necessário fosse
    }

    public User(String userName) {
        this(userName, "Sem login");
    }

    public User(String userName, String userLogin) {
        this (userName, userLogin, LocalDate.of(1900, 1, 1)); // data default 1/1/1900
    }

    public User(String userName, String userLogin, LocalDate dataNasc) {
        // esta chamada ocorre no início do construtor de forma transparente
        super(); // chamada ao construtor da superclasse, neste caso a Object
        this.userName = userName;
        this.userLogin = userLogin;
        this.dataNasc = dataNasc;
    }

    public User(String userName, String userLogin, int dia, int mes, int ano) {
        super();
        this.userName = userName;
        this.userLogin = userLogin;
        this.dataNasc = LocalDate.of(ano, mes, dia);
    }

    public User(String userName, int dia, int mes, int ano) {
        String userLogin, user[];
        user = userName.toLowerCase().split(" ");
        userLogin = user[0] + "." + user[user.length-1]; // sem verificar se já tem na base de users login
        this.userName = userName;
        this.userLogin = userLogin;
        this.dataNasc = LocalDate.of(ano, mes, dia);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdade(){
        // ver: https://docs.oracle.com/javase/8/docs/api/java/time/Period.html
        LocalDate hoje = LocalDate.now(); // data de agora
        Period diferenca = Period.between(dataNasc, hoje); // periodo entre as duas datas
        return diferenca.getYears(); // anos da diferenca corresponde a idade
    }

    public String getDataNascString() {
        // ver: https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNasc.format(formatoBr);
    }

    @Override  // da Object
    // redefinição do toString da classe Objetc, superclasse de toda hierarquia
    // + é um operador sobrecarregado, pode servir de concatenação ou de soma
    public String toString() {
        return userName + " (" + userLogin + "), " +
                getDataNascString() + ": " + getIdade() + " ano(s)";
    }

    @Override // da interface COMPARABLE
    // redefinição do método compareTo abstrato definido na interface Comparable
    public int compareTo(User user) {
        // implementar uma comparação, pos, 0 ou neg para maior, igual ou menor
        // escolhe-se a ordem natural, ou seja, o programdor define o atributo preferencial
        // no exemplo, nome, que é uma String, portanto pode-se usar o compare da String
        return this.userName.compareToIgnoreCase(user.userName);
        // comparacao será feita no método da classe string encapsulado aqui para comparar nomes
        // ver o método em: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
    }
}