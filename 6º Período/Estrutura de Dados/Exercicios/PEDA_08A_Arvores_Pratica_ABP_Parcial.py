# -- coding: latin-1 --
import matplotlib.pyplot as plt  # Importa Matplotlib para gráficos.
import networkx as nx  # Importa NetworkX para grafos.
import random  # Importa random para gerar números aleatórios.


# Classe que representa um nó da árvore binária.
class No:
    """
   A classe No define um nó de uma árvore binária, com valor
   (chave) e referências para seus filhos esquerdo e direito.
   Em aplicações de bancos de dados seria acrescentado mais
   um atributo referente aos dados.
   """

    def __init__(self, chave):
        """
      Inicializa o nó com chave e filhos esquerdo/direito como None.
      """
        # Filho esquerdo inicializado como None.
        self.esquerda = None
        # Filho direito inicializado como None.
        self.direita = None
        # Atribui o valor da chave ao nó.
        self.chave = chave
        """
      No caso de banco de dados, as modificações/adições seriam:
      def __init__(self, chave, dado)
      self.dado = dado
      """


# Classe que representa a árvore binária de pesquisa.
class ABP:
    """
   Classe que define uma árvore binária de busca com métodos para
   inserir, buscar e remover chaves, e também percorrer a árvore.
   """

    def __init__(self):
        """
      Inicializa a árvore sem nenhum nó (raiz é None).
      """
        # Raiz inicializada como None (árvore vazia).
        self.raiz = None
    ##### ESCREVER LINHA DE CÓDIGO #####

    def inserir(self, chave):
        """
      Insere uma chave na árvore. Chaves devem estar entre 1 e 99.
      Essa restrição é apenas para manter a árvore pequena.
      """
        # Verifica se a chave está no intervalo válido.
        if chave <= 0 or chave >= 100:
            # Mensagem de erro para chave fora do intervalo.
            print("Chave fora do intervalo permitido.")
            return  # Sai da função se a chave for inválida.
        # Se a árvore estiver vazia, insere a chave na raiz.
        if self.raiz is None:
            #  Atribui um novo objeto nó com a chave à raiz.
            self.raiz = No(chave)  # Cria o nó com a chave.
        else:
            # Chama método auxiliar para inserir a chave.
            self._inserir(self.raiz, chave)

    def _inserir(self, no_atual, chave):  # Esse método é protegido.
        """
      Função auxiliar para inserir a chave na árvore recursivamente.
      """
        # Verifica se a chave já existe na árvore.
        if chave == no_atual.chave:
            # Exibe mensagem de erro para chave duplicada.
            print(f"Chave {chave} já existe na árvore.")
            return  # Sai do método.
        # Se a chave for menor, insere à esquerda.
        if chave < no_atual.chave:
            if no_atual.esquerda is None:
                # Insere a chave à esquerda.
                no_atual.esquerda = No(chave)
            else:
                # Chamada recursiva à esquerda.
                self._inserir(no_atual.esquerda, chave)
        else:  # Se a chave for maior, insere à direita.
            if no_atual.direita is None:
            # Insere a chave à direita.
               no_atual.direita = No(chave)
            ##### ESCREVER LINHA DE CÓDIGO #####

            else:
               self._inserir(no_atual.direita, chave)
        # Chamada recursiva à direita.

        ##### ESCREVER LINHA DE CÓDIGO #####

    def inserir_lista(self, lista_chaves):
        """
      Insere uma lista de chaves na árvore. Chaves fora do intervalo
      ou duplicadas serão ignoradas.
      """
        # Percorre a lista de chaves.
        for chave in lista_chaves:
           self.inserir(chave)

    # Para cada chave, chama o método inserir individualmente.

    ##### ESCREVER LINHA DE CÓDIGO #####

    def buscar(self, chave):
        """
      Busca uma chave na árvore. Retorna True se encontrada,
      False caso contrário.
      """
        # Chama a função auxiliar de busca a partir da raiz.
        return self._buscar(self.raiz, chave)

    def _buscar(self, no_atual, chave):
        """
      Função auxiliar para busca recursiva da chave na árvore.
      """
        # Verifica se encontrou a chave ou se o nó é None.
        if no_atual is None or no_atual.chave == chave:
            # Retorna True se a chave for encontrada.
            return no_atual is not None
        # Se a chave for menor, busca à esquerda.
        if chave < no_atual.chave:
            return self._buscar(no_atual.esquerda, chave)
        # Caso contrário, busca à direita.
        else:
           return self._buscar(no_atual.direita, chave)
        ##### ESCREVER LINHA DE CÓDIGO #####

    def remover(self, chave):
        """
      Remove uma chave da árvore.
      """
        # Chama a função auxiliar para remover o nó.
        # Caso o nó excluído seja a raiz, ela é atualizada, caso contrário
        # não se altera no retorno recursivo de _remover.
        self.raiz = self._remover(self.raiz, chave)
        if self.raiz is not None:
            print('Raiz:', self.raiz.chave)

    def _remover(self, no_atual, chave):
        """
      Função auxiliar para remover o nó da árvore de forma recursiva.
      A cada passo da recursão, o método _remover retorna o nó atualizado
      ou inalterado para o chamador (ou seja, o nó pai na árvore). A raiz
      só será alterada em remover se a chave excluída for a dela.
      """
        # Se o nó for None, retorna None.
        if no_atual is None:
            return no_atual
        # Busca à esquerda se a chave for menor.
        if chave < no_atual.chave:
            no_atual.esquerda = self._remover(no_atual.esquerda, chave)
        # Busca à direita se a chave for maior.
        elif chave > no_atual.chave:
           no_atual.direita = self._remover(no_atual.direita, chave)

        ##### ESCREVER LINHA DE CÓDIGO #####

        else:  # Se a chave foi encontrada.
            # Caso 1: Nó sem filhos.
            if no_atual.esquerda is None and no_atual.direita is None:
                # Retorna None, pois o nó será removido.
                return None
            # Caso 2: Nó com um único filho.
            elif no_atual.esquerda is None:
                # Substitui o nó pelo filho direito.
                return no_atual.direita

            ##### ESCREVER LINHA DE CÓDIGO #####
            elif no_atual.direita is None:
            # Substitui o nó pelo filho esquerdo.
               return no_atual.esquerda
            ##### ESCREVER LINHA DE CÓDIGO #####

            # Caso 3: Nó com dois filhos.
            else:
                # Encontra o sucessor (menor valor da subárvore direita).
                sucessor = self._minimo(no_atual.direita)
                # Substitui o valor do nó pelo do sucessor.
                no_atual.chave = sucessor.chave
                # Remove o sucessor do lado direito.
                no_atual.direita = self._remover(no_atual.direita, sucessor.chave)

        # Retorna o nó atualizado (ou inalterado) pelo processo recursivo.
        return no_atual

    def _minimo(self, no_atual):
        """
      Encontra o nó com o menor valor na subárvore indo para o nó mais
      à esquerda da subárvore.
      """
        # Vai para o filho mais à esquerda.
        while no_atual.esquerda is not None:
            no_atual = no_atual.esquerda
        return no_atual  # Retorna o nó com o menor valor.

    def pre_ordem(self, no_atual):
        """
      Imprime os valores da árvore em pré-ordem (raiz, sae, sad).
      """
        # Se o nó não for None, imprime seu valor.
        if no_atual:
            print(no_atual.chave, end=" ")
            # Percurso recursivo para o filho esquerdo (sae).
            self.pre_ordem(no_atual.esquerda)
            # Percurso recursivo o filho direito (sad).
            self.pre_ordem(no_atual.direita)

    def em_ordem(self, no_atual):
        """
      Imprime os valores da árvore em ordem (sae, raiz, sad).
      """
        if no_atual:
           self.em_ordem(no_atual.esquerda)
           # Percurso recursivo para o filho esquerdo (sae).
           print(no_atual.chave, end=" ")
           # Percurso recursivo o filho direito (sad).
           self.em_ordem(no_atual.direita)
    ##### ESCREVER LINHA DE CÓDIGO #####
    ##### ESCREVER LINHA DE CÓDIGO #####
    ##### ESCREVER LINHA DE CÓDIGO #####

    def pos_ordem(self, no_atual):
        """
      Imprime os valores da árvore em pós-ordem (sae, sad, raiz).
      """
        if no_atual:
            self.pos_ordem(no_atual.esquerda)
            self.pos_ordem(no_atual.direita)
            print(no_atual.chave, end=" ")

    ##### ESCREVER LINHA DE CÓDIGO #####
    ##### ESCREVER LINHA DE CÓDIGO #####
    ##### ESCREVER LINHA DE CÓDIGO #####

    def mostrar(self):
        """
      Desenha a árvore binária de busca usando uma função externa.
      """
        desenhar_arvore(self)  # Chama a função para desenhar.


def desenhar_arvore(arvore):
    """
   Desenha a árvore binária de busca usando Matplotlib e NetworkX.
   Esta é uma função adicional para facilitar o entedimento da construção
   de uma ABP. Ela não é necessária para o funcionamento do programa, mas
   é um recurso didático.
   """
    # Se a árvore estiver vazia, não desenha.
    if arvore.raiz is None:
        print("Árvore vazia.")
        return

    def adicionar_arestas(grafo, no, pos={}, x=0, y=0, camada=1):
        """
      Função auxiliar que adiciona as arestas (conexões) entre
      os nós da árvore.
      """
        if no is not None:
            # Define a posição do nó.
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
        return pos  # Retorna as posições dos nós.

    # Cria o grafo que representa a árvore.
    grafo = nx.DiGraph()
    # Adiciona as arestas da árvore no grafo.
    pos = adicionar_arestas(grafo, arvore.raiz)
    # 5 polegadas de largura, 5 de altura.
    plt.figure(figsize=(5, 5))
    # Desenha o grafo com os nós e arestas.
    nx.draw(grafo, pos, with_labels=True, node_size=500, node_color='lightblue',
            font_size=10, font_color='black', font_weight='bold', arrows=False)
    # Exibe o gráfico.
    plt.show()


def menu():
    """
   Exibe o menu interativo para o usuário manipular a árvore binária.
   """
    arvore = ABP()  # Cria a árvore binária.

    while True:
        # Exibe as opções do menu.
        print("\nMenu:")
        print("1. Inserir uma chave")
        print("2. Inserir uma lista de chaves")  # Nova opção
        print("3. Buscar uma chave")
        print("4. Remover uma chave")
        print("5. Inserir três chaves aleatórias")
        print("6. Imprimir os percursos (pré-ordem, em ordem, pós-ordem)")
        print("7. Mostrar a árvore")
        print("8. Sair")
        # Lê a opção escolhida pelo usuário.
        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            # Insere uma nova chave.
            chave = int(input("Digite uma chave: "))
            arvore.inserir(chave)
        elif opcao == '2':
            # Insere uma lista de chaves.
            lista = input("Digite uma lista de chaves separadas por vírgula: ")
            # Converte a lista de string para inteiros.
            lista_chaves = [int(x) for x in lista.split(",")]
            arvore.inserir_lista(lista_chaves)
        elif opcao == '3':
            # Busca uma chave na árvore.
            chave = int(input("Digite a chave a ser buscada: "))
            if arvore.buscar(chave):
                print(f"Chave {chave} encontrada.")
            else:
                print(f"Chave {chave} não encontrada.")
        elif opcao == '4':
            # Remove uma chave da árvore.
            chave = int(input("Digite a chave a ser removida: "))
            arvore.remover(chave)
        elif opcao == '5':
            # Insere três chaves aleatórias.
            for _ in range(3):
                chave = random.randint(1, 99)
                arvore.inserir(chave)
        elif opcao == '6':
            # Imprime os percursos da árvore.
            print("Pré-ordem:", end=" ")
            arvore.pre_ordem(arvore.raiz)
            print("\nEm ordem.:", end=" ")
            arvore.em_ordem(arvore.raiz)
            print("\nPós-ordem:", end=" ")
            arvore.pos_ordem(arvore.raiz)
            print()
        elif opcao == '7':
            # Mostra a árvore graficamente.
            arvore.mostrar()
        elif opcao == '8':
            # Sai do programa.
            break
        else:
            # Exibe mensagem de erro para opção inválida.
            print("Opção inválida.")


# Inicia o programa de teste.
menu()
