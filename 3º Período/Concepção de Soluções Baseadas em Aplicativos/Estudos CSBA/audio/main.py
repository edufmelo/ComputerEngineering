from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.core.audio import SoundLoader
from kivy.clock import Clock

class Principal(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        #carrega o arquivo de música
        #Music by SergePavkinMusic from Pixabay
        self.musica = SoundLoader.load("reflected-light.mp3")

        self.musica.bind(on_play = self.play)
        self.musica.bind(on_stop = self.stop)

        #tag para indicar se a música está tocando ou não
        self.isplaying = False

        #define o range para a posicao da musica
        self.ids.posicao.range = (0, self.musica.length)

        #evento para controlar a posição da música
        self.evento_posicao = None

    def clique_botao(self):
        if self.isplaying == False:
            self.musica.play()
            self.isplaying = True
            self.ids.botao.text = "<"
        else:
            self.musica.stop()
            self.isplaying = False
            self.ids.botao.text = "4"

    def play(self, obj):
        if self.evento_posicao == None:
            self.evento_posicao = Clock.schedule_interval(self.muda_posicao, 1)

    def stop(self, obj):
        self.evento_posicao.cancel()
        self.ids.posicao.value = 0

    def muda_volume(self, valor):
        self.musica.volume = valor

    def muda_posicao(self, valor):
        self.ids.posicao.value += valor

class AudioApp(App):
    def build(self):
        Window.size = (350, 300)

        self.title = "Exemplo de Audio"
        return Principal()
    
AudioApp().run()