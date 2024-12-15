
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Lista implements Iterable<Sessao>{

	private ArrayList<Sessao> lista;

	public Lista() {
		super();
		lista = new ArrayList<Sessao>();
	}

	public Sessao getSessao(int i){
		return lista.get(i);
	}
	public void setSessao(Sessao sessao) {
		lista.add(sessao);
	}


	public void append(Sessao sessao) {
		lista.add(sessao);
	}

	public void sortAZ(){
		Collections.sort(lista);
	}

	public void sortOrdCro() {
		Collections.sort(lista, new Sessao.ListagemOrdCro());
	}

	public void sortOrdAva() {
		Collections.sort(lista, new Sessao.ListagemOrdAva());
	}


	@Override
	public Iterator<Sessao> iterator() {
		return lista.iterator();
	}

	public void startSessionList() {

		Filme filme01 = new Filme("Todos Menos Você",Avaliacao.EXCELENTE, false, "Filme romantico muito bom e " + "engracado, uma comedia romantica que vale a pena ver.");
		Filme filme02 = new Filme("Homem-Aranha: Sem Volta para Casa", Avaliacao.EXCELENTE,true, "Um dos melhores filmes que ja vi na minha vida, " + "tem muitas referencias e traz muita nostalgia.");
		Filme filme03 = new Filme("Elementos", Avaliacao.BOM,false, "Filme de animacao muito fofo que mostra que os opostos tambem podem se atrair.");
		Filme filme04 = new Filme("Kung Fu Panda 4",Avaliacao.RUIM, false, "Filme divertido, porem o quarto filme da saga na minha opiniao e o mais “fraco” de todos, " + "mas esta opiniao nao e apenas formada por aspectos tecnicos, mas tambem por aspectos sentimentais");

		Local local01 = new Local("Shopping Sao Jose");
		Local local02 = new Local("Shopping Palladium");
		Local local03 = new Local("Shopping & Sports");

		lista.add(new Sessao(filme01, local01, 12.99f, "Fui com duas amigas e pedimos pipoca.", 25, 1, 2024, 12, 30));
		lista.add(new Sessao(filme02, local01, 15.00f, "A sessao fazia muito barulho toda vez que tinha algo nostalgico.", 16, 12, 2021, 14, 4));
		lista.add(new Sessao(filme03, local03, 21.99f, "Ficamos sentados perto de uma criança que nos incomodou.", 15, 6, 2023, 16, 30));
		lista.add(new Sessao(filme04, local02, 44.00f, "A sessao estava tranquila.", 21, 3, 2024, 22, 0));
	}

	public Sessao getSessao() {
		return null;
	}

}





