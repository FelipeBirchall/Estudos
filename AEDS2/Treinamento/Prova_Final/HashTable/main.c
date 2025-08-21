#include <stdlib.h>
#include <stdio.h>


int hash(int valor , int M){
    return valor % M;
}

int main(){

    int N;
    scanf("%i" , &N);

    for(int casos = 0; casos < N; casos++){
        int M , C;
        scanf("%i %i " , &M , &C);
        int Hash[M][C];
        int valores[C];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < C; j++){
                Hash[i][j] = -1;
            }
        }

        for(int i = 0; i < C; i++){
            scanf("%i" , &valores[i]);
            int pos = hash(valores[i] , M);
            int ind = -1;
            for(int j = 0; j < C; j++){
                if(Hash[pos][j] == -1){
                    ind = j;
                    j = C;
                }
            }
            Hash[pos][ind] = valores[i];
        }

        for(int i = 0; i < M; i++){
            printf("%i ->" , i);
            for(int j = 0; j < C; j++){
                if(Hash[i][j] != -1){
                    printf(" %i ->", Hash[i][j]);
                }
                else{
                    printf(" |");
                    j = C;
                }
            }
            printf("\n");
        }
        printf("\n");

    }

    return 0;
}


