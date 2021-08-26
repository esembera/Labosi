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
#include <pthread.h>
#include <time.h>

pthread_mutexattr_t *ma;
pthread_mutex_t *m;
pthread_cond_t *red;
int *prodaje1, *prodaje2;


void proces(int id){
    while(1){
        pthread_mutex_lock(m);

        while(*prodaje1 == id || *prodaje2 == id){
            pthread_cond_wait(red + id,m);
        }
        if(*prodaje1 == 0 && *prodaje2 == 1){
                printf("Nakupac s tipkovnicama uzeo monitor i racunalo \n");
        }else if(*prodaje1 == 1 && *prodaje2 == 2){
                printf("Nakupac s monitorima uzeo racunalo i tipkovnicu \n");
        }else if(*prodaje1 == 0 && *prodaje2 == 2){
                printf("Nakupac s racunalima uzeo monitor i tipkovnicu \n");
        }else if(*prodaje1 == 1 && *prodaje2 == 0){
                printf("Nakupac s tipkovnicama uzeo racunalo i monitor \n");
        }else if(*prodaje1 == 2 && *prodaje2 == 1){
                printf("Nakupac s monitorima uzeo tipkovnicu i racunalo \n");
        }else if(*prodaje1 == 2 && *prodaje2 == 0){
                printf("Nakupac s racunalima uzeo tipkovnicu i monitor \n");
        }

        *prodaje1 = -1;
        *prodaje2 = -1;
        for(int i = 0; i<4; i++){
            pthread_cond_signal(red + i);
        }
        pthread_mutex_unlock(m);
        sleep(1);
        
    }
}



void main(){

    //radim zajednicku memoriju za monitor i ostatak variajabli

    int Id = shmget(IPC_PRIVATE,sizeof(pthread_cond_t)*4 + sizeof(pthread_mutex_t) + sizeof(int)*2 + sizeof(pthread_mutexattr_t), 0600);     
    red = (pthread_cond_t *) shmat (Id,NULL,0);
    shmctl(Id, IPC_RMID, NULL);
    m = (pthread_mutex_t *) (red + 4);
    pthread_mutex_init (m, NULL);
    prodaje1 = (int *) (m + 1);
    *prodaje1 = -1;
    prodaje2 = (int *) (prodaje1 + 1);
    *prodaje2 = -1;
    ma = (pthread_mutexattr_t *) (prodaje2 + 1);
    pthread_mutexattr_init(ma);

    pthread_mutexattr_setpshared(ma,1);
    
    for(int i=0;i<5;i++){
        pthread_cond_init (&red[i], NULL);
    }


    for(int i=0;i<3;i++){
        if(fork()==0){
            proces(i);
            exit(0);
        }
    }
    srand(time(NULL));
    while(1){
        pthread_mutex_lock(m);
        while(!(*prodaje1 == -1 || *prodaje2 == -1)){
            pthread_cond_wait(red + 3, m);
        }
        int s = rand()%3;
        int l = rand()%3;
        while(l == s){
            l=rand()%3;
        }
        *prodaje1 = s;
        *prodaje2 = l;
        
        if(*prodaje1 == 0 && *prodaje2 == 1){
            printf("Veletrgovac stavio monitor i racunalo \n");
        }else if(*prodaje1 == 1 && *prodaje2 == 2){
            printf("Veletrgovac stavio racunalo i tipkovnicu \n");
        }else if(*prodaje1 == 0 && *prodaje2 == 2){
            printf("Veletrgovac stavio monitor i tipkovnicu \n");
        }else if(*prodaje1 == 1 && *prodaje2 == 0){
            printf("Veletrgovac stavio racunalo i monitor \n");
        }else if(*prodaje1 == 2 && *prodaje2 == 1){
            printf("Veletrgovac stavio tipkovnicu i racunalo \n");
        }else if(*prodaje1 == 2 && *prodaje2 == 0){
            printf("Veletrgovac stavio tipkovnicu i monitor \n");
        }
        for(int i = 0; i<3; i++){
            pthread_cond_signal(red + i);
        }
        pthread_mutex_unlock(m);

        sleep(1);
    }

    //brisem zajednicku memoriju
    shmdt((char *) red);
    shmctl(Id,IPC_RMID,NULL);


    return;
}