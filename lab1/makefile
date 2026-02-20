KC = kotlinc
KFLAGS = -include-runtime
PROG = Main.jar
SRC = DegreesOfSeparation.kt ListaAdyacenciaGrafo.kt Grafo.kt
SC = runMain.sh

all:
	$(KC) $(SRC) $(KFLAGS) -d $(PROG)
	@echo "#!/usr/bin/bash\njava -jar $(PROG) \$$*" > $(SC)
	@chmod 755 $(SC)

.PHONY : clean 

clean : 
	rm -rf $(PROG)
	rm -rf $(SC)