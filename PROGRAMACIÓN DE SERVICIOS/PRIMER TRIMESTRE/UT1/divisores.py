import sys

if len(sys.argv)==2:
    numero = int(int(sys.argv[1]))
    lista = []
    for i in range(1, numero + 1):
        if numero % i == 0:
            lista.append(i)
            
print(f"{numero} tiene {len(lista)} divisores: {lista}")
