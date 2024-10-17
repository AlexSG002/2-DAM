num = int(input("Introduce el número de palabras que vas a escribir: "))
i=0
lista=[]
for i in range(i, num, 1):
    palabra=input(f"Introduce la palabra {i+1}: ")
    lista.append(palabra)
print(f"La lista de elementos es: {(lista)}")

palabraBuscada=input(f"Introduce la palabra a buscar: ")
print(f"La palabra '{palabraBuscada}' aparece {lista.count(palabraBuscada)} veces en la lista")