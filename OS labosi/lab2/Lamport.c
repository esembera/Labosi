#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <malloc.h>
#include <string.h>
#include <stdlib.h>
#include <stdatomic.h>

int broj_dretvi, broj_ponavljanja;
int a=0;
atomic_int *BROJ, *ULAZ;

int maks(atomic_int* a){
    int najv=a[0];
    for(int i=0; i<broj_dretvi;i++){
        if(najv<a[i]){
            najv=a[i];
        }
    }
    return najv;
}

void udi(int i){
    ULAZ[i]=1;
    BROJ[i]=maks(BROJ)+1;
    ULAZ[i]=0;

    for(int j=0; j<broj_dretvi;j++){
        while(ULAZ[j]!=0){
            ;
        }
        while(BROJ[j]!=0 && (BROJ[j]<BROJ[i] || (BROJ[j] == BROJ[i] && j < i))){
            ;
        }
    }
}

void izadi(int i){
    BROJ[i] = 0;
}

void *dretva(void *x){
    int *d = x;
    int i;
    for(i=0;i<broj_ponavljanja;i++){
        udi(*d);
        a++;
        izadi(*d);
    }
    return NULL;
}

int main(int argc, char *argv[]){
    int *broj;
    pthread_t *tr;
    int i,j;

    broj_dretvi = atoi (argv[1]);
    broj_ponavljanja = atoi(argv[2]);

    BROJ = (atomic_int *)malloc (broj_dretvi * sizeof(atomic_int));
    ULAZ = (atomic_int *)malloc (broj_dretvi * sizeof(atomic_int));

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