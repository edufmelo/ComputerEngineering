import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pessoa implements Comparable<Pessoa>{
    private String nome;
    private LocalDate dataNascimento;
    private Endereco endRes;

    public Pessoa(String nome) {
        super();
        this.nome = nome;
    }

    public Pessoa(String nome, LocalDate dataNasc) {
        super();
        this.nome = nome;
        this.dataNascimento = dataNasc;
    }

    public Pessoa(String nome, int dia, int mes, int ano) {
        this(nome, LocalDate.of(ano, mes, dia));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasc() {
        return dataNascimento;
    }

    public String getDataNascString() {
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatoBr);
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNascimento = dataNasc;
    }

    public int getIdade() {
        int idade;
        LocalDate hoje = LocalDate.now();
        Period tempo = Period.between(dataNascimento, hoje);
        idade = tempo.getYears();
        return idade;
    }

    public Endereco getEndRes() {
        return endRes;
    }

    public void setEndRes(Endereco endRes) {
        this.endRes = endRes;
    }

    public String toString() {
        return "Eu sou " + nome;
    }

    public String getEtiqueta() {
        if (endRes == null)
            return nome + "\nSem endereco cadastrado!";
        return nome + "\n" + endRes.toString();
    }

    @Override
    public int compareTo(Pessoa pessoa) {
        return nome.compareToIgnoreCase(pessoa.getNome());
    }

}
