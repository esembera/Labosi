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
#include <semaphore.h>

int broj_procesa;
int *N;
sem_t *ulaz;
sem_t *k; //sluzi za kriticni odsjecak
sem_t *prolaz;

void proces(int id){
    int br;
    sem_wait(ulaz);
    sem_wait(k);

    printf("Proces %d. unesite broj\n",id);
    scanf("%d",&br);
    *N = *N + 1;
    if((*N) < broj_procesa){
        sem_post(k);
        sem_wait(prolaz);
        sem_wait(k);
    }
    *N = *N - 1;
    if((*N) > 0){
        sem_post(prolaz);
    }
    printf("Proces %d. uneseni broj je %d\n",id,br);
    sem_post(k);
}

int main(int argc, char *argv[]){
    
    broj_procesa = atoi(argv[1]);
    
    int Id = shmget(IPC_PRIVATE, sizeof(int)+sizeof(sem_t)*3, 0600); //radim zajednicku memoriju za semafore i zajednicku varijablu N
    
    N = (int *) shmat(Id,NULL,0);
    shmctl(Id, IPC_RMID, NULL);
    *N = 0;
    ulaz = (sem_t *) (N + 1);
    sem_init(ulaz,1,broj_procesa); //inicijaliziram semafor ulaz
    for(int i=0;i<broj_procesa;i++){ //postavljam semafor ulaz na vrijednost jednaku broju procesa
        sem_post(ulaz);
    }
    k = (sem_t *) (ulaz + 1);
    sem_init(k,1,1);
    prolaz = (sem_t *) (k + 1);
    sem_init(prolaz,1,broj_procesa-1);

    for(int i=0;i<broj_procesa-1;i++){ //postavljam semafor ulaz na vrijednost 0
        sem_wait(prolaz);
    }

    //stvaram paralelne procese

    for(int i=0;i<broj_procesa;i++){
        if(fork()==0){
            proces(i);
            exit(0);
        }
    }
 

    for(int i=0;i<broj_procesa;i++){
        wait(NULL);
    }


    //brisem zajednicku memoriju
    shmdt((char *) N);
    shmctl(Id,IPC_RMID,NULL);
    
    return 0;
}