#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include "quotes.h"

typedef enum { false, true } bool;
typedef enum { level_0, level_1, level_2, level_3 } level;
level DEBUG_LEVEL = 0;
bool ARG_OK = 0;
// #define SIZE 100000
// char dummy[SIZE] = {'a'};

int main(int argc, char *argv[]) {

	FILE *filereadptr, *filewriteptr;
	unsigned char *buffer, mask, bufferbak;
	char *filepath;
	long filereadlen, filepathindex;
	unsigned long long seed, index;
	struct timeval tv;

	for(int i=1; i<argc; i++){
		if(!strcmp(argv[i],"-d")){
			DEBUG_LEVEL = 1;
		}
		if(!strcmp(argv[i],"-dd")){
			DEBUG_LEVEL = 2;
		}
		if(!strcmp(argv[i],"-ddd")){
			DEBUG_LEVEL = 3;
		}
		if(!strcmp(argv[i],"-p")){
			if(argc>i+1) ARG_OK = 1;
			filepathindex = i+1;
		}
	}

	if(!ARG_OK){
		printf("Missing Path argument\n");
		fflush(stdout);
		return -1;
	}

	filepath = (char*) malloc(strlen(argv[filepathindex])*sizeof(char));
	strcpy(filepath, argv[filepathindex]);
	if(DEBUG_LEVEL>0){ printf("File Path: %s \n", filepath);fflush(stdout);}

	gettimeofday(&tv, NULL);
	seed = (unsigned long long)(tv.tv_sec) * 1000 + (unsigned long long)(tv.tv_usec) / 1000;
	if(DEBUG_LEVEL>1){ printf("Seed: %llu \n", seed);fflush(stdout);}

	srand(seed);	// Initialization, should only be called once.

	printf("%s\n", quotes[rand() % NUMOFQUOTES]);
	fflush(stdout);

	filereadptr = fopen(filepath, "rb");	// Open the file in binary mode
	fseek(filereadptr, 0, SEEK_END);	// Jump to the end of the file
	filereadlen = ftell(filereadptr);	// Get the current byte offset in the file
	rewind(filereadptr);	// Jump back to the beginning of the file

	buffer = (unsigned char *)malloc((filereadlen+1)*sizeof(char)); // Enough memory for file + \0
	fread(buffer, filereadlen, 1, filereadptr); // Read in the entire file
	fclose(filereadptr); // Close the file

	if(DEBUG_LEVEL>2){ 
		for(int i=0; i<filereadlen-16; i=i+16){
			printf("%02x", buffer[i]);fflush(stdout);
			printf("%02x ", buffer[i+1]);fflush(stdout);
			printf("%02x", buffer[i+2]);fflush(stdout);
			printf("%02x ", buffer[i+3]);fflush(stdout);
			printf("%02x", buffer[i+4]);fflush(stdout);
			printf("%02x ", buffer[i+5]);fflush(stdout);
			printf("%02x", buffer[i+6]);fflush(stdout);
			printf("%02x ", buffer[i+7]);fflush(stdout);
			printf("%02x", buffer[i+8]);fflush(stdout);
			printf("%02x ", buffer[i+9]);fflush(stdout);
			printf("%02x", buffer[i+10]);fflush(stdout);
			printf("%02x ", buffer[i+11]);fflush(stdout);
			printf("%02x", buffer[i+12]);fflush(stdout);
			printf("%02x ", buffer[i+13]);fflush(stdout);
			printf("%02x", buffer[i+14]);fflush(stdout);
			printf("%02x\n", buffer[i+15]);fflush(stdout);
		}
	}

	if(DEBUG_LEVEL>0){ printf("Size: %ld \n",filereadlen);fflush(stdout);}

	for(int i=0; i<filereadlen-2; i=i+2){
		printf("%02x", buffer[i]);
		printf("%02x ", buffer[i+1]);
	}
	printf("\n");
	fflush(stdout);
	
	do{
		if(DEBUG_LEVEL>0){ printf("RAND_MAX %d\n", RAND_MAX);fflush(stdout);}

		index = rand() % filereadlen;	// Returns a pseudo-random integer between 0 and filereadlen.

		if(DEBUG_LEVEL>0){ printf("Index: %lld \n", index);fflush(stdout);}
		bufferbak = buffer[index];

		mask = ~(1<<(rand() % 8));
		buffer[index] = buffer[index] & mask;
		
		if(DEBUG_LEVEL>1){ printf("Mask: %u \n", mask);fflush(stdout);}
		if(DEBUG_LEVEL>0){ printf("Buffer before: %02x\n", bufferbak);fflush(stdout);}
		if(DEBUG_LEVEL>0){ printf("Buffer after: %02x\n", buffer[index]);fflush(stdout);}
	} while(buffer[index]==bufferbak);

	printf("%lld\n", index);
	fflush(stdout);

	filewriteptr = fopen( filepath, "wb" );
	if(DEBUG_LEVEL>1){ printf("File Write pointer: %p \n", filewriteptr);fflush(stdout);}
	fwrite( buffer, 1, filereadlen, filewriteptr );
	fclose(filewriteptr);
	
	printf("%d\n", 1);
	fflush(stdout);

	return 1;
}
