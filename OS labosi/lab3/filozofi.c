#include <pthread.h>
#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <unistd.h>

pthread_mutex_t m;
pthread_cond_t red[5];
int stapici[5]={1,1,1,1,1};
char stanja[5]={'O','O','O','O','O'};

void *filozof (void *p){
    int *d = p;
    int lstapic=*d;
    int dstapic=((*d)+1)%5;
    int lfilozof=((*d)+4)%5;
    int dfilozof=dstapic;

    while(1){
        sleep(2);
        pthread_mutex_lock(&m);
        while(!(stapici[lstapic] && stapici[dstapic])){
            stanja[(*d)]='o';
            pthread_cond_wait(&red[(*d)], &m);
        }
        stapici[lstapic]=0;
        stapici[dstapic]=0;
        pthread_mutex_unlock(&m);
        stanja[*d]='X';    
        sleep(2);
        stanja[*d]='O';
        pthread_mutex_lock(&m);
        stapici[lstapic]=1;
        stapici[dstapic]=1;
        pthread_cond_signal(&red[lfilozof]);
        pthread_cond_signal(&red[dfilozof]);
        pthread_mutex_unlock(&m);
    }
}


int main ()
{
	pthread_t thr_id;
    int *broj;
	pthread_mutex_init (&m, NULL);
    for(int i=0;i<5;i++){
        pthread_cond_init (&red[i], NULL);
    }


    for(int i = 0;i < 5; i++){
        broj[i]=i;
        pthread_create(&thr_id, NULL, filozof, &broj[i]);
    }


    while(1){
        sleep(1);
        for(int i=0;i<5;i++){
            printf("%c ",stanja[i]);
        }
        printf("\n");

    }



	pthread_mutex_destroy (&m);
    for(int i=0;i<5;i++){
        pthread_cond_destroy (&red[i]);
    }

	return 0;
}