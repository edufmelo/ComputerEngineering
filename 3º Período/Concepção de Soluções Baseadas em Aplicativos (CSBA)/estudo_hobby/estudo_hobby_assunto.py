from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window
from kivy.properties import ColorProperty


class Conteudos:
    def __init__(self):
        self.lista = ['','','']
        self.dic = {'eng':'','per':'','hobby':self.lista,'assuntos':''}
    def guardaConteudo(self,chave,valor):
        self.dic[chave] = valor
    def guardaHobbies(self,pos,valor):
        self.lista[pos] = valor
        
        
objConteudo = Conteudos()

class Tela(BoxLayout):
    background_color = ColorProperty() # The ListProperty will also work.
    def __init__(self,**kwargs):
        super().__init__(**kwargs)
        Window.size = (600,400)
    def periodos(self,situacao,texto):
        if situacao:
            if texto == '1º':
                objConteudo.guardaConteudo('per','1º')
            elif texto == '2º':
                objConteudo.guardaConteudo('per','2º')
            else:
                objConteudo.guardaConteudo('per','3º')
    def hobbies(self,situacao,texto):
        if situacao:
            if texto == 'natação':
                objConteudo.guardaHobbies(0,'natacao')
            if texto == 'futebol':
                objConteudo.guardaHobbies(1,'futebol')
            if texto == 'games':
                objConteudo.guardaHobbies(2,'games')
    def assuntos(self,texto):
        if texto == 'Gastronomia':
            objConteudo.guardaConteudo('assuntos','Gastronomia')
        elif texto == 'Esportes':
            objConteudo.guardaConteudo('assuntos','Esportes')
        elif texto == 'Política':
            objConteudo.guardaConteudo('assuntos','Política')
        elif texto == 'Educação':
            objConteudo.guardaConteudo('assuntos','Educação')
        else:
            objConteudo.guardaConteudo('assuntos','Cuidados pessoais')
    def gera(self):
        objConteudo.guardaConteudo('eng',self.ids.curso.text)
        
        m = 'Você cursa Engª '+ objConteudo.dic['eng']+ ' e está no '+ objConteudo.dic['per'] + ' período.\nComo hobby você identifica: \n'
        m+=  objConteudo.dic['hobby'][0] + ' ' +objConteudo.dic['hobby'][1] + ' ' +objConteudo.dic['hobby'][2]+' e você se interessa por '+ objConteudo.dic['assuntos']+'\n'
        self.ids.mensagem.text = f'[b][color=#8B4513] {m} [/color][/b]  '
    def limpa(self):
        self.ids.curso.text = ''
        self.ids.ck1.active = False
        self.ids.ck2.active = False
        self.ids.ck3.active = False
        self.ids.ck4.active = False
        self.ids.ck5.active = False
        self.ids.ck6.active = False
        self.ids.mensagem.text = ''  
        self.ids.opcoes.text = 'assuntos'  
        objConteudo.lista = ['','','']
        objConteudo.dic = {'eng':'','per':'','hobby':objConteudo.lista,'assuntos':''}
        
    def sair(self):
        App.get_running_app.stop()

class estudoHobbyAssuntoApp(App):
    def build(self):
        return Tela(background_color = (1, 0, 1, 1))
meus = estudoHobbyAssuntoApp()
meus.run()
