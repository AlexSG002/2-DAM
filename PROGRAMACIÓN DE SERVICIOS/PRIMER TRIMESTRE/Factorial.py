import sys
if len(sys.argv) > 1:
    factorial = int(sys.argv[1])
    num = factorial
    cont = factorial
    for i in range(1, num + 1):
        factorial *=i
    print(str(num)+" factorial es: "+str(factorial))