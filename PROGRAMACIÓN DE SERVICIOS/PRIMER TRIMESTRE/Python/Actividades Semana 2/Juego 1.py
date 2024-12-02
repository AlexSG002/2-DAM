import random
print("Tirada de dados")
dados = int(input("Número de dados: "))
i=0
while dados>0:
    print("Dados: ", end="")
    for i in range(i, dados, 1):
        dado = random.randint(1,6)
        print(dado, end=" ")
    break
else:
    print("¡Imposible!")