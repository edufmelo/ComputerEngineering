def calculo(n):
    if n == 1:
        return 1
    else:
        return 3*calculo(n-1)
    
print(calculo(3))

def somatorio(n):
    if n <= 0:
        return 0
    else:
        return n + somatorio(n-1)
    
print(somatorio(3))

def somaelev(n):
    if n == 0:
        return 0
    else:
        return n**3 + somaelev(n-1)
    
print(somaelev(3))

def inverte(frase):
    if len(frase) == 1:
        return frase
    else:
        return inverte(frase[1:]) + frase[0]

print(inverte("oi"))

def somat(n):
    if n == 1:
        return 1
    else:
        return (1/n**2) + somat(n-1)
    
print(somat(3))

def maior(lista):
    if len(lista) == 1:
        return lista[0]
    else:
        maior_elemento = maior(lista[1:])
        if maior_elemento > lista[0]:
            return maior_elemento
        else:
            return lista[0] 

lista = [3,1,12,9]
print(maior(lista))
