#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_LINE_LENGTH 1024
#define MAX_FIELDS 12
#define MAX_CAST 100
#define MAX_LISTED 10

typedef struct {
    char* id;
    char* type;
    char* title;
    char* director;
    char** cast;
    int cast_count;
    char* country;
    char* date;
    int year;
    char* rating;
    char* duration;
    char** listed;
    int listed_count;
} Show;

// Função para dividir uma linha CSV em campos
char** splitCSV(const char* linha, int* qtd_campos) {
    char** campos_csv = malloc(MAX_FIELDS * sizeof(char*));
    *qtd_campos = 0;
    bool dentro_aspas = false;
    char campo_atual[256];
    int idx_campo = 0;

    for (int idx = 0; linha[idx] != '\0'; idx++) {
        char caractere = linha[idx];
        if (caractere == '"') {
            dentro_aspas = !dentro_aspas;
        } else if (caractere == ',' && !dentro_aspas) {
            campo_atual[idx_campo] = '\0';
            campos_csv[*qtd_campos] = strdup(campo_atual);
            (*qtd_campos)++;
            idx_campo = 0;
        } else {
            campo_atual[idx_campo++] = caractere;
        }
    }
    campo_atual[idx_campo] = '\0';
    if (idx_campo > 0) {
        campos_csv[*qtd_campos] = strdup(campo_atual);
        (*qtd_campos)++;
    }
    return campos_csv;
}

// Libera memória dos campos do CSV
void freeFields(char** campos_csv, int qtd) {
    for (int idx = 0; idx < qtd; idx++) {
        free(campos_csv[idx]);
    }
    free(campos_csv);
}

// Função de comparação para qsort
int compareStrings(const void* a, const void* b) {
    return strcmp(*(const char**)a, *(const char**)b);
}

// Lê uma linha CSV e preenche o struct Show
void leitura(Show* show, const char* linha_csv) {
    int qtd_campos;
    char** campos = splitCSV(linha_csv, &qtd_campos);

    if (qtd_campos >= 11) {
        // Preenche campos simples
        show->id = strdup(campos[0][0] == '\0' ? "NaN" : campos[0]);
        show->type = strdup(campos[1][0] == '\0' ? "NaN" : campos[1]);
        show->title = strdup(campos[2][0] == '\0' ? "NaN" : campos[2]);

        // Diretor
        char* diretor_temp = campos[3];
        if (diretor_temp[0] == '"') {
            diretor_temp++;
            diretor_temp[strlen(diretor_temp) - 1] = '\0';
        }
        show->director = strdup(diretor_temp[0] == '\0' ? "NaN" : diretor_temp);

        // Elenco (cast)
        char* elenco_temp = strdup(campos[4]);
        if (elenco_temp[0] == '"') {
            elenco_temp++;
            elenco_temp[strlen(elenco_temp) - 1] = '\0';
        }
        if (elenco_temp[0] == '\0') {
            show->cast = malloc(sizeof(char*));
            show->cast[0] = strdup("NaN");
            show->cast_count = 1;
        } else {
            show->cast = malloc(MAX_CAST * sizeof(char*));
            show->cast_count = 0;
            char* inicio = elenco_temp;
            char* fim;
            while ((fim = strstr(inicio, ", ")) != NULL && show->cast_count < MAX_CAST) {
                *fim = '\0';
                show->cast[show->cast_count++] = strdup(inicio);
                inicio = fim + 2;
            }
            if (*inicio != '\0' && show->cast_count < MAX_CAST) {
                show->cast[show->cast_count++] = strdup(inicio);
            }
            // Ordena elenco
            qsort(show->cast, show->cast_count, sizeof(char*), compareStrings);
        }
        free(elenco_temp);

        // País
        char* pais_temp = campos[5];
        if (pais_temp[0] == '"') {
            pais_temp++;
            pais_temp[strlen(pais_temp) - 1] = '\0';
        }
        show->country = strdup(pais_temp[0] == '\0' ? "NaN" : pais_temp);

        // Data
        char* data_temp = campos[6];
        if (data_temp[0] == '"') {
            data_temp++;
            data_temp[strlen(data_temp) - 1] = '\0';
        }
        show->date = strdup(data_temp[0] == '\0' ? "NaN" : data_temp);

        // Ano
        show->year = campos[7][0] == '\0' ? 0 : atoi(campos[7]);
        // Classificação
        show->rating = strdup(campos[8][0] == '\0' ? "NaN" : campos[8]);
        // Duração
        show->duration = strdup(campos[9][0] == '\0' ? "NaN" : campos[9]);

        // Gêneros (listed)
        char* generos_temp = strdup(campos[10]);
        if (generos_temp[0] == '"') {
            generos_temp++;
            generos_temp[strlen(generos_temp) - 1] = '\0';
        }
        if (generos_temp[0] == '\0') {
            show->listed = malloc(sizeof(char*));
            show->listed[0] = strdup("NaN");
            show->listed_count = 1;
        } else {
            show->listed = malloc(MAX_LISTED * sizeof(char*));
            show->listed_count = 0;
            char* inicio = generos_temp;
            char* fim;
            while ((fim = strstr(inicio, ", ")) != NULL && show->listed_count < MAX_LISTED) {
                *fim = '\0';
                show->listed[show->listed_count++] = strdup(inicio);
                inicio = fim + 2;
            }
            if (*inicio != '\0' && show->listed_count < MAX_LISTED) {
                show->listed[show->listed_count++] = strdup(inicio);
            }
        }
        free(generos_temp);
    }

    freeFields(campos, qtd_campos);
}

// Clona um struct Show
Show cloneShow(const Show* origem) {
    Show copia;

    // Copia campos simples
    copia.id = strdup(origem->id);
    copia.type = strdup(origem->type);
    copia.title = strdup(origem->title);
    copia.director = strdup(origem->director);
    copia.country = strdup(origem->country);
    copia.date = strdup(origem->date);
    copia.rating = strdup(origem->rating);
    copia.duration = strdup(origem->duration);

    // Copia campo inteiro
    copia.year = origem->year;

    // Copia array cast
    copia.cast_count = origem->cast_count;
    copia.cast = malloc(copia.cast_count * sizeof(char*));
    for (int idx = 0; idx < copia.cast_count; idx++) {
        copia.cast[idx] = strdup(origem->cast[idx]);
    }

    // Copia array listed
    copia.listed_count = origem->listed_count;
    copia.listed = malloc(copia.listed_count * sizeof(char*));
    for (int idx = 0; idx < copia.listed_count; idx++) {
        copia.listed[idx] = strdup(origem->listed[idx]);
    }

    return copia;
}

// Imprime um Show formatado
void imprimir(const Show* show) {
    printf("=> %s ## %s ## %s ## %s ## [", show->id, show->title, show->type, show->director);
    for (int idx = 0; idx < show->cast_count; idx++) {
        printf("%s", show->cast[idx]);
        if (idx < show->cast_count - 1) printf(", ");
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", show->country, show->date,
           show->year, show->rating, show->duration);
    for (int idx = 0; idx < show->listed_count; idx++) {
        printf("%s", show->listed[idx]);
        if (idx < show->listed_count - 1) printf(", ");
    }
    printf("] ##\n");
}

// Libera memória de um Show
void freeShow(Show* show) {
    free(show->id);
    free(show->type);
    free(show->title);
    free(show->director);
    for (int idx = 0; idx < show->cast_count; idx++) {
        free(show->cast[idx]);
    }
    free(show->cast);
    free(show->country);
    free(show->date);
    free(show->rating);
    free(show->duration);
    for (int idx = 0; idx < show->listed_count; idx++) {
        free(show->listed[idx]);
    }
    free(show->listed);
}

int main() {
    char caminho_arquivo[] = "/tmp/disneyplus.csv";
    Show lista[1369];
    int idx_lista = 0;

    char entrada_id[10];
    scanf("%s", entrada_id);
    while (strcmp(entrada_id, "FIM") != 0) {
        FILE* arquivo = fopen(caminho_arquivo, "r");
        if (arquivo == NULL) {
            printf("Erro ao abrir o arquivo.\n");
            return 1;
        }

        char linha[MAX_LINE_LENGTH];
        fgets(linha, MAX_LINE_LENGTH, arquivo); // Pula cabeçalho

        // Procura o ID na tabela
        while (fgets(linha, MAX_LINE_LENGTH, arquivo) != NULL) {
            int qtd_campos;
            char** campos = splitCSV(linha, &qtd_campos);
            if (qtd_campos > 0 && strcmp(campos[0], entrada_id) == 0) {
                Show show;
                leitura(&show, linha);
                lista[idx_lista++] = show;
                imprimir(&show);
                freeFields(campos, qtd_campos);
                break;
            }
            freeFields(campos, qtd_campos);
        }
        fclose(arquivo);

        scanf("%s", entrada_id);
    }

    return 0;
}
