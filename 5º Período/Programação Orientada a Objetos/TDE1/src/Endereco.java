
public class Endereco {
    private String logradouro;
    private int num;
    private String comp;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int num, String cidade, String estado, String cep) {
        super();
        this.logradouro = logradouro;
        this.num = num;
        this.comp = "";
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco(String logradouro, int num, String comp, String cidade, String estado, String cep) {
        super();
        this.logradouro = logradouro;
        this.num = num;
        this.comp = comp;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String toString() {
        return logradouro + ", " + num + " " + comp + "\n"
                + cidade + " - " + estado + "\n"
                + cep;
    }

}