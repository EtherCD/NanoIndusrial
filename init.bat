git init
git add .
git commit "Initall Commit"

for %%C in (".\patches\*.patch") do git apply %%C 