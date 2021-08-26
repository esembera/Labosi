#include <stdio.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <semaphore.h>

int Id; /* identifikacijski broj segmenta */
int *nesto;
sem_t *s1;
sem_t *s2_1;
sem_t *s2_2;
sem_t *s3;
sem_t *s4;
sem_t *s5;
sem_t *s6;

void ispis(int id){
    srand(id);
    int s = rand()%9+1;
    for(int i=0;i<s;i++){
        sleep(1);
        printf("Izvodim zadatak %d: %d/%d\n",id,i+1,s);
    }
}

void sem(int id){
     switch(id){ //ispitujem koji je proces dosao na red
        case 1: 
            ispis(1);
            sem_post(s1); //postavljam semafor 1
            exit(0);
        case 2:
            ispis(2);
            sem_post(s2_1); 
            sem_post(s2_2);
            exit(0);
        case 3:
            sem_wait(s2_1);
            ispis(3);
            sem_post(s3);
            exit(0);
        case 4:
            sem_wait(s2_2);
            ispis(4);
            sem_post(s4);
            exit(0);
        case 5:
            sem_wait(s1); //cekam semafor 1
            ispis(5);
            sem_post(s5);
            exit(0);            
        case 6:
            sem_wait(s3);
            ispis(6);
            sem_post(s6);
            exit(0);
        case 7:
            sem_wait(s5);
            sem_wait(s6);
            sem_wait(s4);
            ispis(7);
            exit(0);
    }  
}

int main(){

    Id = shmget(IPC_PRIVATE, sizeof(sem_t)*7, 0600); //zauzimam zajednicku memoriju
    
   if (Id == -1)
      exit(1);  /* greška - nema zajedničke memorije */

    //inicijaliziram semafore
    s1 = (sem_t *) shmat (Id, NULL, 0);
    shmctl(Id, IPC_RMID, NULL);
    sem_init(s1,1,0);

    s2_1 = (sem_t *) (s1 + 1);
    sem_init(s2_1,1,0);
    s2_2 = (sem_t *) (s2_1 + 1);
    sem_init(s2_2,1,0);
    s3 = (sem_t *) (s2_2 + 1);
    sem_init(s3,1,0);
    s4 = (sem_t *) (s3 + 1);
    sem_init(s4,1,0);
    s5 = (sem_t *) (s4 + 1);
    sem_init(s5,1,0);
    s6 = (sem_t *) (s5 + 1);
    sem_init(s6,1,0);
    
   /* pokretanje paralelnih procesa */
    
    for(int i=0;i<7;i++){
        if(fork()==0){
            sem(i+1);
        }
    }
    
    for(int i=0;i<7;i++){
        wait(NULL);
    }


    //brisem zajednicku memoriju
    shmdt((char *) s1);
    shmctl(Id,IPC_RMID,NULL);
    
    return 0;
}