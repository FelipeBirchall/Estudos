#include <stdio.h>
#include <stdlib.h>

typedef struct Celula 
{
    int elemento;
    struct Celula *prox;
}Celula;

typedef struct 
{
    Celula *primeiro, *ultimo;
}Lista;


void iniciar(Lista *x)
{
    x->primeiro = (Celula*) malloc(sizeof(Celula));
    x->primeiro->prox = NULL;
    x->ultimo = x->primeiro;
}

void inserir(Lista *x, int val)
{
    Celula *nova = (Celula*) malloc(sizeof(Celula));
    nova->elemento = val;
    nova->prox = NULL;
    x->ultimo->prox = nova;
    x->ultimo = nova;
}

void mostrar(Lista *x)
{
    Celula *i;
    for(i = x->primeiro->prox; i != NULL; i= i->prox)
    {
        printf("%i " , i->elemento);
    }
}

void meiose(Lista *x)
{
    Celula *i = x->primeiro->prox;
    while(i != NULL)
    {
        int val = i->elemento/2;
        i->elemento = val;
        Celula *nova = (Celula*) malloc(sizeof(Celula));
        nova->elemento = val;
        nova->prox = i->prox;
        i->prox = nova;

        if (nova->prox == NULL) {
            x->ultimo = nova;
        }

        i = nova->prox;
    }
}



int main()
{

    Lista *lista;
    iniciar(&lista);
    inserir(&lista, 8);
    inserir(&lista, 5);
    inserir(&lista, 6);
    inserir(&lista, 1);
    printf("Antes da meiose: \n");
    mostrar(&lista);
    printf("\nApos a meiose: \n");
    meiose(&lista);
    mostrar(&lista);
    return 0;
}