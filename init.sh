git init;
git add .;
git commit -m "Inital Commit";

for file in `find ./patches -type f -name "*.patch"`
do
   git apply $file;
done
