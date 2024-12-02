import keyword
print(keyword.kwlist)

print(keyword.iskeyword('variable'))
#comentarios

#help('modules')

edad=24
nombre="Maria"
print("La edad de",nombre, "es",edad, ".", end="")
print(" Me dijo \"voy a ir\"")

semanas = 4
print ('En', semanas, 'semanas hay',7*semanas, 'dias')

print(f'¡Hola {nombre}!')

#input

#nombre=input("¿Como te llamas?")
print('Hola', nombre)

print(2*3/5)
print(2/5*3)

print(divmod(13,4))

n=2/5*3
print(round(n,2))

import math

print(math.floor(4.92))
print(math.ceil(4.2))
print(abs(-2))
print(max(2,4,8))
print(min(2,4,8))

print(sorted((3,5,2,8,9,1,4,7,32,6)))
print("bof")

A=True
print(not A)
print("ormiga")
edad=17
print(edad>7)

ormiga = False
if(not ormiga):
    print("awebo")

if(edad>18 and edad<65):
    print("Es mayor de edad")
elif(edad>=65):
    print("e un awelo")
else: print('Es ormiga')

def hola():
    print("Hola mundo")

hola()

def division(x,y):
    q=x//y
    r=x%y;
    return(q,r)

division(4,2)
print("El cociente es ", division.q, " y el resto es", division.r)

print()