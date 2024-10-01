import random
print("Tirada de dados")
jugadores = int(input("Número de Jugadores: "))
i=0
while jugadores>1:
    for i in range(i, jugadores, 1):
        print(f"Jugador {i+1}: ", end="")
        dado = random.randint(1,6)
        print(dado)
    break
else:
    print("¡Imposible!")