#include <stdio.h>
#include <stdlib.h>

typedef struct Celula
{
    int elemento;
    struct Celula *dir , *esq, *sup, *inf;
}Celula;

typedef struct 
{
    Celula *inicio;
    int linha , coluna;
}Matriz;


void iniciar(Matriz *m , int lin, int col)
{
    m->inicio = (Celula*) malloc(sizeof(Celula));
    m->linha = lin;
    m->coluna = col;
    m->inicio->dir = m->inicio->esq = m->inicio->sup = m->inicio->inf = NULL;
    Celula *colunaAtual;

    Celula *linhaAtual = m->inicio;
    for(int i = 1; i < col; i++)
    {
        colunaAtual = (Celula*) malloc(sizeof(Celula));
        linhaAtual->dir = colunaAtual;
        colunaAtual->esq = linhaAtual;
        linhaAtual = colunaAtual;
    }

    Celula *linhaAcima = m->inicio;
    for(int i = 1; i < lin; i++)
    {
        Celula *novaLinha = (Celula*) malloc(sizeof(Celula));
        
        linhaAcima->inf = novaLinha;
        novaLinha->sup = linhaAcima;

        linhaAtual = novaLinha;
        colunaAtual = linhaAcima->dir;
        for(int j = 1; j < col; j++)
        {
            Celula *novaCelula = (Celula*) malloc(sizeof(Celula));
            linhaAtual->dir = novaCelula;
            novaCelula->esq = linhaAtual;

            novaCelula->sup = colunaAtual;
            colunaAtual->inf = novaCelula;

            linhaAtual = novaCelula;
            colunaAtual = colunaAtual->dir;
        }
        linhaAcima = linhaAcima->inf;
    }
}

void inserir(Matriz *m)
{
    Celula *linhaAtual = m->inicio;
    for(int i = 0; i < m->linha; i++)
    {
        Celula *celulaAtual = linhaAtual;
        for(int j = 0; j < m->coluna; j++)
        {
            int x;
            scanf("%i" , &x);
            celulaAtual->elemento = x;
            celulaAtual = celulaAtual->dir;
        }
        linhaAtual = linhaAtual->inf;
    }
}

int somaDiagonalPrincipal(Matriz *m)
{
    int soma = 0;
    for(Celula *i = m->inicio; i != NULL; i = i->inf)
    {
        if(i != m->inicio){i = i->dir;}
        soma += i->elemento;
    }
    return soma;
}

int somaDiagonalSecundaria(Matriz *m)
{
    int soma = 0;
    Celula *i; 
    for(i = m->inicio; i->dir != NULL; i = i->dir);
    for(Celula *j = i; j != NULL; j = j->inf){

        if(j != i){j = j->esq;}
        soma += j->elemento;
    }
    return soma;
}

void removerUltimaColuna(Matriz *m)
{
   Celula *topoColuna = m->inicio;
   while(topoColuna->dir != NULL)
   {
     topoColuna = topoColuna->dir;
   }
   Celula *atual = topoColuna;
   for(int i = 0; i < m->linha; i++)
   {
     if(atual->esq != NULL)
     {
        atual->esq->dir = NULL;
     }
     if(atual->sup != NULL)
     {
        atual->inf->sup = NULL;
     }
     if(atual->inf != NULL)
     {
        atual->sup->inf = NULL;
     }
     Celula *temp = atual->inf;

     free(atual); // Libera a célula

     atual = temp;
   }
   m->coluna--;
}

void mostrar(Matriz *m)
{
    Celula *linhaAtual = m->inicio;
    for(int i = 0; i < m->linha; i++)
    {
        Celula *celulaAtual = linhaAtual;
        for(int j = 0; j < m->coluna; j++)
        {
            printf("%i " , celulaAtual->elemento);
            celulaAtual = celulaAtual->dir;
        }
        printf("\n");
        linhaAtual = linhaAtual->inf;
    }
}



int main()
{
    Matriz *matriz = (Matriz*) malloc(sizeof(Matriz));
    iniciar(matriz ,3 ,3);
    inserir(matriz);
    mostrar(matriz);
    printf("Soma da diagonal principal: %d" , somaDiagonalPrincipal(matriz));
    removerUltimaColuna(matriz);
    mostrar(matriz);
    
    return 0;
}