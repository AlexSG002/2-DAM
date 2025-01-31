import sys
#if len(sys.argv) > 2:
num1 = int(sys.argv[1])
num2 = int(sys.argv[2])
auxiliar = num2
auxiliarMulti = 1
total = 1

if num1 >= num2 or num1 == 0 or num2 == 0:
    print("Los números introducidos no son válidos, recuerda que el primer número debe ser menor que el segundo")
else:
    for num1 in range(num2-2):
        auxiliar=auxiliar-1
        print(auxiliar)
        total*=auxiliar
    total*=num1*num2
    print(str(total))