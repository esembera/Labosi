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
#include <pthread.h>
#include <time.h>

int ULAZ=0, IZLAZ=0, UKUPNO=0;
int M[5];
sem_t *PISI;
sem_t *PUN;
sem_t *PRAZAN;
int broj_slucBr;
int broj_dretava;

void *proizvodac(void *x){
    int *d = x; //uzimam id dretve
    int i = 0;
    for(i=0;i<broj_slucBr;i++){
        sem_wait(PUN);
        sem_wait(PISI);
        srand((*d)*broj_slucBr+i);
        M[ULAZ] = rand()%1000;
        printf("Proizvodac %d salje \"%d\"\n",*d+1,M[ULAZ]);
        ULAZ=(ULAZ+1) % 5;
        sem_post(PISI);
        sem_post(PRAZAN);
        
    }
    printf("Proizvodac %d zavrsio sa slanjem\n", *d+1);
}

void *potrosac(void *x){
    for(int i=0;i<broj_dretava*broj_slucBr;i++){
        sem_wait(PRAZAN); //cekanje na semafor prazan -- sem_wait ceka semafor
        printf("Potrosac prima %d\n",M[IZLAZ]);
        UKUPNO += M[IZLAZ];
        IZLAZ= (IZLAZ + 1) % 5; 
        sem_post(PUN); //postavljanje semafora pun -- sem_post postavlja semafor
    }
    printf("Potrosac - zbroj primljenih brojeva = %d\n",UKUPNO);
}

int main(int argc, char *argv[]){
    pthread_t *tr;

    //uzimanje argumenata
    broj_dretava = atoi(argv[1]);
    int broj[broj_dretava];
    broj_slucBr = atoi(argv[2]);



    int Id = shmget(IPC_PRIVATE, sizeof(sem_t)*3, 0600); //radim zajednicku memoriju za semafore
    PISI = (sem_t *) shmat (Id,NULL,0);
    shmctl(Id, IPC_RMID, NULL);
    sem_init(PISI,1,1); //inicijaliziram semafor PISI
    PUN = (sem_t *) (PISI + 1);
    sem_init(PUN,1,5);
    PRAZAN = (sem_t *) (PUN + 1);
    sem_init(PRAZAN,1,1);

    tr = malloc (broj_dretava * sizeof(pthread_t)); //alociram memoriju za opisnik dretve
    //stvaram dretve
    for(int i=0;i<broj_dretava;i++){
        broj[i]=i;
        if(pthread_create(&tr[i],NULL,proizvodac,&broj[i])){
            fprintf(stderr,"Ne mogu stvoriti novu dretvu!\n");
            exit(1);
        }
    }
    pthread_t *tp;
    pthread_create(tp,NULL,potrosac,NULL);

    //cekanje na ostale dretve
    for(int j=0;j<broj_dretava;j++){
        pthread_join(tr[j], NULL);
    }
    pthread_join(*tp,NULL);
    return 0;
}