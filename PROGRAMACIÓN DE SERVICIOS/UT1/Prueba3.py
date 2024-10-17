import subprocess

result = subprocess.run(["python","programa.py"], capture_output=True, text=True)

print(result.stdout)