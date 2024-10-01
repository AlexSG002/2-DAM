import random
print("Tirada de dados")
dados = int(input("Número de dados: "))
objetivo = int(input("Número objetivo: "))
ganar=False;
i=0
while dados < 1 or objetivo < 0 or objetivo >=6:
        print("Imposible!")
        break;
print("Dados: ", end="")
while not ganar:
    for i in range(i, dados, 1):
        dado = random.randint(1,6)
        print(f"{dado} ", end="")
        if dado == objetivo:
            ganar=True;
    i=i+1;
if(ganar):
    print("Has ganado!")
elif(not ganar):
    print("Has perdido :c")


