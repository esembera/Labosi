#define _XOPEN_SOURCE
#define _XOPEN_SOURCE_EXTENDED

#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>

int Id; /* identifikacijski broj segmenta */
int *ZajednickaVarijabla;
int broj_procesa, broj_ponavljanja;

void proces(int j){
    for(int i=0;i<j;i++){
        *ZajednickaVarijabla = *ZajednickaVarijabla + 1;
    }
}

void brisi(int i){
    shmdt((char *) ZajednickaVarijabla);
    shmctl(Id,IPC_RMID,NULL);
}

int main(int argc, char *argv[]){
    
    broj_procesa = atoi(argv[1]);
    broj_ponavljanja = atoi(argv[2]);


    sigset(SIGINT, brisi);

    Id = shmget(IPC_PRIVATE, sizeof(int), 0600);
 
   if (Id == -1)
      exit(1);  /* greška - nema zajedničke memorije */
 
    ZajednickaVarijabla = (int *) shmat(Id, NULL, 0);
    *ZajednickaVarijabla = 0;

   /* pokretanje paralelnih procesa */

    for(int i=0; i<broj_procesa;i++){
        if (fork() == 0) {
            proces(broj_ponavljanja);
            exit(0);
        }
    }

    for(int i=0;i<broj_procesa;i++){
        wait(NULL);
    }

    printf("A=%d\n",*ZajednickaVarijabla);
 
    brisi(0);
    
    return 0;
}