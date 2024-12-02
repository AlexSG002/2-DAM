n1 = int(input("Introduce un número: "))
n2 = int(input("Introduce otro número: "))
total=0
for i in range(n1+1,n2,1):
    total=total+i
print(f"La suma total de los números entre {n1} y {n2} es: {total}")