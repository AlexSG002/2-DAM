n1 = int(input("Introduce un número: "))
n2 = int(input("Introduce otro número: "))
total=0
if n1<n2:
    for i in range(n1+1,n2,1):
        print(f"Total actual: {total} + {i}")
        total=total+i
        print(f"={total}")
    print(f"La suma total de los números entre {n1} y {n2} es: {total}")
else:
    for i in range(n2+1,n1,1):
        print(f"Total actual: {total} + {i}")
        total=total+i
        print(f"={total}")
    print(f"La suma total de los números entre {n1} y {n2} es: {total}")