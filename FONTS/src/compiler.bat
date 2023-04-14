@echo off
echo Compilant projecte Mastermind...
javac drivers/Driver.java
echo Compilacio finalitzada.
if "%1"=="jar" (
    echo Creant arxiu JAR...
    jar cmvf ./drivers/Driver.mf ../../EXE/Driver.jar -C ../../EXE/out/ .
    echo Arxiu JAR creat.
)
if "%1"=="run" (
    java drivers.Driver
)
pause