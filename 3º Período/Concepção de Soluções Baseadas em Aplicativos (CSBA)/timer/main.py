from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.clock import Clock
from kivy.core.audio import SoundLoader

class Principal(BoxLayout):
    def __init__(self, **kwargs):
        super().__init__(**kwargs)

        self.estado = "off"
        self.tempo = 0.0
        self.evento = None

        self.beep = SoundLoader.load("beep.mp3")

    def clique_botao(self):        
        if self.estado == "off": #inicia o timer
            #valida o tempo fornecido pelo usuário
            try:
                self.tempo = int(self.ids.tempo_timer.text)
            except:
                return
            #muda o estado do botão e do timer
            self.ids.botao_timer.text = "Parar"
            self.estado = "on"
            self.atual = self.tempo
            self.ids.timer.text = f"{self.atual:0.0f}"
            #cria um evento de relogio, que irá chamar o método muda_tempo a cada 1s
            self.evento = Clock.schedule_interval(self.muda_tempo, 1)
        else: #para o timer
            self.ids.botao_timer.text = "Iniciar"
            self.ids.timer.text = "0"
            #cancela o evento de relogio
            self.evento.cancel()
            self.estado = "off"

    #metodo chamado pelo relogio a cada 1s
    def muda_tempo(self, dt):
        self.atual -= dt
        self.atual = round(self.atual) #arredonda para evitar problemas de precisão
        self.ids.timer.text = f"{self.atual:0.0f}"
        if self.atual <= 0:
            self.clique_botao()
            self.beep.play()

class TimerApp(App):
    def build(self):
        Window.size = (500, 300)

        self.title = "Timer"
        return Principal()
    
TimerApp().run()