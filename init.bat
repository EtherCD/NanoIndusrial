git init
git add .
git commit -m "Inital Commit"

for %%C in (".\patches\*.patch") do git apply %%C 
