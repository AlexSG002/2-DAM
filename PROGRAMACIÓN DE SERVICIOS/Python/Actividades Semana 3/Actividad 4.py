num = int(input("Introduce el número de palabras que vas a escribir: "))
lista = []

for i in range(num):
    palabra = input(f"Introduce la palabra {i + 1}: ")
    lista.append(palabra)

print(f"La lista de elementos es: {lista}")

palabraBorr = input("Introduce la palabra a borrar: ")

while palabraBorr in lista:
    lista.remove(palabraBorr)

print(lista)
