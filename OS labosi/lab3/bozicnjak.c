#include <stdio.h>
#include <semaphore.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
int Id;
int *br_sobova;
int *br_patuljaka;
sem_t *djedbozicnjak;
sem_t *k;
sem_t *sobovi;
sem_t *konzultacije;

void patuljak(){
    sem_wait(k);
    (*br_patuljaka)++;
    printf("broj patuljaka: %d\n",*br_patuljaka);
    if(*br_patuljaka==3){
        sem_post(djedbozicnjak);
        printf("patuljak budi djedicu\n");
    }
    sem_post(k);
    sem_wait(konzultacije);
}

void sob(){
    sem_wait(k);
    (*br_sobova)++;
    printf("broj sobova: %d\n", *br_sobova);
    if(*br_sobova==10){
        sem_post(djedbozicnjak);
        printf("sob budi djedicu\n");
    }
    sem_post(k);
    sem_wait(sobovi);
}

void djedica(){
    while(1){
        printf("Djedica spava\n");
        sem_wait(djedbozicnjak);
        sem_wait(k);
        printf("Djedica se probudio\n");
        if(*br_sobova==10 && *br_patuljaka > 0){
            sem_post(k);
            sleep(2);
            printf("Raznio poklone\n");
            sem_wait(k);
            for(int i=0;i<10;i++){
                sem_post(sobovi);
            }
            *br_sobova=0;
        }
        if(*br_sobova==10){
            sem_post(k);
            sleep(2);
            printf("Nahranio sobove\n");
            sem_wait(k);
        }
        while(*br_patuljaka>=3){
            sem_post(k);
            sleep(2);
            printf("Rijesio problem patuljaka\n");
            sem_wait(k);

            for(int i=0;i<3;i++){
                sem_post(konzultacije);
            }

            *br_patuljaka = *br_patuljaka - 3;
        }
        sem_post(k);
    }
}

int main(){

    Id = shmget(IPC_PRIVATE, sizeof(int)*2+sizeof(sem_t)*4, 0600);

    br_patuljaka = (int *) shmat (Id, NULL, 0);
    shmctl(Id, IPC_RMID, NULL);
    *br_patuljaka = 0;
 
    br_sobova = (int *) (br_patuljaka + 1);
    *br_sobova = 0;

    djedbozicnjak = (sem_t *) (br_sobova + 1);
    sem_init(djedbozicnjak,1,1);

    k = (sem_t *) (djedbozicnjak + 1);
    sem_init(k,1,1);
    sem_post(k);

    sobovi = (sem_t *) (k + 1);
    sem_init(sobovi,1,10);

    konzultacije = (sem_t *) (sobovi + 1);
    sem_init(konzultacije,1,3);

    if(fork()==0){
        djedica();
    }else{
        while(1){
            printf("cekam \n");
            srand(time(NULL));
            int s=rand()%3+1;
            sleep(s);
            if(rand()%2 && *br_sobova < 10){
                if(fork()==0){
                    printf("Stvaram soba\n");
                    sob();
                    break;
                }
            }
            if(rand()%2){
                if(fork()==0){
                    printf("Stvaram patuljka\n");
                    patuljak();
                    break;
                }
            }
        }
    }
}