#define _XOPEN_SOURCE
#define _XOPEN_SOURCE_EXTENDED

#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

int pid=0;

void prekidna_rutina(int sig){
   kill(pid, SIGKILL);
   exit(0);
}

int main(int argc, char *argv[]){
   pid=atoi(argv[1]);
   sigset(SIGINT, prekidna_rutina);

   while(1){
       srand(time(NULL));
       int s=rand()%3+3;
       sleep(s);
       int r=rand()%4+1;
       if(r==1){
            kill(pid,SIGUSR1);
       }else if(r==2){
           kill(pid, SIGUSR2);
       }else if(r=3){
           kill(pid,SIGTRAP);
       }else{
           kill(pid, SIGSYS);
       }
   }
   return 0;
}