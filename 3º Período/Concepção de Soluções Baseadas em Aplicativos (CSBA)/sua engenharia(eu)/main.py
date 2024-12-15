from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.properties import ColorProperty

class Tela(BoxLayout):
    def clique(self):
        input1 = self.ids.text1.text
        input2 = self.ids.text2.text
        spinner = self.ids.saida_spinner.text
        op1 = self.ids.op1.active
        op2 = self.ids.op2.active
        check1 = self.ids.check1.text
        check2 = self.ids.check2.text
        if input1 == '' or input2 == '':
            self.ids.saida.text = f'Informe a caixa textinput'
        else:
            if op1 == True: 
                self.ids.saida.text = f'Faz {input1} e pretende trabalhar com {input2}.\nTem como hobby {spinner} e quer {check1}.'
            elif op2 == True:
                self.ids.saida.text = f'Faz {input1} e pretende trabalhar com {input2}.\nTem como hobby {spinner} e quer {check2}.'
    
class CursoApp(App):
    def build(self):
        self.title = 'Seu Curso'
        Window.size = (400,500)
        return Tela()
    
CursoApp().run()