import random

J1 = "Alvaro"
J2 = "Barbara"

dado1 = random.randint(1,6)
dado2 = random.randint(1,6)

print(J1+" ha sacado un : "+str(dado1))
print(J2+" ha sacado un : "+str(dado2))

if(dado1>dado2):
    print("Ha ganado "+J1);
elif(dado1==dado2):
    print("Empate")
else:
    print("Ha ganado "+J2)
