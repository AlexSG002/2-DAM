cantidad = int(input("Introduce el número de números que vayas a introducir: "))
cont=0
for i in range(0, cantidad,1):
    n2 = int(input("Introduce un número: "))
    if(n2<0):
        cont+=1
    i+1
print(f"El número de números negativos introducidos fue de: {cont}")