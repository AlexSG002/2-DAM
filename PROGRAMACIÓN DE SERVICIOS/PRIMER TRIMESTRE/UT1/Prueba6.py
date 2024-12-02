import subprocess
import time

def crearProceso():
    try:
        SW_SHOWMAXIMIZED = 3
        info = subprocess.STARTUPINFO()
        info.dwFlags = subprocess.STARTF_USESHOWWINDOW
        info.wShowWindow = SW_SHOWMAXIMIZED
        proc = subprocess.Popen('notepad.exe', startupinfo=info)
        return proc
    except subprocess.CalledProcessError as e:
        print(e.output)
p = crearProceso()
print("El PID de este proceso es: "+str(p.pid))
time.sleep(5)