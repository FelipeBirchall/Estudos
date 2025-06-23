#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(){

    int N;
    scanf("%i" , &N);
    for(int caso = 0; caso < N; caso++){
        int L;
        scanf("%i" , &L);

        char strings[100][51];
        for(int i = 0; i < L; i++){
            scanf("%s" , strings[i]);
        }
        
        int hash = 0;

        for(int i = 0; i < L; i++){
            int tamanho = strlen(strings[i]);
            for(int j = 0; j < tamanho; j++){
                int pos_alfabetica = strings[i][j] - 'A';
                hash += pos_alfabetica + i + j;
            }
        }
        printf("%i\n" , hash);
    }

    return 0;
}