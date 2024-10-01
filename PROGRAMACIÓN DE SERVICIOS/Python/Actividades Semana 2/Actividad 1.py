print("Pares e impares")
n = int(input("Introduce un número entero: "))
n2 = int(input(f"Introduce un número entero mayor o igual que {n}: "))

while n2 < n:
    n2 = int(input(f"He dicho un número mayor o igual que {n}: "))

while n <= n2:
    if n % 2 == 0:
        print(f"El número {n} es par")
    else:
        print(f"El número {n} es impar")
    n += 1
