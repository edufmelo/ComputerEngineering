class NRac():

    def __init__(self, num, den):
        self.num = num
        self.den = den
        if self.den == 0:
            print("Denominador não pode ser zero.")

    def __str__(self):
        return f'{self.num}/{self.den}'

    def __add__(self, other):
        num = self.num * other.den + other.num * self.den
        den = self.den * other.den
        return NRac(num, den)

    def __sub__(self, other):
        num = self.num * other.den - other.num * self.den
        den = self.den * other.den
        return NRac(num, den)

    def __mul__(self, other):
        num = self.num * other.num
        den = self.den * other.den
        return NRac(num, den)

    def __truediv__(self, other):
        num = self.num * other.den
        den = self.den * other.num
        if den == 0:
            print("ERRO! Não é possível realizar divisão por zero.")
        return NRac(num, den)

    def simplifica(self):
        def mdc(num, den):
            while den != 0:
                resto = num % den
                num = den
                den = resto
            return num

        divisor = mdc(self.num, self.den)
        self.num = self.num // divisor
        self.den = self.den // divisor

        if self.den < 0:
            self.num = -self.num
            self.den = -self.den


q1 = NRac(6, 2)
q2 = NRac(2, 1)

soma = (q1+q2)
subtracao = (q1-q2)
mult = (q1*q2)
divisao = (q1/q2)

print("----- Operações não simplificadas -----\n")
print(f'Q1 = {q1};\nQ2 = {q2};\n')
print(f'{q1} + {q2} = {soma}')
print(f'{q1} - {q2} = {subtracao}')
print(f'{q1} * {q2} = {mult}')
print(f'{q1} / {q2} = {divisao}')

q1.simplifica()
q2.simplifica()
soma.simplifica()
subtracao.simplifica()
mult.simplifica()
divisao.simplifica()

print("\n----- Operações simplificadas -----\n")
print(f'Q1 = {q1} (simplificado);\nQ2 = {q2} (simplificado);\n')
print(f'{q1} + {q2} = {soma} (simplificado)')
print(f'{q1} - {q2} = {subtracao} (simplificado)')
print(f'{q1} * {q2} = {mult} (simplificado)')
print(f'{q1} / {q2} = {divisao} (simplificado)')


