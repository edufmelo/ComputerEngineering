from kivy.app import App
from kivy.core.window import Window
from kivy.uix.screenmanager import ScreenManager
from kivy.uix.screenmanager import Screen

class PrimeiraTela(Screen):
    def proximo(self):
        self.manager.transition.direction = 'left'
        self.manager.current = "segunda"

class SegundaTela(Screen):
    def anterior(self):
        self.manager.transition.direction = 'right'
        self.manager.current = "primeira"

    def proximo(self):
        self.manager.transition.direction = 'left'
        self.manager.current = "terceira"

class TerceiraTela(Screen):
    def anterior(self):
        self.manager.transition.direction = 'right'
        self.manager.current = "segunda"

class MultitelasApp(App):
    def build(self):
        #window.size permite definir o tamanho do quadro
        Window.size = (300, 200)

        #self.title define o titulo do quadro
        self.title = "Multiplas telas"

        telas = ScreenManager()
        telas.add_widget(PrimeiraTela(name="primeira"))
        telas.add_widget(SegundaTela(name="segunda"))
        telas.add_widget(TerceiraTela(name="terceira"))

        telas.current = "primeira"
        return telas
    
MultitelasApp().run()