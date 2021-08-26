#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <malloc.h>
#include <string.h>
#include <stdlib.h>

int broj_dretvi, broj_ponavljanja;
int a=0;

void *dretva(void *x){
    int *d = x;
    int i;
    for(i=0;i<broj_ponavljanja;i++){
        a++;
    }
    return NULL;
}


int main(int argc, char *argv[]){
    int *broj;
    pthread_t *tr;
    int i,j;

    broj_dretvi = atoi (argv[1]);
    broj_ponavljanja = atoi(argv[2]);

    broj = malloc (broj_dretvi * sizeof(int));
    tr = malloc (broj_dretvi * sizeof(pthread_t));

    /* pokretanje dretvi */
    for(i=0;i<broj_dretvi;i++){
        broj[i]=i;
        if(pthread_create(&tr[i],NULL,dretva,&broj[i])){
            fprintf(stderr,"Ne mogu stvoriti novu dretvu!\n");
            exit(1);
        }
    }

    for(j=0;j<broj_dretvi;j++){
        pthread_join(tr[j], NULL);
    }
    
    printf("A=%d\n",a);

    return 0;
}