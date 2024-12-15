from kivy.app import App
from kivy.uix.boxlayout import BoxLayout

#classe que representa uma tela do app. 
# Todos os eventos desta tela são escritos nela
#Principal é uma subclasse de BoxLayout, então os objetos nela
# seguem o principio de organização de um BoxLayout
class Principal(BoxLayout):
    pass

#classe principal do app. O nome dela indica o nome do arquivo KV
# como a classe se chama InterfaceApp, remova o sufixo App e você 
# tem o nome do arquivo kv. No caso interface.kv
class InterfaceApp(App):
    def build(self):
        #retorna uma instancia da tela do app
        return Principal()
    
InterfaceApp().run()