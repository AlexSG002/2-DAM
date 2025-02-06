import subprocess
result = subprocess.run(["python3","Factorial.py",str(9)], shell=True, capture_output=True, text=True, check=True)
print(result.stdout)