import sys

if len(sys.argv)==3:
    suma=int(int(sys.argv[1])+int(sys.argv[2]))
    print(f"La suma es: {suma}")
else:
    print("Error, introduce los parámetros correctamente")