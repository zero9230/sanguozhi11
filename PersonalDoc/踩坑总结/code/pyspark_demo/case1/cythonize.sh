rm -rf ./**/*.c ./**/*.so
GLOBIGNORE="./main.py"
cythonize -X always_allow_keywords=True -3 -i ./*/**/*.py
unset GLOBIGNORE