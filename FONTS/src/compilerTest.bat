@echo off
echo Compilant projecte Mastermind...
javac -cp ../../lib/forms_rt-7.0.3.jar presentacio/controllers/CtrlPresentacio.java
echo Compilacio finalitzada.
if "%1"=="jar" (
    echo Creant arxiu JAR...
    jar cmvf presentacio/controllers/CtrlPresentacio.mf ../../EXE/CtrlPresentacio.jar -C ../../EXE/out/ .
    echo Arxiu JAR creat.
)
if "%1"=="run" (
    java presentacio.controllers.CtrlPresentacio
)
pause