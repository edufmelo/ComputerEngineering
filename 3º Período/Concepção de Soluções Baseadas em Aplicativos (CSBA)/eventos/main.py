from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window

class Principal(BoxLayout):
    def pressionar_botao(self):
        #ids é um dicionário, com todos os objetos que receberam um id no arquivo .kv
        #saida é o id do label onde quero mostrar o resultado.
        #text é o atributo do label que define o texto dele
        
        #essa linha significa, portanto:
        #coloque a frase "pressionou botao" como o texto do label que eu chamei de saida no .kv
        self.ids.saida.text = "pressionou botão"

    def clicar_botao(self):
        self.ids.saida.text = "clicou no botão"
        
    def digitar_no_input(self):
        digitado = self.ids.campo_digitar_1.text
        self.ids.saida.text = "Digitou: " + digitado
        
    def digitar_no_input_2(self, texto_digitado):
        #no kv o método recebe como parametro self.text que é o texto do textinput
        #isso poupa o trabalho de ler o texto via id
        self.ids.saida.text = "Digitou: " + texto_digitado
        
    def escolhe_spinner(self, opcao_escolhida):
        self.ids.saida.text = "Escolheu opção: " + opcao_escolhida

    def marcou_check(self, ativo):
        if ativo == True:
            self.ids.saida.text = "Marcou o checkbox"
        else:
            self.ids.saida.text = "Desarcou o checkbox"
    
    def marcou_opcao(self, opcao):
        if opcao == 'op_1':
            self.ids.saida.text = "Marcou a primeira opção"
        elif opcao == 'op_2':
            self.ids.saida.text = "Marcou a segunda opção"
        elif opcao == 'op_3':
            self.ids.saida.text = "Marcou a terceira opção"


class EventosApp(App):
    def build(self):
        #window.size permite definir o tamanho do quadro
        Window.size = (500, 600)

        #self.title define o titulo do quadro
        self.title = "Tipos de Label"
        return Principal()
    
EventosApp().run()