#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
    int n;
    int index = 0;
    int acoes = 0;
    scanf("%i" , &n);
    getchar();

    char *entrada = (char*) malloc(30*sizeof(char));
    int *pilha = (int*)malloc(n*sizeof(int));

    while(acoes < n)
    {
        fgets(entrada , 30 , stdin);
        if(strncmp(entrada, "PUSH", 4) == 0)
        {
            pilha[index] = atoi(&entrada[5]);
            index++;
        }
        else if(strncmp(entrada, "POP" , 3) == 0)
        {
            if(index > 0)
            {
                index--;
            }
            else{
                printf("EMPTY\n");
            }
        }
        else if(strncmp(entrada, "MIN" , 3) == 0)
        {
            if(index > 0)
            {
                int menor = pilha[0];
                for(int i = 1; i < index; i++)
                {
                    if(menor > pilha[i]){menor = pilha[i];}
                }
                printf("%i\n" , menor);
            }
            else{
                printf("EMPTY");
            }
        }
        acoes++;
    }
    return 0;
}



