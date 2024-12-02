import random
print("Tirada de dados")
dados = int(input("Número de dados: "))
total1 = 0
total2 = 0
i=0
while dados < 1:
        print("Imposible!")
        dados = int(input("Número de dados: "))
        
print("Dados J1: ", end="")
for i in range(i, dados, 1):
    dado = random.randint(1,6)
    print(f"{dado} ", end="")
    if dado%2==0:
         total1=total1+1
    i=i+1
print(f"La puntuación del J1 es: {total1}")
print("Dados J2: ", end="")
i=0
for i in range(i, dados, 1):
    dado = random.randint(1,6)
    print(f"{dado} ", end="")
    if dado%2!=0:
         total2=total2+1
    i=i+1;
print(f"La puntuación del J2 es: {total2}")

if total1 == total2:
     print("Empate!")
elif total1 > total2:
     print("Gana el J1")
else:
     print("Gana el J2")