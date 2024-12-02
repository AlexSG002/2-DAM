import random

J1 = "Gloria"
J2 = "Hector"

Total1=0;
Total2=0;
carta1 = random.randint(1,10)
carta2 = random.randint(1,10)
carta3 = random.randint(1,10)
carta4 = random.randint(1,10)
carta5 = random.randint(1,10)
carta6 = random.randint(1,10)
print(J1+" ha sacado un : "+str(carta1)+", un : "+str(carta2)+" y un : "+str(carta3))
print(J2+" ha sacado un : "+str(carta4)+", un : "+str(carta5)+" y un : "+str(carta6))
Total1 = carta1+carta2+carta3
Total2 = carta4+carta5+carta6

if(Total1 and Total2>15):
    print("Empate porque los 2 pasan de 15")
elif(Total1>15):
    print(J1+" pierde porque se pasa de 15")
elif(Total2>15):
    print(J2+" pierde porque se pasa de 15")
elif(Total1>Total2):
    print("Ha ganado "+J1);
elif(Total1==Total2):
    print("Empate")
else:
    print("Ha ganado "+J2)

