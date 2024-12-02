numero = int(input("Introduce un número: "))

primos = []
for i in range(1, numero + 1):
    es_primo = True
    if i < 2:
        es_primo = False
    for j in range(2, i):
        if i % j == 0:
            es_primo = False
            break
    if es_primo:
        primos.append(i)

print(f"Los primos hasta el {numero} son: {primos}")
