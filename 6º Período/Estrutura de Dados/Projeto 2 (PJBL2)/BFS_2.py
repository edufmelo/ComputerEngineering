
import matplotlib.pyplot as plt
import networkx as nx

def recomendar_amigos_lista(lista_adj, usuario_id):
    amigos_diretos = set(lista_adj[usuario_id])
    recomendacoes = set()

    for amigo in amigos_diretos:
        for amigo_ in lista_adj[amigo]:
            if amigo_ != usuario_id and amigo_ not in amigos_diretos:
                recomendacoes.add(amigo_)

    # retorna as recomendações por uma lista
    return list(recomendacoes)

def desenhar_recomendacoes_lista(lista_adj, usuario_id, recomendacoes, 
nomes_usuarios):
    """
    Desenha o grafo com o usuário selecionado e suas recomendações de amizade.
    Utiliza a biblioteca NetworkX para criação do grafo e Matplotlib para desenhar.
    """
    G = nx.Graph()  # cria um grafo não direcionado
    cores_nomes = {}

    # coloca arestas ao grafo usando os nomes
    for i in range(len(lista_adj)):
        for j in lista_adj[i]:  
            if i < j:  # adiciona arestas apenas uma vez
                G.add_edge(nomes_usuarios[i], nomes_usuarios[j])  # adiciona aresta

    for i in range(len(lista_adj)):
        if i == usuario_id:
            cores_nomes[nomes_usuarios[i]] = 'black'  # cor do usuário selecionado
        elif i in recomendacoes:
            cores_nomes[nomes_usuarios[i]] = 'black'  # cor para recomendações
        else:
            cores_nomes[nomes_usuarios[i]] = 'black'  # cor para os outros nós

    pos = nx.spring_layout(G,seed=50,k=100)  
    plt.figure(figsize=(6, 4))  # define o tamanho da figura

    # título do grafo
    nomes_recomendados = [nomes_usuarios[i] for i in recomendacoes]
    Tit = f"Recomendações de amizade para {nomes_usuarios[usuario_id]}:\n"
    Tit += ", ".join(nomes_recomendados) + "."
    plt.title(Tit)

    # desenha o grafo com cores dos vértices em branco e em negrito
    nx.draw(G, pos, with_labels=True, node_color='white', font_weight='bold')

    # itera sobre cada item no dicionário "pos", que contém as posições dos nós no 
grafo
    for node, (x, y) in pos.items():
        if node == nomes_usuarios[usuario_id]:
            facecolor = '#90EE90'  # define a cor de fundo 

        # se o nó não for o usuário selecionado, verifica se ele está na lista de 
recomendações
        elif node in [nomes_usuarios[i] for i in recomendacoes]:



            facecolor = 'red'  # define a cor de fundo recomendações

        # se o nó não for o usuário nem uma recomendação, define a cor de fundo 
como branca.
        else:
            facecolor = 'white'

        # desenha o texto do nome do nó 
        plt.text(x, y, node, fontsize=12, ha='center', va='center',
                 bbox=dict(facecolor=facecolor, edgecolor='black', 
boxstyle='round,pad=0.2'),
                 color=cores_nomes[node])

    # exibe a figura
    plt.show()  # mostra o grafo na tela

def criar_grafo_exemplo_lista():
    lista_adj = [
        [1, 3, 5, 6],  # Ana(0) -> Bruno, Daniel, Fabio, Gabriela  
        [0, 2, 4],  # Bruno(1) -> Ana, Carla e Eduarda 
        [1, 5, 9],  # Carla(2) -> Bruno, Fabio, João
        [0, 7, 8],  # Daniel(3) -> Ana, Henrique, Isabela
        [1, 6, 9],  # Eduarda(4) -> Bruno, Gabriela e Joao
        [0, 2, 8],  # Fabio(5) -> Ana, Carla e Isabela
        [0, 4, 7],  # Gabriela(6) -> Ana, Eduarda e Henrique
        [3, 6, 9],  # Henrique(7) -> Daniel, Gabriela e João
        [3, 5, 9],  # Isabela(8) -> Daniel, Fabio e João
        [2, 4, 7, 8]  # João(9) -> Carla, Eduarda, Henrique e Isabela
    ]
    return lista_adj  # retorna a lista de adjacência

nomes_usuarios = ["Ana", "Bruno", "Carla", "Daniel", "Eduarda",
                  "Fabio", "Gabriela", "Henrique", "Isabela", "João"]
lista_adj = criar_grafo_exemplo_lista()  # cria o grafo

print("Usuários disponíveis:", ", ".join(nomes_usuarios))
nome_usuario = input("Digite o nome do usuário para ver as recomendações: ")
usuario_id = nomes_usuarios.index(nome_usuario)  # pega o índice do usuario 
selecionado

# gera e exibe as recomendações de amizade
recomendacoes = recomendar_amigos_lista(lista_adj, usuario_id)
nomes_recomendados = [nomes_usuarios[i] for i in recomendacoes]
print("Recomendações de amizade para {}: {}".format(nome_usuario, ", 
".join(nomes_recomendados)))

# desenha o grafo com as recomendações
desenhar_recomendacoes_lista(lista_adj, usuario_id, recomendacoes, nomes_usuarios)
