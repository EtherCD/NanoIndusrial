git init
git add .
git commit -m "Initial Commit"

for %%C in (".\patches\*.patch") do git apply %%C 
