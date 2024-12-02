import subprocess

result = subprocess.run(["python","ActividadSuma.py","4","7"],capture_output=True, text=True, check=True)

print(result.stdout)