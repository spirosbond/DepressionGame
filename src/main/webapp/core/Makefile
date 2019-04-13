all: areyoudepressed.c quotes.h win lin
win: areyoudepressed.c quotes.h
	x86_64-w64-mingw32-gcc -std=c99 areyoudepressed.c -o areyoudepressed.exe
lin: areyoudepressed.c quotes.h
	gcc -std=c99 areyoudepressed.c -o areyoudepressed.o
clean:
	-rm *.o *.exe