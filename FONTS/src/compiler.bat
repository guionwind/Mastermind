@echo off
echo Compilant projecte Mastermind...
javac controllers/CtrlPresentacio.java
echo Compilacio finalitzada.
if "%1"=="jar" (
    echo Creant arxiu JAR...
    jar cmvf ./controllers/CtrlPresentacio.mf ../../EXE/CtrlPresentacio.jar -C ../../EXE/out/ .
    echo Arxiu JAR creat.
)
if "%1"=="run" (
    java controllers.CtrlPresentacio
)
pause