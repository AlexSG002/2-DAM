cantidad = int(input("Introduce el número de números que vayas a introducir: "))
mayor=0
menor=100000000
total=0
for i in range(0, cantidad, 1):
    n1 = int(input("Introduce un número: "))
    if n1>mayor:
        mayor=n1
    if n1<menor:
        menor=n1
    total=total+n1
print(f"El mayor es: {mayor}, el menor es: {menor} y la media entre: {total} y {cantidad} es: {total/cantidad}")
