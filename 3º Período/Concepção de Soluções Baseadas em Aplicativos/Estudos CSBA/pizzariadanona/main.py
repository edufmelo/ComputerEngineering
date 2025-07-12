from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.properties import StringProperty

class Tela(BoxLayout):
    logo = StringProperty('pizza.png')
    
class PizzariaApp(App):
    def build(self):
        self.title = 'Pizzaria da Nona'
        Window.size = (400,600)
        return Tela()
    
PizzariaApp().run()