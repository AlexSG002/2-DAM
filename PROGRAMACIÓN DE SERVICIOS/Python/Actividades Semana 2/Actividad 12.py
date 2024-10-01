n1 = int(input("Introduce un número mayor que 0: "))
total=1
i=0
while n1>0:
    for i in range(i+1,n1):
        total+=total*i
        i+1
    print(f"El factorial de {n1} es: {total}")
    break
else:
    n1 = int(input("Introduce un número mayor que 0: "))