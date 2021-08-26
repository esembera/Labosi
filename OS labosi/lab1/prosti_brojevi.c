#define _XOPEN_SOURCE
#define _XOPEN_SOURCE_EXTENDED


#include<stdio.h>
#include<signal.h>
#include<unistd.h>
#include<sys/time.h>
#include<math.h>
#include<stdlib.h>

int pauza=0;
int broj=1000000001;
int zadnji=1000000001;

void periodicki_ispis(){
    printf("zadnji prosti broj = %u\n", zadnji);
}

void postavi_pauzu(int i){
    pauza=1-pauza;
}

void prekini(int i){
    printf("zadnji prosti broj = %u\n", zadnji);
    exit(1);
}

int prost(unsigned long n){
    unsigned long i, max;
    
    if((n & 1)==0)
        return 0;
    max = sqrt(n);
    for (i=3;i<=max;i+=2)
        if((n%i)==0)
            return 0;
    return 1;
}

int main(){
    struct itimerval t;

    sigset(SIGALRM, periodicki_ispis);
    sigset(SIGTERM, prekini);
    sigset(SIGINT, postavi_pauzu);

    t.it_value.tv_sec=0;
    t.it_value.tv_usec=500000;

    t.it_interval.tv_sec=0;
    t.it_interval.tv_usec=500000;

    setitimer(ITIMER_REAL, &t, NULL);


    while(1){
        if(prost(broj)==1)
            zadnji=broj;
        broj++;
        while(pauza==1)
            pause();
       }


    return 0;
}