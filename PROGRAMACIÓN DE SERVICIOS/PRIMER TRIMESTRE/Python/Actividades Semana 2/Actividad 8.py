cantidad = int(input("Introduce el número de números que vayas a introducir: "))
for i in range(0, cantidad,1):
    n2 = float(input("Introduce un número: "))
    n2=n2+n2
print(f"La suma de todos los números introducidos fue: {n2}")