default:
	@clear
	@echo "Compiling ..."
	@javac *.java --add-modules java.se.ee --add-modules java.xml.bind
	@echo "Compilation complete. 0 errors (?)"
	@echo
	@echo "WARNING ! If you are running a machine with a JDK version 9,"
	@echo "the program will throw a classnotFound exception. This is because,"
	@echo "Oracle, in their great wisdom, deprecated the javax.xml.bind package, "
	@echo "but didn't provide an alternative. While it compiles, and runs on Wiktor's"
	@echo "machine, it doesn't run on mine (on JDK9.0). If you can, try to fin a JDK8"
	@echo "equipped machine. For now, the DatatypeConverter class is nowhere to be found."
	@echo
	@java coursework2 --add-modules javax.xml.bind
