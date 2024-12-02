import subprocess

result = subprocess.run(["python","divisores.py","12"],capture_output=True, text=True, check=True)

print(result.stdout)