import random
print("Tirada de dados")
dados = int(input("Número de dados: "))
mayor1 = 0
mayor2 = 0
i=0
while dados < 1:
        print("Imposible!")
        dados = int(input("Número de dados: "))
        
print("Dados J1: ", end="")
for i in range(i, dados, 1):
    dado = random.randint(1,6)
    print(f"{dado} ", end="")
    if mayor1 < dado:
        mayor1=dado
    i=i+1
print(f"El dado mayor del J1 es: {mayor1}")
print("Dados J2: ", end="")
i=0
for i in range(i, dados, 1):
    dado = random.randint(1,6)
    print(f"{dado} ", end="")
    if mayor2 < dado:
        mayor2=dado
    i=i+1;
print(f"El dado mayor del J2 es: {mayor2}")

if mayor1 == mayor2:
     print("Empate!")
elif mayor1 > mayor2:
     print("Gana el J1")
else:
     print("Gana el J2")