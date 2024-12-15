import sys
import networkx as nx
import matplotlib.pyplot as plt

def dijkstra(ini, fin, grafo, desig):
    num_vert = len(grafo)  # número de vértices no grafo
    INF = float('inf')
    for i in range(num_vert):
        for j in range(num_vert):
            if grafo[i][j] == 0 and i != j:
                grafo[i][j] = INF

    L = [INF] * num_vert  # inicializa a lista de distâncias com infinito
    L[ini] = 0  # distância para o vértice inicial é 0

    solv = [False] * num_vert  # vértices resolvidos (False indica que não está resolvido)
    prev = [None] * num_vert  # guarda/armazena o caminho

    for _ in range(num_vert):
        # encontra o vértice não resolvido com menor distância L
        LMin = INF
        for i in range(num_vert):
            if not solv[i] and L[i] < LMin:
                LMin = L[i]
                u = i

        solv[u] = True  # marca o vértice u como resolvido

        # atualiza as distâncias para os vizinhos de u
        for vizinho in range(num_vert):
            peso = grafo[u][vizinho]
            if not solv[vizinho] and peso != INF and L[u] + peso < L[vizinho]:
                L[vizinho] = L[u] + peso
                prev[vizinho] = u

    caminho = []
    i = fin
    while i is not None:
        caminho.insert(0, i)  
        i = prev[i]

    return caminho, L[fin]  # retorna o caminho e a distância mínima para o destino

def plot_grafos(grafo, caminho, ini, fin, desig, Comp):
    G = nx.Graph()

    # adiciona as arestas do grafo original
    for i in range(len(grafo)):
        for j in range(len(grafo[i])):
            if grafo[i][j] > 0 and grafo[i][j] != float('inf'):
                G.add_edge(i, j, weight=grafo[i][j])

    # adiciona o menor caminho em um grafo separado
    G_result = nx.Graph()
    for k in range(len(caminho) - 1):
        u, v = caminho[k], caminho[k + 1]
        G_result.add_edge(u, v, weight=grafo[u][v])

    # define as posições dos nós
    pos = nx.spring_layout(G, seed=24)  # organiza layout
    plt.figure(figsize=(12, 6))

    # grafo original com arestas pretas
    plt.subplot(1, 2, 1)
    nx.draw_networkx_edges(G, pos, edge_color='black', width=1)

    # desenha cada nó do grafo original
    for node in G.nodes:
        x, y = pos[node]
        color = 'white' if node not in [ini, fin] else ('lightgreen' if node == ini else 'lightsalmon')
        plt.annotate(
            desig[node],
            xy=(x, y), xycoords='data',
            ha='center', va='center',
            bbox=dict(boxstyle="round,pad=0.2", fc=color, ec="black", lw=1),
            size=7, color="black"
        )
    nx.draw_networkx_edge_labels(G, pos, edge_labels={(u, v): d['weight'] for u, v, d in G.edges(data=True)}, font_color='black', rotate=False)
    plt.title("Grafo Original")

    # menor caminho com arestas cinzas
    plt.subplot(1, 2, 2)
    nx.draw_networkx_edges(G, pos, edge_color='lightgray', width=1)

    # desenha cada nó do grafo com as cores específicas para o menor caminho
    for node in G.nodes:
        x, y = pos[node]
        color = 'white' if node not in [ini, fin] else ('lightgreen' if node == ini else 'lightsalmon')
        plt.annotate(
            desig[node],
            xy=(x, y), xycoords='data',
            ha='center', va='center',
            bbox=dict(boxstyle="round,pad=0.2", fc=color, ec="black", lw=1),
            size=7, color="black"
        )

    # sobrescreve as arestas do menor caminho em preto
    nx.draw_networkx_edges(G_result, pos, edge_color='black', width=1.5)
    nx.draw_networkx_edge_labels(G, pos, edge_labels={(u, v): d['weight'] for u, v, d in G.edges(data=True)}, font_color='black', rotate=False)

    # calcula e exibe o comprimento do caminho
    comprimento = sum(grafo[caminho[i]][caminho[i + 1]] for i in range(len(caminho) - 1))
    plt.title(f"Grafo com menor caminho\n (Comprimento: {comprimento})")

    plt.show()

cidades = ['Aurora', 'Bonito', 'Carmo', 'Douras', 'Estela', 'Felice', 'Gema', 'Herval', 'Ipiaú', 'Jaburu', 'Lindoa', 'Mundaú'] 

# matriz com os respectivos pesos das arestas
MA = [
    [0, 25, 0, 20, 33, 0, 0, 0, 0, 0, 0, 0],  # Aurora
    [25, 0, 32, 0, 0, 36, 0, 0, 0, 0, 0, 0],  # Bonito
    [0, 32, 0, 0, 0, 36, 22, 0, 0, 0, 0, 0],  # Carmo
    [20, 0, 0, 0, 0, 0, 42, 34, 0, 0, 0, 0],  # Douras
    [33, 0, 0, 0, 0, 0, 0, 30, 28, 0, 0, 0],  # Estela
    [0, 36, 36, 0, 0, 0, 0, 0, 40, 25, 0, 0],  # Felice
    [0, 0, 22, 0, 0, 0, 0, 0, 0, 50, 30, 0],  # Gema
    [0, 0, 0, 34, 30, 0, 0, 0, 0, 45, 45, 55], # Herval
    [0, 0, 0, 0, 28, 40, 0, 0, 0, 0, 40, 60],   # Ipiaú
    [0, 0, 0, 0, 0, 25, 50, 45, 0, 0, 0, 0],   # Jaburu
    [0, 0, 0, 0, 0, 0, 30, 45, 40, 0, 0, 0],   # Lindoa
    [0, 0, 0, 0, 0, 0, 0, 55, 60, 0, 0, 0]     # Mundaú
]

print("Cidades disponíveis:")
for i, cidade in enumerate(cidades, 1):
    print(f"{i} - {cidade}")

orig = int(input("Escolha uma cidade de origem (1 a 12): ")) - 1  
print(f"Origem selecionada: {cidades[orig]}")

destf = int(input("Escolha uma cidade de destino (1 a 12): ")) - 1  
print(f"Destino selecionado: {cidades[destf]}")

# chama função dijkstra para encontrar o menor caminho e o seu custo
caminho, Comp = dijkstra(orig, destf, MA, cidades)
print("Menor caminho de %s até %s: %s\nValor: %d\n" % 
      (cidades[orig], cidades[destf], " > ".join(cidades[v] for v in caminho), 
Comp))

caminho1 = caminho

print("--------------------------")
print("Outros destinos")
print("--------------------------")
print("Destino: Caminho (comprimento)")
print("--------------------------")
for dest in range(len(MA)):  # percorre todos os destinos
    if orig != dest:  # ignora o vértice de origem
        caminho, custo = dijkstra(orig, dest, MA, cidades)  # executa o algoritmo 
de dijkstra para cada destino
        print(f"{cidades[dest]:<6} : {' > '.join(cidades[v] for v in caminho)} 
({custo}).")

# plota o grafo e o menor caminho
plot_grafos(MA, caminho1, orig, destf, cidades, Comp)
