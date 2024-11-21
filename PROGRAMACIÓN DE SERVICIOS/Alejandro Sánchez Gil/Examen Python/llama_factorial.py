import subprocess
result = subprocess.run(["Python","Factorial.py"], capture_output=True, text=True, check=True)
print(result.stdout)