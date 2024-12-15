# PY 
from kivy.app import App
from kivy.uix.widget import Widget
from kivy.core.window import Window

class Tela(Widget):
    def escolha(self, texto):
        print('Então você gosta de assistir', texto)

    def clique_assistir(self):
        msg = 'Você gosta de assistir '
        msg += self.ids.spinner_serie.text
        if self.ids.pipoca.active == True:
            msg += ' comendo pipoca'
        elif self.ids.opmanha.active == True:
            msg += ' de manhã'
        elif self.ids.opnoite.active == True:
            msg += ' de noite'
        self.ids.label_saida.text = msg

class AulaApp(App):
    def build(self):
        Window.size = (400,300)
        self.title = 'Série favorita'
        return Tela()

AulaApp().run()