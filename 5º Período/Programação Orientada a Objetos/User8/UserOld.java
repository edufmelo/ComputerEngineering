public class UserOld {
    // atibutos non-static são variáveis de INSTANCIA
    private String userName; // nome do usuário
    private String userLogin; // login do usuário
    private String userPswd; // senha do usuário

    // SOBRECARGA do construtor serve entre outros para AUMENTAR a FLEXIBILIDADE da classe
    public UserOld() { // usuário genérico de testes
        userName = "Sem nome"; // atribuindo valor default para evitar null da String
        userLogin = "Sem login";
        userPswd = "";
    }
    public UserOld(String userName) {
        this.userName = userName;
        userLogin = "Sem login";
        userPswd = "";
    }
    // papel do this é diferencia o identificador do parâmetro do identificador do atributo
    public UserOld(String userName, String userLogin) {
        this.userName = userName;
        this.userLogin = userLogin;
        userPswd = "";
    }
    public UserOld(String userName, String userLogin, String userPswd) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPswd = userPswd;
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

    public String getUserPswd() {
        return userPswd;
    }
    //trocar para resetar senha (gera uma senha provisoria)
    //fazer outro mét

    public void setUserPswd(String userPswd) {
        //precisa de uma série de regras de negócio para validar a senha recebida
        this.userPswd = userPswd;
    }

    @Override
    // + é um operador sobrecarregado, pode servir de concatenação ou de soma
    public String toString() {
        return userName + "(" + userLogin + ")";
    }

}