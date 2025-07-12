from kivy.app import App
from kivy.uix.floatlayout import FloatLayout
from kivy.core.window import Window

class Principal(FloatLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        
        #evento para capturar a posicao do mouse
        Window.bind(on_motion=self.mover)        

        #flag para indicar se o botão está movendo
        self.is_movendo = False

    #inicio do movimento ao pressionar o botão (on_press)
    def inicio_mover(self):
        self.is_movendo = True

    #fim do movimento ao soltar o botão (on_release)
    def fim_mover(self):
        self.is_movendo = False

    def mover(self, etype, tipo, pos):
        #botao que vai mover
        b = self.ids.botao
        #ajusta a posicao do botao a partir da posicao (pos.spos) do mouse
        if self.is_movendo:
            #spos é a posição do mouse em % de tela
            b.pos = pos.spos[0] * self.width - b.width/2, pos.spos[1] * self.height - b.height/2

class ArrastarApp(App):
    def build(self):
        Window.size = (400, 600)
        return Principal()

ArrastarApp().run()