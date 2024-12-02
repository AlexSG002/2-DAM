cantidad = int(input("Introduce el número de números que vayas a introducir: "))
primerNum = int(input("Introduce un número: "))
for i in range(1, cantidad,1):
    n2 = int(input("Introduce un número: "))
    i+1
    if(n2<primerNum):
        print(f"El número {n2} es menor que el número {primerNum}")