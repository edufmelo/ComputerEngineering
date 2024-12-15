from kivy.app import App
from kivy.uix.boxlayout import BoxLayout
from kivy.core.window import Window

class Tela(BoxLayout):
    def __init__(self,**kwargs):
        super().__init__(**kwargs)
        Window.size = (400,500)
        self.total = 0
        self.preco = {'Marguerita': 38,
                      'Napolitana': 40,
                      'Atum': 39.9,
                      '5 queijos':45,
                      'Frango com Catupiry':51}

    def ativar(self,situacao,texto):
        if self.ids.nome.focus == False:
            self.ids.sbv.text = f'Seja bem vinda(o) {self.ids.nome.text}'    
            self.ids.tx1.focus=True
        if situacao:
            if texto == 'Marguerita':
                self.total += int(self.ids.tx1.text) * self.preco['Marguerita']
            if texto == 'Napolitana':
                self.total += int(self.ids.tx2.text) * self.preco['Napolitana'] 
            
            if texto == 'Atum':
                self.total += int(self.ids.tx3.text) * self.preco['Atum']                
                
            if texto == '5 queijos':
                self.total += int(self.ids.tx4.text) * self.preco['5 queijos']                
            
            if texto == 'Frango com Catupiry':
                self.total += int(self.ids.tx5.text) * self.preco['Frango com Catupiry']
        
    def pagar(self,tipo):
        if tipo == 'A vista (-5%)':
            self.total -= (self.total*5/100)
        elif tipo == '2 vezes (+2,5%)':
            self.total += (self.total*2.5/100)
        else:
            self.total += (self.total*8/100)
        self.ids.total.text = f'A pagar R$ {round(self.total,2)}'
    def limpa(self):
        self.total = 0
        self.ids.nome.text   = ''
        self.ids.id1.active  = False
        self.ids.id2.active  = False
        self.ids.id3.active  = False
        self.ids.id4.active  = False
        self.ids.id5.active  = False
        self.ids.tx1.text  = ''    
        self.ids.tx2.text  = ''
        self.ids.tx3.text  = ''
        self.ids.tx4.text  = ''
        self.ids.tx5.text  = ''
        self.ids.sp1.text  = 'pagamento'
        self.ids.total.text= 'A pagar R$ '
class pizzariaApp(App):
    def build(self):
        return Tela() 
pizzariaApp().run()
       