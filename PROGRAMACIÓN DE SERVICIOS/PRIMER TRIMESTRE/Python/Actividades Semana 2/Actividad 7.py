n1 = int(input("Introduce un número mayor que 1: "))
while n1<=0:
    n1=int(input("Un número mayor que 1: "))


if n1 == 2:
    print("El número introducido es primo")
else:
    for i in range(2, int(n1/2) + 1):
        if n1 % i == 0:
            print("El número introducido no es primo")
            break
        else:
            print("El número introducido es primo")
            break
