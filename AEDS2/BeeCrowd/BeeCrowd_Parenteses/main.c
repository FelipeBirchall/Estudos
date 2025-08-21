#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool verificar(char entrada[])
{
    int n = strlen(entrada);
    bool parenteses = true;
    int cont = 0;
    for(int i = 0; i < n; i++)
    {
        if(entrada[i] == '(')
        {
            cont++;
        }
        else if(entrada[i] == ')')
        {
            cont--;
            if(cont < 0)
            {
                parenteses = false;
                i = n;
            }
        }
    }
    if(cont != 0){
        parenteses = false;
    }
    return parenteses;
}

int main()
{
    int n;
    scanf("%i" , &n);
    getchar();
    
    for(int i = 0; i < n; i++)
    {
        char entrada[1000];
        fgets(entrada, sizeof(entrada), stdin);
        if(verificar(entrada) == true){
            printf("Correct");
        }
        else{
            printf("Incorrect");
        }
    }
    return 0;
}