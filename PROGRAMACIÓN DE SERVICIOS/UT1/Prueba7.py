import psutil
import os
import subprocess
import sys

def ProcesoActual():
    return psutil.Process(os.getpid())

def esWindows():
    try:
        sys.getwindowsversion()
    except AttributeError:
        return(False)
    else:
        return(True)
    
print(ProcesoActual().name()) #nombre
print(ProcesoActual().cwd()) #Path de ejecución
print(ProcesoActual().nice()) #Prioridad antes del cambio
if esWindows():
    subprocess.check_output("wmic process where processid=\""+str(os.getpid())+"\"CALL  setpriority \"below normal\"")
else:
    os.nice(1) #Prioridad después del cambio

print(ProcesoActual().nice())
a=input()