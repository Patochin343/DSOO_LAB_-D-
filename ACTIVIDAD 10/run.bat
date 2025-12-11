@echo off
if not exist bin mkdir bin
dir /s /B src\*.java > sources.txt
javac -d bin -cp "lib/*;src" @sources.txt
if %errorlevel% neq 0 (
    echo Compilation failed
    exit /b %errorlevel%
)
echo Compilation successful. Running...
java -cp "bin;lib/*" com.banco.main.Main
