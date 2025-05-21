#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>



int numDiamantes(char entrada[])
{
    int n = strlen(entrada);
    int diamantes = 0;
    int esquerda = 0;
    for(int i = 0; i < n; i++)
    {
        if(entrada[i] == '<')
        {
            esquerda++;
        }
        else if(entrada[i] == '>')
        {
            if(esquerda > 0){
                esquerda--;
                diamantes++;
            }
        }
    }

    return diamantes;
}

int main()
{
    int n;
    scanf("%i" , &n);
    getchar();
    char entrada[1000];
    
    for(int i = 0; i < n; i++)
    {
        fgets(entrada, sizeof(entrada), stdin);
        printf("%i" , numDiamantes(entrada));
    }

    return 0;
}