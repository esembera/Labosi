#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <malloc.h>
#include <string.h>
#include <stdlib.h>

int velSpremnika;


int main(int argc, char *argv[]){
    char c;
    velSpremnika = atoi (argv[1]);
    char Spremnik[velSpremnika+1];
    memset(Spremnik,0,sizeof(Spremnik));
    memset(Spremnik,'-', sizeof(Spremnik)-1);
    int brojevi[velSpremnika];
    for(int i=0;i<velSpremnika;i++){
        brojevi[i]=(i+1)%10;
        Spremnik[i]='-';
    }
    int br=48;

    for(int i=0;i<velSpremnika;i++){
        printf("%d",brojevi[i]);
    }
    printf("\n");
    for(int i=0;i<velSpremnika;i++){
        printf("%c",Spremnik[i]);
    }
    printf("\n");
    srand(time(NULL));
    int error=0;
    while(1){
        int prazMj=0;
        int dovBr=0;
        int prazMje=0;
        scanf(" %c",&c);
        if(c=='z'){              
            int r = rand()%7+1;
            printf("Novi zahtjev %c za %d spremnickih mjesta\n",br,r);
            for(int i=0;i<velSpremnika;++i){
                if(Spremnik[i]=='-'){
                    int j=i;
                    while(Spremnik[j]=='-'){
                        dovBr++;
                        j++;
                    }
                    if(dovBr>=r){
                        break;
                    }else{
                        i=i+dovBr;
                        prazMj=i+1;
                    }
                    if(Spremnik[j]=='\0'){
                        printf("U memoriji nema prostora za zahtjev %c!\n",br);
                        error=1;
                        break;
                    }
                }else{
                    prazMj++;
                }
                if(i==velSpremnika-1){
                    printf("U memoriji nema prostora za zahtjev %c!\n",br);
                    error=1;
                }                
            }
            if(error!=1){
                for(int i=prazMj;i<prazMj+r;i++){
                    Spremnik[i]=br;
                }
            }
            br++;
        }else if(c=='o'){
            char oslobodiBr;
            printf("Koji zahtjev treba osloboditi?\n");
            scanf(" %c",&oslobodiBr);
            for(int i=0;i<velSpremnika;i++){
                if(Spremnik[i]==oslobodiBr){
                    Spremnik[i]='-';
                }
            }
        }else if(c=='d'){
            for(int i=0;i<velSpremnika;){
                if(Spremnik[i]=='-'){
                    int imaJos=0;
                    for(int j=i+1;j<velSpremnika;j++){
                        Spremnik[j-1]=Spremnik[j];
                        if(Spremnik[j]!='-'){
                            imaJos=1;
                        }
                    }
                    Spremnik[velSpremnika-1]='-';
                    if(!(imaJos)){
                        break;
                    }              
                }else{
                    i++;
                }
            }
        }
        if(error){
            error=0;
            continue;
        }
        for(int i=0;i<velSpremnika;i++){
            printf("%d",brojevi[i]);
        }
        printf("\n");
        for(int i=0;i<velSpremnika;i++){
            printf("%c",Spremnik[i]);
        }
        printf("\n");    
    }
    return 0;
}