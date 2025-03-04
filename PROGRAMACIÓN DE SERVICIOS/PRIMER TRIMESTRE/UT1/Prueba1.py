from multiprocessing import Process
import os

def hijo():
    print('\n>>>>>>>>>> Nuevo hijo creado con el pid %d a punto de finalizar<<<<<<<<' %os.getpid())
    os._exit(0)

def padre():
    salir = False
    while not salir:
        p = Process(target=hijo)
        p.start()
        print("\nNuevo hijo creado ", p.pid)
        p.join()
        reply = input("Pulsa 's' si quieres crear un nuevo proceso\n")
        if reply != 's':
            salir=True

if __name__ == '__main__':
    padre()
