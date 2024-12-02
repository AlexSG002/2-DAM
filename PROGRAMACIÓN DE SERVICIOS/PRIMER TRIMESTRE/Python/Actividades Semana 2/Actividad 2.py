n1 = int(input("Introduce un número mayor que 0: "))
while n1<=0:
    n1=int(input("Un número mayor que 0 por favor: "))

print(f"Los divisores de {n1} son: ")
for i in range (1, n1+1):
    if n1%i==0:
        print(i)