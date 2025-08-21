#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main()
{

    int n;
    scanf("%i" , &n);
    getchar();
    char *entrada = (char*) malloc(10*sizeof(char));
    int Pilha[1000];
    int acoes = 0;
    int index = 0;
    while(acoes < n)
    {
        fgets(entrada, 10, stdin);
        if(strncmp(entrada, "PUSH", 4) == 0){
            Pilha[index] = atoi(&entrada[5]);
            index++;
        }
        else if(strncmp(entrada, "POP", 3) == 0){
            if(index > 0){
                index--;
            }
            else{
                printf("EMPTY");
            }
        }
        else if(strncmp(entrada, "MAX", 3) == 0){
            if(index > 0)
            {
                int maior = Pilha[0];
                for(int i = 1; i < index; i++)
                {
                    if(Pilha[i] > maior){maior = Pilha[i];}
                }
                printf("%i", maior);
            }
            else{
                printf("EMPTY");
            }
        }
        acoes++;
    }

    free(entrada);
    return 0;
}