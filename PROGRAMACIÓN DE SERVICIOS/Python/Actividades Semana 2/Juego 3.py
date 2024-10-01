import random
print("Tirada de dados")
jugadores = int(input("Número de Jugadores: "))
objetivo = int(input("Número objetivo: "))
i=0
while jugadores > 1 and objetivo > 0 and objetivo <=6:
    for i in range(i, jugadores, 1):
        print(f"Jugador {i+1}: ", end="")
        dado = random.randint(1,6)
        if dado == objetivo:
            print(f"{dado} CONSEGUIDO")
        else:
            print(dado)
    break
else:
    print("¡Imposible!")