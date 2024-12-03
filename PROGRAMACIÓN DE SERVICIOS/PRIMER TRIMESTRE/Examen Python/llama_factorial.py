import subprocess
result = subprocess.run(["Python","Factorial.py","5"], capture_output=True, text=True, check=True)
print(result.stdout)