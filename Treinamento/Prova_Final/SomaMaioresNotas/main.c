#include <stdlib.h> 
#include <stdio.h>


int main(){

    int N;
    int K;
   while (scanf("%d %d", &N, &K) != EOF) {
        if (N <= 0 || K <= 0 || N > 100000 || K > N) continue;
        
        int notas[N];
        for(int i = 0; i < N; i++){
            scanf("%i" , &notas[i]);
        }
        for(int i = 0; i < N -1 ; i++){
            int maior = i;
            for(int j = i+1; j < N; j++){
                if(notas[j] > notas[maior]){maior = j;}
            }
            int tmp = notas[maior];
            notas[maior] = notas[i];
            notas[i] = tmp;
        }
        int soma = 0;
        for(int i = 0; i < K; i++){
            soma+= notas[i];
        }
        printf("%i\n" , soma);
    }
        

    return 0;
}