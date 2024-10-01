import random

J1 = "Carmen"
J2 = "David"

Total1=0;
Total2=0;
dado1 = random.randint(1,6)
dado2 = random.randint(1,6)
dado3 = random.randint(1,6)
dado4 = random.randint(1,6)
print(J1+" ha sacado un : "+str(dado1)+" y un : "+str(dado2))
print(J2+" ha sacado un : "+str(dado3)+" y un : "+str(dado4))
Total1 = dado1+dado2
Total2 = dado3+dado4



if(Total1>Total2):
    print("Ha ganado "+J1);
elif(Total1==Total2):
    print("Empate")
else:
    print("Ha ganado "+J2)
