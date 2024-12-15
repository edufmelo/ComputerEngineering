import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

public class Aplicacao {

	private static Lista sessoes = new Lista();
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		int opcao;

		sessoes.startSessionList();

		do {
			System.out.println("\n\n===== Menu principal =====\n");
			System.out.println("0 - Sair");
			System.out.println("1 - Cadastrar filme");
			System.out.println("2 - Mostrar dados de um filme");
			System.out.println("3 - Editar filme");
			System.out.println("4 - Listagem dos filmes em ordem alfabética");
			System.out.println("5 - Listagem dos filmes em ordem de avaliação");
			System.out.println("6 - Listagem dos filmes em ordem cronológica");
			System.out.println("7 - Listagem dos filmes favoritos");
			System.out.print("\nOpção? ");
			opcao = teclado.nextInt();
			teclado.nextLine();

			switch (opcao) {
				case 1:
					sessoes.append(cadastrarFilme());
					break;
				case 2:
					mostrarDadosFilme();
					break;
				case 3:
					editarFilme();
					break;
				case 4:
					sessoes.sortAZ();
					listagemAZ();
					break;
				case 5:
					listagemOrdAva();
					break;
				case 6:
					listagemOrdCro();
					break;
				case 7:
					sessoes.sortAZ();
					listagemFav();
					break;
			}
		} while (opcao != 0);
		teclado.close();
	}

	public static Sessao cadastrarFilme() {
		System.out.println("\n\n===== Incluir filme =====\n");
		System.out.print("Nome: ");
		String nome = teclado.nextLine();

		System.out.print("Data (dd/mm/aaa): ");
		String data = teclado.nextLine();
		String dataSplit[] = data.split("/");
		int dia = Integer.valueOf(dataSplit[0]);
		int mes = Integer.valueOf(dataSplit[1]);
		int ano = Integer.valueOf(dataSplit[2]);

		System.out.println("Horário (hh:mm): ");
		String hora = teclado.nextLine();
		String horaSplit[] = hora.split(":");
		int h = Integer.valueOf(horaSplit[0]);
		int m = Integer.valueOf(horaSplit[1]);


		System.out.print("Favorito (Sim/Não): ");
		String fav = teclado.nextLine();
		boolean favorito = false;
		if (fav.equalsIgnoreCase("Sim") || fav.equalsIgnoreCase("S")) {
			favorito = true;
		}

		System.out.println("|| Escolha uma nota de 1 a 5 ||\n");
		System.out.println("1 - Ruim");
		System.out.println("2 - Regular");
		System.out.println("3 - Bom");
		System.out.println("4 - Muito bom");
		System.out.println("5 - Excelente\n");
		System.out.print("Nota: ");
		int notaInt = teclado.nextInt();
		Avaliacao nota = null;
		switch (notaInt) {
			case 1:
				nota = Avaliacao.RUIM;
				break;
			case 2:
				nota = Avaliacao.REGULAR;
				break;
			case 3:
				nota = Avaliacao.BOM;
				break;
			case 4:
				nota = Avaliacao.MUITO_BOM;
				break;
			case 5:
				nota = Avaliacao.EXCELENTE;
				break;
			default:
				System.out.println("Opção inválida, nota definida como 'Regular'");
				nota = Avaliacao.REGULAR;
				break;
		}
		teclado.nextLine();

		System.out.print("Deseja comentar o filme (S/N)? ");
		String op1 = teclado.nextLine();
		String comF = null;
		if (op1.equalsIgnoreCase("S")) {
			System.out.print("Comentário do filme: ");
			comF = teclado.nextLine();
		}

		System.out.print("Preço: ");
		float preco = teclado.nextFloat();
		teclado.nextLine();

		System.out.print("Local: ");
		String locall = teclado.nextLine();

		System.out.print("Deseja comentar a sessão (S/N)? ");
		String op2 = teclado.nextLine();
		String comS = null;
		if (op2.equalsIgnoreCase("S")) {
			System.out.print("Comentário da sessão: ");
			comS = teclado.nextLine();
		}

		Filme film = new Filme(nome,nota, favorito, comF);
        Local local = new Local(locall);
		return new Sessao(film, local, preco, comS, dia, mes, ano, h, m);
	}

	public static Sessao procurarFilme() {
		System.out.print("\nNome do filme a procurar? ");
		String filme = teclado.nextLine();

		for (Sessao sessao : sessoes) {
			if (sessao.getFilme().getNome().toUpperCase().contains(filme.toUpperCase())) {
				System.out.print("\nEncontrei: " + sessao.getFilme().getNome());
				System.out.print("\nCorresponde a procura, <S/N>: ");
				String op3 = teclado.nextLine();
				if (op3.toUpperCase().charAt(0) == 'S') {
					return sessao;
				}
			}
		}
		return null;
	}

	public static void mostrarDadosFilme(){
		Sessao sessao = procurarFilme();
		System.out.println("\n===== Dados do Filme =====\n");
		if (sessao != null) {
			System.out.println("Nome: " + sessao.getFilme().getNome());
			if(sessao.getFilme().getNota() == Avaliacao.RUIM){
				System.out.println("Nota: *");
			}else if(sessao.getFilme().getNota() == Avaliacao.REGULAR){
				System.out.println("Nota: **");
			}else if(sessao.getFilme().getNota() == Avaliacao.BOM){
				System.out.println("Nota: ***");
			}else if(sessao.getFilme().getNota() == Avaliacao.MUITO_BOM){
				System.out.println("Nota: ****");
			}else if(sessao.getFilme().getNota() == Avaliacao.EXCELENTE){
				System.out.println("Nota: *****");
			}else {
				System.out.println("Nota: **");
			}

			if (sessao.getFilme().isFavorito()) {
				System.out.println("Favorito : Sim");
			} else {
				System.out.println("Favorito : Não");
			}
			System.out.println("Data: " + sessao.getDataStrShort() + " - "+sessao.getData().format(DateTimeFormatter.ofPattern("HH:mm")));
			System.out.println("Preço : R$" + sessao.getPreco());
			System.out.println("Local : " + sessao.getLocal().getNome());

			if (sessao.getFilme().getComentario() == null) {
				System.out.println("Comentário do Filme: Sem comentário");
			} else {
				System.out.println("Comentário do Filme: " + sessao.getFilme().getComentario());
			}

			if (sessao.getComentario() == null) {
				System.out.println("Comentário da Sessão: Sem comentário");
			} else {
				System.out.println("Comentário da Sessão: " + sessao.getComentario());
			}
		} else {
			System.out.println("Filme não cadastrado!");
		}
	}

	public static void editarFilme(){
		Sessao sessao = procurarFilme();
		System.out.println("\n===== Editar Filme =====\n");
		if (sessao != null) {

			System.out.print("Deseja editar a nota do filme? (S/N): ");
			String op4 = teclado.nextLine();

			if(op4.equalsIgnoreCase("S") || op4.equalsIgnoreCase("Sim")) {
				System.out.println("|| Escolha uma nota de 1 a 5 ||\n");
				System.out.println("1 - Ruim");
				System.out.println("2 - Regular");
				System.out.println("3 - Bom");
				System.out.println("4 - Muito bom");
				System.out.println("5 - Excelente\n");
				System.out.print("Nota: ");
				int notaInt = teclado.nextInt();
				Avaliacao nota = null;
				switch (notaInt) {
					case 1:
						nota = Avaliacao.RUIM;
						break;
					case 2:
						nota = Avaliacao.REGULAR;
						break;
					case 3:
						nota = Avaliacao.BOM;
						break;
					case 4:
						nota = Avaliacao.MUITO_BOM;
						break;
					case 5:
						nota = Avaliacao.EXCELENTE;
						break;
					default:
						System.out.println("Opção inválida, nota definida como 'Regular'");
						nota = Avaliacao.REGULAR;
						break;
				}
				teclado.nextLine();
				sessao.getFilme().setNota(nota);
			}else{
				System.out.println("Nota: Nota não editada");
			}
			System.out.print("Deseja editar o comentário do filme (S/N)? ");
			String op1 = teclado.nextLine();
			String comF = null;
			if (op1.equalsIgnoreCase("S")) {
				System.out.print("Comentário do filme: ");
				comF = teclado.nextLine();
				sessao.getFilme().setComentario(comF);
			}

			System.out.print("Deseja editar o comentário da sessão (S/N)? ");
			String op2 = teclado.nextLine();
			String comS = null;
			if (op2.equalsIgnoreCase("S")) {
				System.out.print("Comentário da sessão: ");
				comS = teclado.nextLine();
				sessao.setComentario(comS);
			}
		}
	}

	public static void listagemAZ(){
		System.out.println("\nListagem em ordem alfabética: \n");
		Iterator<Sessao> iterator = sessoes.iterator();
		while (iterator.hasNext()){
			Sessao sessao = iterator.next();
			System.out.println(sessao.getFilme().getNome() + " ("+ sessao.getDataStrShort()+") - " + sessao.getLocal().getNome());

		}
	}

	public static boolean temFilmesComNota(Lista lista, Avaliacao avaliacao) {
		for (Sessao sessao : lista) {
			if (sessao.getFilme().getNota() == avaliacao) {
				return true;
			}
		}
		return false;
	}

	public static void listagemOrdAva() {
		sessoes.sortAZ();
		Lista listaSessoesAva = new Lista();
		for (Sessao sessao : sessoes) {
			listaSessoesAva.append(sessao);
		}
		listaSessoesAva.sortOrdAva();
		System.out.println("\nListagem dos filmes em ordem de avaliação: ");

		if (temFilmesComNota(listaSessoesAva, Avaliacao.EXCELENTE)) {
			System.out.println("\nNota 5 (****):");
			for (Sessao sessao : listaSessoesAva) {
				if (sessao.getFilme().getNota() == Avaliacao.EXCELENTE) {
					System.out.println(sessao.getFilme().getNome() + " " + sessao.getDataStrShort() + " - " + sessao.getLocal().getNome());
				}
			}
		}

		if (temFilmesComNota(listaSessoesAva, Avaliacao.MUITO_BOM)) {
			System.out.println("\nNota 4 (****):");
			for (Sessao sessao : listaSessoesAva) {
				if (sessao.getFilme().getNota() == Avaliacao.MUITO_BOM) {
					System.out.println(sessao.getFilme().getNome() + " " + sessao.getDataStrShort() + " - " + sessao.getLocal().getNome());
				}
			}
		}

		if (temFilmesComNota(listaSessoesAva, Avaliacao.BOM)) {
			System.out.println("\nNota 3 (***):");
			for (Sessao sessao : listaSessoesAva) {
				if (sessao.getFilme().getNota() == Avaliacao.BOM) {
					System.out.println(sessao.getFilme().getNome() + " " + sessao.getDataStrShort() + " - " + sessao.getLocal().getNome());
				}
			}
		}

		if (temFilmesComNota(listaSessoesAva, Avaliacao.REGULAR)) {
			System.out.println("\nNota 2 (**):");
			for (Sessao sessao : listaSessoesAva) {
				if (sessao.getFilme().getNota() == Avaliacao.REGULAR) {
					System.out.println(sessao.getFilme().getNome() + " " + sessao.getDataStrShort() + " - " + sessao.getLocal().getNome());
				}
			}
		}

		if (temFilmesComNota(listaSessoesAva, Avaliacao.RUIM)) {
			System.out.println("\nNota 1 (*):");
			for (Sessao sessao : listaSessoesAva) {
				if (sessao.getFilme().getNota() == Avaliacao.RUIM) {
					System.out.println(sessao.getFilme().getNome() + " " + sessao.getDataStrShort() + " - " + sessao.getLocal().getNome());
				}
			}
		}
	}

	public static void listagemOrdCro(){
		sessoes.sortAZ();
		Lista listaSessoesCro = new Lista();
		for (Sessao sessao : sessoes) {
			listaSessoesCro.append(sessao);
		}
		listaSessoesCro.sortOrdCro();
		System.out.println("\nListagem dos filmes em ordem cronológica: \n");
		for (Sessao sessao : listaSessoesCro) {
			System.out.println(sessao.getDataStrShort1());
			System.out.println(sessao.getDataStrShort2() + " - " + sessao.getLocal().getNome() + " - " + sessao.getLocal().getNome()+"\n");
		}
	}


	public static void listagemFav(){
		System.out.println("\nListagem de filmes favoritos: \n");
		Iterator<Sessao> iterator = sessoes.iterator();
		while (iterator.hasNext()){
			Sessao sessao = iterator.next();
			if (sessao.getFilme().isFavorito())
				System.out.println(sessao.getFilme().getNome() + " ("+ sessao.getDataStrShort()+") - " + sessao.getLocal().getNome());

		}
	}
}