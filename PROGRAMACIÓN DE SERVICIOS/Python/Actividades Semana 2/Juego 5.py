import random
print("Tirada de dados")
dados = int(input("Número de dados: "))
mayor = 0
i=0
while dados < 1: 
        print("Imposible!")
        dados = int(input("Número de dados: "))
        
print("Dados: ", end="")
for i in range(i, dados, 1):
    dado = random.randint(1,6)
    print(f"{dado} ", end="")
    if mayor < dado:
        mayor=dado
    i=i+1;
print(f"El dado mayor es: {mayor}")