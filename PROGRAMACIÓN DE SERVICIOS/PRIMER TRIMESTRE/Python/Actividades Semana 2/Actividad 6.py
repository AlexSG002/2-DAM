cantidad = int(input("Introduce el número de números que vayas a introducir: "))
contP=0
contI=0
for i in range(0, cantidad,1):
    n2 = int(input("Introduce un número: "))
    if(n2%2==0):
        contP+=1
    else:
        contI+=1
    i+1
print(f"El número de números pares introducidos fue de: {contP}")
print(f"El número de números impares introducidos fue de: {contI}")