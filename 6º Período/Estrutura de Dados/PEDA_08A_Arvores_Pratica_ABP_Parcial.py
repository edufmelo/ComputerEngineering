# -- coding: latin-1 --
import matplotlib.pyplot as plt  # Importa Matplotlib para gr�ficos.
import networkx as nx  # Importa NetworkX para grafos.
import random  # Importa random para gerar n�meros aleat�rios.


# Classe que representa um n� da �rvore bin�ria.
class No:
    """
   A classe No define um n� de uma �rvore bin�ria, com valor
   (chave) e refer�ncias para seus filhos esquerdo e direito.
   Em aplica��es de bancos de dados seria acrescentado mais
   um atributo referente aos dados.
   """

    def __init__(self, chave):
        """
      Inicializa o n� com chave e filhos esquerdo/direito como None.
      """
        # Filho esquerdo inicializado como None.
        self.esquerda = None
        # Filho direito inicializado como None.
        self.direita = None
        # Atribui o valor da chave ao n�.
        self.chave = chave
        """
      No caso de banco de dados, as modifica��es/adi��es seriam:
      def __init__(self, chave, dado)
      self.dado = dado
      """


# Classe que representa a �rvore bin�ria de pesquisa.
class ABP:
    """
   Classe que define uma �rvore bin�ria de busca com m�todos para
   inserir, buscar e remover chaves, e tamb�m percorrer a �rvore.
   """

    def __init__(self):
        """
      Inicializa a �rvore sem nenhum n� (raiz � None).
      """
        # Raiz inicializada como None (�rvore vazia).
        self.raiz = None
    ##### ESCREVER LINHA DE C�DIGO #####

    def inserir(self, chave):
        """
      Insere uma chave na �rvore. Chaves devem estar entre 1 e 99.
      Essa restri��o � apenas para manter a �rvore pequena.
      """
        # Verifica se a chave est� no intervalo v�lido.
        if chave <= 0 or chave >= 100:
            # Mensagem de erro para chave fora do intervalo.
            print("Chave fora do intervalo permitido.")
            return  # Sai da fun��o se a chave for inv�lida.
        # Se a �rvore estiver vazia, insere a chave na raiz.
        if self.raiz is None:
            #  Atribui um novo objeto n� com a chave � raiz.
            self.raiz = No(chave)  # Cria o n� com a chave.
        else:
            # Chama m�todo auxiliar para inserir a chave.
            self._inserir(self.raiz, chave)

    def _inserir(self, no_atual, chave):  # Esse m�todo � protegido.
        """
      Fun��o auxiliar para inserir a chave na �rvore recursivamente.
      """
        # Verifica se a chave j� existe na �rvore.
        if chave == no_atual.chave:
            # Exibe mensagem de erro para chave duplicada.
            print(f"Chave {chave} j� existe na �rvore.")
            return  # Sai do m�todo.
        # Se a chave for menor, insere � esquerda.
        if chave < no_atual.chave:
            if no_atual.esquerda is None:
                # Insere a chave � esquerda.
                no_atual.esquerda = No(chave)
            else:
                # Chamada recursiva � esquerda.
                self._inserir(no_atual.esquerda, chave)
        else:  # Se a chave for maior, insere � direita.
            if no_atual.direita is None:
            # Insere a chave � direita.
               no_atual.direita = No(chave)
            ##### ESCREVER LINHA DE C�DIGO #####

            else:
               self._inserir(no_atual.direita, chave)
        # Chamada recursiva � direita.

        ##### ESCREVER LINHA DE C�DIGO #####

    def inserir_lista(self, lista_chaves):
        """
      Insere uma lista de chaves na �rvore. Chaves fora do intervalo
      ou duplicadas ser�o ignoradas.
      """
        # Percorre a lista de chaves.
        for chave in lista_chaves:
           self.inserir(chave)

    # Para cada chave, chama o m�todo inserir individualmente.

    ##### ESCREVER LINHA DE C�DIGO #####

    def buscar(self, chave):
        """
      Busca uma chave na �rvore. Retorna True se encontrada,
      False caso contr�rio.
      """
        # Chama a fun��o auxiliar de busca a partir da raiz.
        return self._buscar(self.raiz, chave)

    def _buscar(self, no_atual, chave):
        """
      Fun��o auxiliar para busca recursiva da chave na �rvore.
      """
        # Verifica se encontrou a chave ou se o n� � None.
        if no_atual is None or no_atual.chave == chave:
            # Retorna True se a chave for encontrada.
            return no_atual is not None
        # Se a chave for menor, busca � esquerda.
        if chave < no_atual.chave:
            return self._buscar(no_atual.esquerda, chave)
        # Caso contr�rio, busca � direita.
        else:
           return self._buscar(no_atual.direita, chave)
        ##### ESCREVER LINHA DE C�DIGO #####

    def remover(self, chave):
        """
      Remove uma chave da �rvore.
      """
        # Chama a fun��o auxiliar para remover o n�.
        # Caso o n� exclu�do seja a raiz, ela � atualizada, caso contr�rio
        # n�o se altera no retorno recursivo de _remover.
        self.raiz = self._remover(self.raiz, chave)
        if self.raiz is not None:
            print('Raiz:', self.raiz.chave)

    def _remover(self, no_atual, chave):
        """
      Fun��o auxiliar para remover o n� da �rvore de forma recursiva.
      A cada passo da recurs�o, o m�todo _remover retorna o n� atualizado
      ou inalterado para o chamador (ou seja, o n� pai na �rvore). A raiz
      s� ser� alterada em remover se a chave exclu�da for a dela.
      """
        # Se o n� for None, retorna None.
        if no_atual is None:
            return no_atual
        # Busca � esquerda se a chave for menor.
        if chave < no_atual.chave:
            no_atual.esquerda = self._remover(no_atual.esquerda, chave)
        # Busca � direita se a chave for maior.
        elif chave > no_atual.chave:
           no_atual.direita = self._remover(no_atual.direita, chave)

        ##### ESCREVER LINHA DE C�DIGO #####

        else:  # Se a chave foi encontrada.
            # Caso 1: N� sem filhos.
            if no_atual.esquerda is None and no_atual.direita is None:
                # Retorna None, pois o n� ser� removido.
                return None
            # Caso 2: N� com um �nico filho.
            elif no_atual.esquerda is None:
                # Substitui o n� pelo filho direito.
                return no_atual.direita

            ##### ESCREVER LINHA DE C�DIGO #####
            elif no_atual.direita is None:
            # Substitui o n� pelo filho esquerdo.
               return no_atual.esquerda
            ##### ESCREVER LINHA DE C�DIGO #####

            # Caso 3: N� com dois filhos.
            else:
                # Encontra o sucessor (menor valor da sub�rvore direita).
                sucessor = self._minimo(no_atual.direita)
                # Substitui o valor do n� pelo do sucessor.
                no_atual.chave = sucessor.chave
                # Remove o sucessor do lado direito.
                no_atual.direita = self._remover(no_atual.direita, sucessor.chave)

        # Retorna o n� atualizado (ou inalterado) pelo processo recursivo.
        return no_atual

    def _minimo(self, no_atual):
        """
      Encontra o n� com o menor valor na sub�rvore indo para o n� mais
      � esquerda da sub�rvore.
      """
        # Vai para o filho mais � esquerda.
        while no_atual.esquerda is not None:
            no_atual = no_atual.esquerda
        return no_atual  # Retorna o n� com o menor valor.

    def pre_ordem(self, no_atual):
        """
      Imprime os valores da �rvore em pr�-ordem (raiz, sae, sad).
      """
        # Se o n� n�o for None, imprime seu valor.
        if no_atual:
            print(no_atual.chave, end=" ")
            # Percurso recursivo para o filho esquerdo (sae).
            self.pre_ordem(no_atual.esquerda)
            # Percurso recursivo o filho direito (sad).
            self.pre_ordem(no_atual.direita)

    def em_ordem(self, no_atual):
        """
      Imprime os valores da �rvore em ordem (sae, raiz, sad).
      """
        if no_atual:
           self.em_ordem(no_atual.esquerda)
           # Percurso recursivo para o filho esquerdo (sae).
           print(no_atual.chave, end=" ")
           # Percurso recursivo o filho direito (sad).
           self.em_ordem(no_atual.direita)
    ##### ESCREVER LINHA DE C�DIGO #####
    ##### ESCREVER LINHA DE C�DIGO #####
    ##### ESCREVER LINHA DE C�DIGO #####

    def pos_ordem(self, no_atual):
        """
      Imprime os valores da �rvore em p�s-ordem (sae, sad, raiz).
      """
        if no_atual:
            self.pos_ordem(no_atual.esquerda)
            self.pos_ordem(no_atual.direita)
            print(no_atual.chave, end=" ")

    ##### ESCREVER LINHA DE C�DIGO #####
    ##### ESCREVER LINHA DE C�DIGO #####
    ##### ESCREVER LINHA DE C�DIGO #####

    def mostrar(self):
        """
      Desenha a �rvore bin�ria de busca usando uma fun��o externa.
      """
        desenhar_arvore(self)  # Chama a fun��o para desenhar.


def desenhar_arvore(arvore):
    """
   Desenha a �rvore bin�ria de busca usando Matplotlib e NetworkX.
   Esta � uma fun��o adicional para facilitar o entedimento da constru��o
   de uma ABP. Ela n�o � necess�ria para o funcionamento do programa, mas
   � um recurso did�tico.
   """
    # Se a �rvore estiver vazia, n�o desenha.
    if arvore.raiz is None:
        print("�rvore vazia.")
        return

    def adicionar_arestas(grafo, no, pos={}, x=0, y=0, camada=1):
        """
      Fun��o auxiliar que adiciona as arestas (conex�es) entre
      os n�s da �rvore.
      """
        if no is not None:
            # Define a posi��o do n�.
            pos[no.chave] = (x, y)
            # Adiciona o filho esquerdo, se existir.
            if no.esquerda is not None:
                grafo.add_edge(no.chave, no.esquerda.chave)
                adicionar_arestas(grafo, no.esquerda, pos, x - 1 / camada, y - 1,
                                  camada + 1)
            # Adiciona o filho direito, se existir.
            if no.direita is not None:
                grafo.add_edge(no.chave, no.direita.chave)
                adicionar_arestas(grafo, no.direita, pos, x + 1 / camada, y - 1,
                                  camada + 1)
        return pos  # Retorna as posi��es dos n�s.

    # Cria o grafo que representa a �rvore.
    grafo = nx.DiGraph()
    # Adiciona as arestas da �rvore no grafo.
    pos = adicionar_arestas(grafo, arvore.raiz)
    # 5 polegadas de largura, 5 de altura.
    plt.figure(figsize=(5, 5))
    # Desenha o grafo com os n�s e arestas.
    nx.draw(grafo, pos, with_labels=True, node_size=500, node_color='lightblue',
            font_size=10, font_color='black', font_weight='bold', arrows=False)
    # Exibe o gr�fico.
    plt.show()


def menu():
    """
   Exibe o menu interativo para o usu�rio manipular a �rvore bin�ria.
   """
    arvore = ABP()  # Cria a �rvore bin�ria.

    while True:
        # Exibe as op��es do menu.
        print("\nMenu:")
        print("1. Inserir uma chave")
        print("2. Inserir uma lista de chaves")  # Nova op��o
        print("3. Buscar uma chave")
        print("4. Remover uma chave")
        print("5. Inserir tr�s chaves aleat�rias")
        print("6. Imprimir os percursos (pr�-ordem, em ordem, p�s-ordem)")
        print("7. Mostrar a �rvore")
        print("8. Sair")
        # L� a op��o escolhida pelo usu�rio.
        opcao = input("Escolha uma op��o: ")

        if opcao == '1':
            # Insere uma nova chave.
            chave = int(input("Digite uma chave: "))
            arvore.inserir(chave)
        elif opcao == '2':
            # Insere uma lista de chaves.
            lista = input("Digite uma lista de chaves separadas por v�rgula: ")
            # Converte a lista de string para inteiros.
            lista_chaves = [int(x) for x in lista.split(",")]
            arvore.inserir_lista(lista_chaves)
        elif opcao == '3':
            # Busca uma chave na �rvore.
            chave = int(input("Digite a chave a ser buscada: "))
            if arvore.buscar(chave):
                print(f"Chave {chave} encontrada.")
            else:
                print(f"Chave {chave} n�o encontrada.")
        elif opcao == '4':
            # Remove uma chave da �rvore.
            chave = int(input("Digite a chave a ser removida: "))
            arvore.remover(chave)
        elif opcao == '5':
            # Insere tr�s chaves aleat�rias.
            for _ in range(3):
                chave = random.randint(1, 99)
                arvore.inserir(chave)
        elif opcao == '6':
            # Imprime os percursos da �rvore.
            print("Pr�-ordem:", end=" ")
            arvore.pre_ordem(arvore.raiz)
            print("\nEm ordem.:", end=" ")
            arvore.em_ordem(arvore.raiz)
            print("\nP�s-ordem:", end=" ")
            arvore.pos_ordem(arvore.raiz)
            print()
        elif opcao == '7':
            # Mostra a �rvore graficamente.
            arvore.mostrar()
        elif opcao == '8':
            # Sai do programa.
            break
        else:
            # Exibe mensagem de erro para op��o inv�lida.
            print("Op��o inv�lida.")


# Inicia o programa de teste.
menu()
