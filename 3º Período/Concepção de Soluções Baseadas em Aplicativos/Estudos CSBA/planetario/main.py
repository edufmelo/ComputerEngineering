from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.properties import StringProperty
from kivy.core.audio import SoundLoader

class Tela(BoxLayout):
    imagem_fundo = StringProperty('terra.jpg')

    def __init__(self, **kwargs):
        super().__init__(**kwargs)
        self.som_terra = SoundLoader.load('SomTerra.wav')
        self.som_lua = SoundLoader.load('SomLua.wav')
        self.som_saturno = SoundLoader.load('SomSaturno.wav')
        self.som_jupiter = SoundLoader.load('SomSaturno.wav')

    def muda_gravidade(self, planeta):
        if planeta == 'Terra':
            self.ids.mensagem.text = ' Terra'
            self.imagem_fundo = 'terra.jpg'
            self.som_terra.play()
        elif planeta == 'Lua':
            self.ids.mensagem.text = 'Nossa Lua'
            self.imagem_fundo = 'lua.jpg'
            self.som_lua.play()
        elif planeta == 'Saturno':
            self.ids.mensagem.text = 'Planeta Saturno'
            self.imagem_fundo = 'saturno.jpg'
            self.som_saturno.play()
        elif planeta == 'Jupiter':
            self.ids.mensagem.text = 'Planeta Jupiter'
            self.imagem_fundo = 'jupiter.jpg'
            self.som_saturno.play()

    def define_som(self, marcado):
        volume = 0 
        if marcado == True:
            volume = 1
        self.som_terra.volume = volume
        self.som_lua.volume = volume
        self.som_saturno.volume = volume
        self.som_jupiter.volume = volume

    def sair(self):
        App.get_running_app().stop()

class PlanetarioApp(App):
    def build(self):
        Window.size = (400,600)
        self.title = 'Planetário'
        return Tela()
    
PlanetarioApp().run()