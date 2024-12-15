import pickle
import os
from DequeVC import Deque, Except

class compFerroviaria(Deque):
    def __init__(self, N, Comp_Fer, potencia_minima):
        super().__init__(N)
        self.Comp_Fer = Comp_Fer
        self.potencia_minima = potencia_minima
        self._locomotiva_potencia = 0  # Inicialize o atributo
        self._peso_composicao = 0      # Inicialize o atributo
        
        if os.path.exists(Comp_Fer):
            print(f"\nArquivo '{Comp_Fer}' carregado com sucesso.")
            self.carregar()
        else:
            print(f"\nAviso: O arquivo '{Comp_Fer}' não foi encontrado. A composição foi inicializada vazia.")


    def salvar(self):
        with open(self.Comp_Fer, 'wb') as arquivo:
            pickle.dump(self, arquivo)
            print(f"Composição salva no arquivo: {self.Comp_Fer}")


    def add_objeto(self, objeto, posicao):
        if posicao == 1:
            self.addFirst(objeto)
        elif posicao == 2:
            self.addLast(objeto)
        self.salvar()

    def del_objeto(self, posicao):
        if posicao == 1:
            self.deleteFirst()
        elif posicao == 2:
            self.deleteLast()
        self.salvar()

    def carregar(self):
        try:
            with open(self.Comp_Fer, 'rb') as f:
                dados = pickle.load(f)
                if isinstance(dados, Deque):
                    self._data = dados._data
                    self._size = dados._size
                    self._front = dados._front
                    self._top = dados._top
                    self._locomotiva_potencia = getattr(dados, '_locomotiva_potencia', 0)
                    self._peso_composicao = getattr(dados, '_peso_composicao', 0)
        except (FileNotFoundError, EOFError) as e:
            print(f"Erro ao carregar o arquivo: {e}")
            self._data = []
            self._size = 0
            self._front = 0
            self._top = 0
            self._locomotiva_potencia = 0
            self._peso_composicao = 0


    def calcPesoTotal(self):
        peso_total = 0
        for vagao in self._data:
            if vagao is not None:
                peso_total += vagao.peso
        return peso_total

    def calcCompTotal(self):
        comp_total = 0
        for vagao in self._data:
            if vagao is not None:
                comp_total += vagao.comp + 2
        return comp_total - 2

    def contar_passageiros_total(self):
        total_passageiros = 0
        for vagao in self._data:
            if isinstance(vagao, Passageiro):
                total_passageiros += vagao.passageiros
        return total_passageiros
    
    def contar_carga_total(self):
        total_carga = 0
        for vagao in self._data:
            if isinstance(vagao, Carga):
                total_carga += vagao.calcPesoCarga()
        return total_carga
    
    def contar_potencia_total(self):
        total_potencia = 0
        for vagao in self._data:
            if isinstance(vagao, Locomotiva):
                total_potencia += vagao.calculoPotencia()
        return total_potencia

    def get_size(self):
        return super().get_size()

    def criar_composicao_padrao(self):
        locomotiva = Locomotiva(150)
        self.addLast(locomotiva)

        for _ in range(30):
            carga = Carga(90)
            self.addLast(carga)

        for _ in range(50):
            passageiro = Passageiro(40, 25)
            self.addLast(passageiro)

        self.salvar()
        print("Composição padrão criada com 1 locomotiva, 30 vagões de carga e 50 vagões de passageiros.")
        
    def verificar_potencia(self):
        potencia_total = self.contar_potencia_total()
        peso_composicao = self.calcPesoTotal()

        if peso_composicao > 0:
            relacao_potencia_peso = potencia_total / peso_composicao
        else:
            raise ValueError("Peso da composição deve ser maior que zero.")
        
        if relacao_potencia_peso >= self.potencia_minima:
            print(f"\nRelação Potência/Peso: {relacao_potencia_peso:.2f} HP/Ton (Suficiente)\n")
        else:
            potencia_faltante = (self.potencia_minima * peso_composicao) - potencia_total
            locomotivas_necessarias = (potencia_faltante / Locomotiva(150).calculoPotencia())
            locomotivas_necessarias = int(locomotivas_necessarias) + (1 if potencia_faltante % Locomotiva(150).calculoPotencia() > 0 else 0)
            print(f"\nRelação Potência/Peso: {relacao_potencia_peso:.2f} HP/Ton (Insuficiente)")
            print(f"Falta {potencia_faltante:.2f} de potência.")
            print(f"Sugere-se adicionar {locomotivas_necessarias} locomotiva(s) adicional(is).\n")

    def imprime_descricao(self):
        qtde_locomotiva = 0
        peso_locomotiva = 0
        comp_locomotiva = 20
        potencia_locomotiva = 0

        qtde_passageiro = 0
        peso_passageiro = 0
        comp_passageiro = 18
        

        qtde_carga = 0
        peso_carga = 0
        comp_carga = 15
        total_carga=0

        for vagao in self._data:  
            if isinstance(vagao, Locomotiva):  # Se for vagão de locomotiva
                qtde_locomotiva += 1
                peso_locomotiva += vagao.peso
                potencia_locomotiva = vagao.calculoPotencia()
            
            elif isinstance(vagao, Passageiro):  # Se for vagão de passageiro
                
                qtde_passageiro += 1
                peso_passageiro += vagao.peso
                self.contar_passageiros_total()

            elif isinstance(vagao, Carga):  # Se for vagão de carga
                qtde_carga += 1
                peso_carga += vagao.peso
                total_carga +=vagao.calcPesoCarga()
        if qtde_passageiro==0:
            media_passageiros=0
        else:
            media_passageiros=self.contar_passageiros_total()/qtde_passageiro
        
                
        
        print(f"\n| Locomotiva: QTDE: {qtde_locomotiva}  | Peso(ton): {peso_locomotiva}  | Comp(m): {comp_locomotiva}   | HP: {potencia_locomotiva}  | Passag: -      | Carga(ton): -      |")
        print(f"| Passageiro: QTDE: {qtde_passageiro} | Peso(ton): {peso_passageiro} | Comp(m): {comp_passageiro}   | HP: -       | Passag: {media_passageiros}   | Carga(ton): -      |")
        print(f"| Carga:   QTDE: {qtde_carga}    | Peso(ton): {peso_carga} | Comp(m): {comp_carga}   | HP: -       | Passag: -      | Carga(ton): {total_carga} |")
        print(f"| Composição: QTDE: {qtde_carga+qtde_locomotiva+qtde_passageiro} | Peso(ton): {peso_carga+peso_locomotiva+peso_passageiro} | Comp(m): {self.calcCompTotal()} |  HP: {self.contar_potencia_total()} | Passag: - {self.contar_passageiros_total()} | Carga(ton): {self.contar_carga_total()} |")

    def limpar_arquivo(self):
        if os.path.exists(self.Comp_Fer):
            os.remove(self.Comp_Fer)
            print(f"Arquivo '{self.Comp_Fer}' apagado com sucesso.")
        else:
            print(f"O arquivo '{self.Comp_Fer}' não foi encontrado.")


class Vagao:
    def __init__(self, comp, peso):
        self.comp = comp
        self.peso = peso

    def imprime(self):
        print(f"Comprimento: {self.comp} m, Peso: {self.peso} toneladas")


class Locomotiva(Vagao):
    def __init__(self, peso):
        super().__init__(20, peso)

    def imprime(self):
        super().imprime()
        print("Tipo: Locomotiva")
        print(f"Potência: {self.calculoPotencia()} HP")

    def retornarPeso(self):
        if 100 < self.peso < 200:
            return self.peso
        else:
            print("Peso deve ser entre 100 e 200 toneladas.")

    def calculoPotencia(self):
        potencia = 2000 + (8000 / 100) * (self.peso - 100)
        return potencia


class Carga(Vagao):
    def __init__(self, peso):
        super().__init__(18, peso)

    def imprime(self):
        super().imprime()
        print("Tipo: Carga")
        print(f"Carga: {self.calcPesoCarga()} toneladas")

    def retornarPeso(self):
        if 80 <= self.peso <= 100:
            return self.peso
        else:
            print("Peso deve ser entre 80 e 100 toneladas.")

    def calcPesoCarga(self):
        peso_carga = 0.75 * self.peso
        return peso_carga

class Passageiro(Vagao):
    def __init__(self, peso, passageiros):
        super().__init__(15, peso)
        self.passageiros = self.verifica_passageiros(passageiros)

    def retornarPeso(self):
        if 30 < self.peso < 50:
            return self.peso
        else:
            print("Peso deve ser entre 30 e 50 toneladas.")

    def verifica_passageiros(self, passageiros):
        if passageiros > 30 or passageiros < 0:
            print("O número de passageiros não pode ultrapassar 30 nem ser menor que 0!")
            return 0
        else:
            return passageiros

    def imprime(self):
        super().imprime()
        print("Tipo: Passageiro")
        print(f"Número de passageiros: {self.passageiros}")

def menu():
    print('\n=============== Escolha a opção desejada ===============')
    print('\n< 1 > Criar Composição Padrão')
    print('< 2 > Inserir vagão')
    print('< 3 > Remover vagão')
    print('< 4 > Apresentação Descrição')
    print('< 5 > Apresentar os dados do primeiro vagão')
    print('< 6 > Apresentar os dados do último vagão')
    print('< 7 > Apresentar os dados de todos vagões')
    print('< 8 > Verificar potência mínima de desempenho')
    print('< 0 > Sair\n')
    print('========================================================\n')

    opcao = int(input('Digite a opção desejada: '))

    return opcao


def adicionar_vagao(composicao):
    print("\nEscolha o tipo de vagão:")
    print("< 1 > Locomotiva")
    print("< 2 > Passageiro")
    print("< 3 > Carga")

    tipo_vagao = int(input('Digite o tipo de vagão desejado: '))
    if tipo_vagao == 1:
        peso = float(input('Digite o peso da locomotiva: '))
        vagao = Locomotiva(peso)

    elif tipo_vagao == 2:
        peso = float(input('Digite o peso do vagão de passageiro: '))
        passageiros = int(input('Digite o número de passageiros: '))
        vagao = Passageiro(peso, passageiros)

    elif tipo_vagao == 3:
        peso = float(input('Digite o peso do vagão de carga: '))
        vagao = Carga(peso)

    else: 
        print("Tipo de vagão inválido.")
        return
    
    # Adicionar escolha para adicionar no começo ou no final
    print("Deseja adicionar o vagão no início ou no final da composição?")
    print("< 1 > Início (addFirst)")
    print("< 2 > Final (addLast)")
    
    escolha_posicao = int(input("Digite a opção desejada: "))
    composicao.add_objeto(vagao,escolha_posicao)

    print("Vagão adicionado com sucesso!")

def remover_vagao(composicao):
    print("Remover vagão")
    print("< 1 > Início (deleteFirst)")
    print("< 2 > Final (deleteLast)")
    escolha_posicao = int(input("Digite a opção desejada: "))
    composicao.del_objeto(escolha_posicao)


tamanho = int(input("\nDigite o tamanho do Deque (Tamanho maior/igual a 81): "))
while tamanho < 81:
    print("Tamanho da Composição Padrão é 81! Digite um tamanho maior ou igual")
    tamanho = int(input("\nDigite o tamanho do Deque (Tamanho maior/igual 81): "))
print(f"\nTamanho definido como {tamanho}")
composicao = compFerroviaria(tamanho, 'dados.pkl', 1.05)  # O número 81 (1 locomotiva + 30 + 50 vagões)


terminou = 1
while terminou == 1:
    op = menu()
    
    if op == 0:
        print('Encerrando o programa...')
        terminou=0
    
    elif op == 1:
        if Deque.is_full(composicao):
            print("Arquivo/Deque cheio!")
            print("Limpando arquivo...\n")
            composicao.limpar_arquivo()
        composicao.criar_composicao_padrao()
        print("Composição padrão inicializada")
        menu()
    
    elif op == 2:
        if Deque.is_full(composicao):
            print("Arquivo/Deque cheio!")
            print("Limpando arquivo...\n")
            composicao.limpar_arquivo()
        print("Inserindo vagão")
        adicionar_vagao(composicao)
        print("Vagão adicionado com sucesso!")
        menu()
    
    elif op == 3:
        print("Removendo vagão")
        remover_vagao(composicao)
        print("Vagão removido com sucesso!")
        menu()
    
    elif op == 4:
        print("\nDescrição da Composição Ferroviária:")
        composicao.imprime_descricao()
        menu()
    
    elif op == 5:
        print("Dados do primeiro vagão:")
        composicao.rewind()
        vagao = composicao.next()
        if vagao:
            vagao.imprime()
        menu()
    
    elif op == 6:
        print("Dados do último vagão:")
        composicao.rewind()  # Reposiciona o ponteiro para o início
        for _ in range(composicao.get_size() - 1):  # Avança até o último vagão
            composicao.next()
        lastvagao = composicao.next()  # Obtém o último vagão
        if lastvagao:
            lastvagao.imprime()  # Mostra os dados do vagão
        menu()
    
    elif op == 7:
        print("Dados de todos os vagões (percorrendo todo Deque):")
        composicao.rewind()
        for i in range(composicao.get_size()):
            vagao = composicao.next()
            if vagao:
                vagao.imprime()
        menu()

    elif op == 8:
        composicao.verificar_potencia()

    else:
        print("Digite um número de 0 a 7")
        terminou=0
