numero = int(input("Introduce un número: "))

lista = []
for i in range(1, numero + 1):
    if numero % i == 0:
        lista.append(i)

print(f"{numero} tiene {len(lista)} divisores: {lista}")
