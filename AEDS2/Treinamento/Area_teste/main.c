#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int elemento;
    struct Celula *ant , *prox;
}Celula;

typedef struct{
    Celula *primeiro, *ultimo;
}Lista;

void inicializar(Lista *l)
{
    
}

int main()
{
    Lista *lista =(Lista*) malloc(sizeof(Lista));
    
    return 0;
}