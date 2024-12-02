num = int(input("Introduce el número de palabras que vas a escribir: "))
i=0
lista=[]
for i in range(i, num, 1):
    palabra=input(f"Introduce la palabra {i+1}: ")
    lista.append(palabra)
print(f"La lista de elementos es: {(lista)}")

palabraCamb=input(f"Introduce la palabra a cambiar: ")
palabraPorCamb = input(f"Introduce la palabra por la que deseas sustituir a {palabraCamb}: ")

i=0
for i in range(i,num,1):
    if lista[i]==palabraCamb:
        lista.insert(i,palabraPorCamb)
        lista.remove(palabraCamb)
        break
    i=i+1
print(lista)