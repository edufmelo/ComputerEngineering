import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Sessao implements Comparable<Sessao>{

	private float preco;
	private String comentario;
	private Local local;
	private Filme filme;
	private LocalDateTime data;

	public Sessao(Filme filme, Local local, float preco, String comentario, int dia, int mes, int ano, int hora, int minuto) {
		this.filme = filme;
		this.local = local;
		this.preco = preco;
		this.comentario = comentario;
		this.data = LocalDateTime.of(ano, mes, dia, hora, minuto);
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getDataStrShort() {
		LocalDateTime date = getData();
		Locale locale = new Locale("pt", "BR");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy, EEE", locale);
		String text = date.format(formatter);
		// Removendo o ponto após a abreviação do mês
		text = text.replace(".", "");
		return text;
	}

	public String getDataStrShort1() {
		LocalDateTime date = getData();
		Locale locale = new Locale("pt", "BR");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM/yyyy", locale);
		String text = date.format(formatter);
		// Removendo o ponto após a abreviação do mês
		text = text.replace(".", "");
		return text;
	}

	public String getDataStrShort2() {
		LocalDateTime date = getData();
		Locale locale = new Locale("pt", "BR");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd(EEE, HH:mm)", locale);
		String text = date.format(formatter);
		// Removendo o ponto após a abreviação do mês
		text = text.replace(".", "");
		return text;
	}

	@Override
	public String toString() {
		return "Sessao{" +
				"preco=" + preco +
				", comentario='" + comentario + '\'' +
				", local=" + local +
				", filme=" + filme +
				", data=" + data +
				'}';
	}

	@Override
	public int compareTo(Sessao sessao) {
		return this.filme.getNome().compareToIgnoreCase(sessao.getFilme().getNome());
	}

	public static class ListagemOrdAva implements Comparator<Sessao> {
		@Override
		public int compare(Sessao sessao1, Sessao sessao2) {
			return sessao1.getFilme().getNota().compareTo(sessao2.getFilme().getNota());
		}
	}

	public static class ListagemOrdCro implements Comparator<Sessao> {
		@Override
		public int compare(Sessao sessao1, Sessao sessao2) {
			return sessao1.getData().compareTo(sessao2.getData());
		}
	}
}





