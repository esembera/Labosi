#define _XOPEN_SOURCE
#define _XOPEN_SOURCE_EXTENDED

#include <stdio.h>
#include <signal.h>
#include <unistd.h>
 
#define N 6    /* broj razina proriteta */
 
int OZNAKA_CEKANJA[N];
int PRIORITET[N];
int TEKUCI_PRIORITET;
 
int sig[]={SIGUSR1, SIGUSR2, SIGTRAP, SIGSYS, SIGINT};
void zabrani_prekidanje(){
   int i;
   for(i=0; i<5; i++)
      sighold(sig[i]);
}
void dozvoli_prekidanje(){
   int i;
   for(i=0; i<5; i++)
      sigrelse(sig[i]);
}
 
void obrada_signala(int i){
   if(i==1){
        printf("\n- P - - - -");
        for(int i=1;i<6;i++){
            printf("\n- %d - - - -",i);
            sleep(1);
        }
        printf("\n- K - - - -");
   }else if(i==2){
         printf("\n- - P - - -");
        for(int i=1;i<6;i++){
            printf("\n- - %d - - -",i);
            sleep(1);
        }
        printf("\n- - K - - -");      
   }else if(i==3){
        printf("\n- - - P - -");
        for(int i=1;i<6;i++){
            printf("\n- - - %d - -",i);
            sleep(1);
        }
        printf("\n- - - K - -");
   }else if(i==4){
        printf("\n- - - - P -");
        for(int i=1;i<6;i++){
            printf("\n- - - - %d -",i);
            sleep(1);
        }
        printf("\n- - - - K -");
   }else{
        printf("\n- - - - - P");
        for(int i=1;i<6;i++){
            printf("\n- - - - - %d",i);
            sleep(1);
        }
        printf("\n- - - - - K");
   }
}

void prekidna_rutina(int sig){
   int n=-1;
   zabrani_prekidanje();
   switch(sig){
      case SIGUSR1: 
         n=1; 
         printf("\n- X - - - -");
         break;
      case SIGUSR2: 
         n=2; 
         printf("\n- - X - - -");
         break;
      case SIGTRAP:
        n=3;
        printf("\n- - - X - -");
        break;
      case SIGSYS:
        n=4;
        printf("\n- - - - X -");
        break;
      case SIGINT:
        n=5;
        printf("\n- - - - - X");
        break;
   }
   OZNAKA_CEKANJA[n]++;
   int x=0;
   do{
      x=0;
      for(int j=TEKUCI_PRIORITET+1;j<N;++j){
          if(OZNAKA_CEKANJA[j]!=0)
               x=j;
      }
      if(x>0){
        OZNAKA_CEKANJA[x]--;
        PRIORITET[x]=TEKUCI_PRIORITET;
        TEKUCI_PRIORITET=x;
        dozvoli_prekidanje();
        obrada_signala(x);
        zabrani_prekidanje();
        TEKUCI_PRIORITET = PRIORITET[x];
      }

   }while(x>0);
   dozvoli_prekidanje();
}
 
int main ( void )
{
   sigset (SIGUSR1, prekidna_rutina);
   sigset(SIGUSR2, prekidna_rutina);
   sigset(SIGTRAP, prekidna_rutina);
   sigset(SIGSYS, prekidna_rutina);
   sigset (SIGINT, prekidna_rutina);
 
   printf("Proces obrade prekida, PID=%d\n", getpid());
   printf("G 1 2 3 4 5");
   printf("\n-----------");
   int i=1;
   while(1){
        printf("\n%d - - - - -",i);
        sleep(1);
        i++;
   } 
   printf ("\nZavrsio osnovni program\n");
 
   return 0;
}