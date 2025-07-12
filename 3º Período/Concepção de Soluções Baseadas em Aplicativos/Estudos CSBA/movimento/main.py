from kivy.app import App
from kivy.uix.floatlayout import FloatLayout
from kivy.core.window import Window
from kivy.clock import Clock
import random
import math

class Principal(FloatLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        self.direcao = [0,0]

        #evento de relogio
        self.evento = None

        #indica se está movendo ou não
        self.movendo = False

        #posição central do botao
        self.bt_pos = [150,150]
        self.move_botao()

    def move_botao(self):
        b = self.ids.botao
        b.pos = (self.bt_pos[0] - b.width/2, self.bt_pos[1] - b.height/2)

    def clique(self):
        #direção é aleatoria entre
        ang = random.random() * 2 * math.pi
        speed = 12
        self.direcao = [math.sin(ang) * speed, math.cos(ang) * speed]

        #garante que o evento será criado uma única vez.
        if (self.evento == None):
            self.evento = Clock.schedule_interval(self.mover, 0.05)

    def mover(self, dt):
        #ajusta a posição atual
        self.bt_pos[0] += self.direcao[0]
        self.bt_pos[1] += self.direcao[1]
        #move o botão
        self.move_botao()
        #verifica se o botão "bateu" em uma das laterais do quadro
        self.colisao()

    def colisao(self):
        sb = self.ids.botao.size
        if self.bt_pos[0] + sb[0]/2 > self.width: #parede da direita
            self.direcao[0] *= -1
        if self.bt_pos[0] - sb[0]/2 < 0: #parede da esquerda
            self.direcao[0] *= -1
        if self.bt_pos[1] + sb[1]/2 > self.height: #parede da cima
            self.direcao[1] *= -1
        if self.bt_pos[1] - sb[1]/2 < 0: #parede da baixo
            self.direcao[1] *= -1


class MovimentoApp(App):
    def build(self):
        Window.size = (300, 300)

        self.title = "Timer"
        return Principal()
    
MovimentoApp().run()