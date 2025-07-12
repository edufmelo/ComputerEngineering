import java.io.Serializable;
import java.time.LocalDate;

class Filme implements Comparable<Filme>{

	private String nome;
	private Avaliacao nota;
	private boolean favorito;
	private String comentario;


	public Filme(String nome, Avaliacao nota, boolean favorito, String comentario) {
		this.nome = nome;
		this.nota = nota;
		this.favorito = favorito;
		this.comentario = comentario;;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}

	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Avaliacao getNota() {
		return nota;
	}
	public void setNota(Avaliacao nota) {
		this.nota = nota;
	}

	@Override
	public int compareTo(Filme filme) {
		return this.nome.compareToIgnoreCase(filme.getNome());
	}
}



