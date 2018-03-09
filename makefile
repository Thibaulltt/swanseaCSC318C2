default:
	@clear; tmux clear-history
	@echo "Compiling ..."
	@javac *.java --add-modules java.se.ee
	@echo "Compilation complete. 0 errors (?)"
	@echo
	@java coursework2
