CLASSES = domini/classes
CONTROLADORES = domini/controllers
VISTA = presentacio/views

ifeq ($(OS), Windows_NT)
CLPTH = "bin;../../lib/forms_rt-7.0.3.jar"
else
CLPTH = "bin:../../lib/forms_rt-7.0.3.jar"
endif

default: class

class:
	javac -d bin -encoding UTF-8 -cp $(CLPTH) -sourcepath . presentacio/controllers/CtrlPresentacio.java $(CLASSES)/*.java $(CONTROLADORES)/*.java $(VISTA)/*.java

run:
	java -cp $(CLPTH) presentacio.controllers.CtrlPresentacio

clean:
	rmdir /s bin
	del /f domini\classes\*.class
	del /f domini\classes\exceptions\*.class
	del /f domini\controllers\*.class