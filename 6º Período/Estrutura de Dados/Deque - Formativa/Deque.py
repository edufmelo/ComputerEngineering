# -- coding: latin-1 --

class Deque:
    """Classe que representa uma lista duplamente ligada com sentinelas."""

    class __No:
        """Subclasse privada que representa um nó da lista."""

        def __init__(self, dado=None):
            self.dado = dado  # Armazena o dado do nó.
            self.proximo = None  # Aponta para o próximo nó.
            self.anterior = None  # Aponta para o nó anterior.

    def __init__(self):
        self.__cabeca = self.__No()  # Cria o nó sentinela de cabeça.
        self.__cauda = self.__No()  # Cria o nó sentinela de cauda.
        self.__cabeca.proximo = self.__cauda  # Liga cabeça ao nó sentinela de cauda.
        self.__cauda.anterior = self.__cabeca  # Liga cauda ao nó sentinela de cabeça.
        self.__tamanho = 0  # Inicializa o tamanho da lista como 0.

    def addFirst(self, dado):
        """Insere um novo nó no início da lista."""
        novo_no = self.__No(dado)  # Cria um novo nó com o dado fornecido.
        primeiro = self.__cabeca.proximo  # O primeiro nó real.
        novo_no.proximo = primeiro  # Aponta para o antigo primeiro nó.
        novo_no.anterior = self.__cabeca  # Aponta para o nó sentinela de cabeça.
        primeiro.anterior = novo_no  # Atualiza o anterior do antigo primeiro.
        self.__cabeca.proximo = novo_no  # Atualiza o próximo da cabeça.
        self.__tamanho += 1  # Incrementa o tamanho da lista.

    def addLast(self, dado):
        """Insere um novo nó no final da lista."""
        novo_no = self.__No(dado)  # Cria um novo nó com o dado fornecido.
        ultimo = self.__cauda.anterior  # O último nó real.
        novo_no.proximo = self.__cauda  # O novo nó aponta para o nó sentinela de cauda.
        novo_no.anterior = ultimo  # Aponta para o antigo último nó.
        ultimo.proximo = novo_no  # O antigo último nó aponta para o novo nó.
        self.__cauda.anterior = novo_no  # O nó sentinela de cauda aponta para o novo nó.
        self.__tamanho += 1  # Incrementa o tamanho da lista.

    def inserir_posicao(self, dado, posicao):
        """Insere um novo nó na posição especificada."""
        if posicao < 0 or posicao > self.__tamanho:
            print("Posição inválida.")
            return
        atual = self.__cabeca.proximo  # Começa no primeiro nó real.
        for _ in range(posicao):
            atual = atual.proximo  # Avança até a posição.
        novo_no = self.__No(dado)  # Cria o novo nó.
        novo_no.proximo = atual  # O novo nó aponta para o nó atual.
        novo_no.anterior = atual.anterior  # O novo nó aponta para o nó anterior.
        atual.anterior.proximo = novo_no  # O anterior aponta para o novo nó.
        atual.anterior = novo_no  # O nó atual aponta para o novo nó.
        self.__tamanho += 1  # Incrementa o tamanho da lista.

    def deleteFirst(self):
        """Remove o nó do início da lista e retorna seu dado."""
        if self.__tamanho == 0:  # Lista vazia.
            print("Erro: Não é possível remover de um deque vazio.")
            return None
        removido = self.__cabeca.proximo  # O primeiro nó real.
        self.__cabeca.proximo = removido.proximo  # Cabeça aponta para o segundo nó.
        removido.proximo.anterior = self.__cabeca  # O segundo nó aponta para a cabeça.
        self.__tamanho -= 1  # Decrementa o tamanho.
        return removido.dado  # Retorna o dado removido.

    def deleteLast(self):
        """Remove o nó do final da lista e retorna seu dado."""
        if self.__tamanho == 0:  # Lista vazia.
            print("Erro: Não é possível remover de um deque vazio.")
            return None
        removido = self.__cauda.anterior  # O último nó real.
        self.__cauda.anterior = removido.anterior  # Cauda aponta para o penúltimo nó.
        removido.anterior.proximo = self.__cauda  # O penúltimo nó aponta para a cauda.
        self.__tamanho -= 1  # Decrementa o tamanho.
        return removido.dado  # Retorna o dado removido.

    def remover_posicao(self, posicao):
        """Remove o nó na posição especificada e retorna seu dado."""
        if posicao < 0 or posicao >= self.__tamanho:
            print("Posição inválida.")
            return None
        atual = self.__cabeca.proximo
        for _ in range(posicao):
            atual = atual.proximo  # Avança até a posição.
        removido = atual  # Nó a ser removido.
        removido.anterior.proximo = removido.proximo  # Conecta anterior ao próximo.
        removido.proximo.anterior = removido.anterior  # Conecta próximo ao anterior.
        self.__tamanho -= 1  # Decrementa o tamanho.
        return removido.dado  # Retorna o dado removido.

    def remover_dado(self, dado):
        """Remove o primeiro nó que contém o dado especificado."""
        atual = self.__cabeca.proximo
        for _ in range(self.__tamanho):
            if atual.dado == dado:  # Verifica se o dado corresponde.
                atual.anterior.proximo = atual.proximo  # Conecta anterior ao próximo.
                atual.proximo.anterior = atual.anterior  # Conecta próximo ao anterior.
                self.__tamanho -= 1  # Decrementa o tamanho.
                return atual.dado  # Retorna o dado removido.
            atual = atual.proximo
        print("Dado não encontrado.")  # Se o dado não estiver na lista.
        return None

    def peek(self):
        """Recupera o dado do nó no início da lista."""
        if self.__tamanho == 0:  # Lista vazia.
            return None
        return self.__cabeca.proximo.dado  # Retorna o dado do primeiro nó real.

    def top(self):
        """Recupera o dado do nó no final da lista."""
        if self.__tamanho == 0:  # Lista vazia.
            return None
        return self.__cauda.anterior.dado  # Retorna o dado do último nó real.

    def recuperar_posicao(self, posicao):
        """Recupera o dado do nó na posição especificada."""
        if posicao < 0 or posicao >= self.__tamanho:
            print("Posição inválida.")
            return None
        atual = self.__cabeca.proximo
        for _ in range(posicao):
            atual = atual.proximo  # Avança até a posição desejada.
        return atual.dado  # Retorna o dado do nó da posição.

    def imprimir_lista(self):
        """Imprime os dados de todos os nós da lista."""
        atual = self.__cabeca.proximo
        for _ in range(self.__tamanho):
            print(atual.dado, end=" <-> ")  # Imprime o dado do nó atual.
            atual = atual.proximo  # Avança para o próximo nó.
        print("None")  # Indica o fim da lista.

    def getSize(self):
        """Retorna o tamanho da lista."""
        return self.__tamanho  # Retorna o número de nós na lista.

    def obter_cabeca(self):
        """Retorna o nó sentinela da cabeça."""
        return self.__cabeca

    def obter_cauda(self):
        """Retorna o nó sentinela da cauda."""
        return self.__cauda

    def isEmpty(self):
        if self.__tamanho == 0:
            return True
        else:
            return False

    def __str__(self):
        # """Retorna uma string representando os elementos da fila."""
        elementos = []  # Cria uma lista para armazenar os elementos.
        atual = self.obter_cabeca().proximo  # Começa no primeiro nó da lista.
        while atual != self.__cauda:  # Percorre todos os nós da lista.
            elementos.append(str(atual.dado))  # Adiciona o dado à lista.
            atual = atual.proximo  # Move para o próximo nó.
        return " ".join(elementos)  #


# Criação de um deque.
deque = Deque()
# Inserindo e exibindo elementos no início.
print("Inserindo 10 no início do deque:")
deque.addFirst(10)
print(deque)  # Deve imprimir: 10.
print("Inserindo 5 no início do deque:")
deque.addFirst(5)
print(deque)  # Deve imprimir: 5 10.
# Inserindo e exibindo elementos no final.
print("Inserindo 20 no final do deque:")
deque.addLast(20)
print(deque)  # Deve imprimir: 5 10 20.
print("Inserindo 30 no final do deque:")
deque.addLast(30)
print(deque)  # Deve imprimir: 5 10 20 30.
# Removendo e exibindo elemento do início.
print("\nRemovendo elemento do início do deque:")
deque.deleteFirst()
print(deque)  # Deve imprimir: 10 20 30.
print("Removendo outro elemento do início do deque:")
deque.deleteFirst()
print(deque)  # Deve imprimir: 20 30.
# Removendo e exibindo elemento do final.
print("Removendo elemento do final do deque:")
deque.deleteLast()
print(deque)  # Deve imprimir: 20.
print("Removendo outro elemento do final do deque:")
deque.deleteLast()
print(deque)  # Deve imprimir: None.
# Verificando se o deque está vazio e tentando remover de um deque vazio.
print("\nO deque está vazio?", deque.isEmpty())  # Deve imprimir: True.
print("Tentando remover de um deque vazio:")
deque.deleteFirst()  # Deve imprimir mensagem de erro.