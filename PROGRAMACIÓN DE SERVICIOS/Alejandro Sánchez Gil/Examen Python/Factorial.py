factorial = 5;
auxiliar = factorial
cont = factorial
for i in range(0, cont-1):
    auxiliar = auxiliar-1
    factorial = factorial * auxiliar
print("5 factorial es: "+str(factorial))