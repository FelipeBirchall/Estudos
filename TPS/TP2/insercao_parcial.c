#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define TAM_MAX_LINHA 1024
#define TAM_MAX_SHOWS 10000
#define TAM_MAX_LISTA 50

// Estrutura para data
typedef struct Data {
    char mes[20];
    int dia;
    int ano;
} Data;

// Estrutura principal para shows
typedef struct Show {
    char* show_id;
    char* type;
    char* title;
    char* director;
    char** cast;       
    int castSize;      
    char* country;
    Data date_added;   
    int release_year;
    char* rating;
    char* duration;
    char** listed_in; 
    int listedInSize;
    char* description;
} Show;

Show* lista[TAM_MAX_SHOWS];
int totalShows = 0;

// Limpa espaços desnecessários
char* limparEspaco(char* str) {
    if (!str) return strdup("NaN");
    while (isspace(*str)) str++;
    char* end = str + strlen(str) - 1;
    while (end > str && isspace(*end)) end--;
    *(end + 1) = '\0';
    return strdup(str);
}

// Retira aspas duplas
char* tirarAspas(char* str) {
    if (!str) return NULL;
    char* result = malloc(strlen(str) + 1);
    int j = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            result[j++] = str[i];
        }
    }
    result[j] = '\0';
    return result;
}

// Processa o campo removendo aspas e espaços
char* tirarAspasEEspaco(char* field) {
    if (!field || strlen(field) == 0) return strdup("NaN");
    char* noQuotes = tirarAspas(field);
    char* result = limparEspaco(noQuotes);
    free(noQuotes);
    return result;
}

char** tratarLista(char* str, int* tamanho) {
    char** items = malloc(TAM_MAX_LISTA * sizeof(char*));
    *tamanho = 0;

    if (!str || strlen(str) == 0) {
        items[(*tamanho)++] = strdup("NaN");
        return items;
    }

    char* clear = tirarAspas(str);
    char* token = strtok(clear, ",");
    while (token != NULL && *tamanho < TAM_MAX_LISTA) {
        char* item = tirarAspasEEspaco(token);
        items[(*tamanho)++] = item;
        token = strtok(NULL, ",");
    }

    free(clear);

    //Bubble Sort para ordenar
    for (int i = 0; i < *tamanho - 1; i++) {
        for (int j = 0; j < *tamanho - i - 1; j++) {
            if (strcasecmp(items[j], items[j + 1]) > 0) {
                char* temp = items[j];
                items[j] = items[j + 1];
                items[j + 1] = temp;
            }
        }
    }

    if (*tamanho == 0) {
        items[(*tamanho)++] = strdup("NaN");
    }
    return items;
}

int dividirCampos(char* linha, char* campos[], int maxCampos) {
    int i = 0;
    char* ptr = linha;
    while (*ptr && i < maxCampos) {
        if (*ptr == '"') {
            ptr++;
            char* start = ptr;
            while (*ptr && !(*ptr == '"' && (*(ptr + 1) == ',' || *(ptr + 1) == '\0'))) {
                ptr++;
            }
            int len = ptr - start;
            campos[i] = malloc(len + 1);
            strncpy(campos[i], start, len);
            campos[i][len] = '\0';
            if (*ptr == '"') ptr++;
            if (*ptr == ',') ptr++;
        } else {
            char* start = ptr;
            while (*ptr && *ptr != ',') ptr++;
            int len = ptr - start;
            campos[i] = malloc(len + 1);
            strncpy(campos[i], start, len);
            campos[i][len] = '\0';
            if (*ptr == ',') ptr++;
        }
        i++;
    }
    return i;
}

// Leitura do CSV
void readCSV() {
    FILE* file = fopen("/tmp/disneyplus.csv", "r");   
    if (file == NULL) {
        return;
    }

    char linha[TAM_MAX_LINHA];
    fgets(linha, TAM_MAX_LINHA, file);

    while (fgets(linha, TAM_MAX_LINHA, file)) {
        linha[strcspn(linha, "\n")] = 0;

        char* campos[15];
        int fieldCount = dividirCampos(linha, campos, 15);

        Show* shows = malloc(sizeof(Show));
        if (!shows) {
            exit(1);
        }

        shows->show_id = tirarAspasEEspaco(campos[0]);
        shows->type = tirarAspasEEspaco(campos[1]);
        shows->title = tirarAspasEEspaco(campos[2]);
        shows->director = tirarAspasEEspaco(campos[3]);
        shows->cast = tratarLista(campos[4], &shows->castSize);
        shows->country = tirarAspasEEspaco(campos[5]);

        // Trata campo de data
        char* dateStr = tirarAspasEEspaco(campos[6]);
        if (strcmp(dateStr, "NaN") != 0) {
            char* parte = strtok(dateStr, " ");
            strcpy(shows->date_added.mes, parte ? parte : "NaN");
            parte = strtok(NULL, ",");
            shows->date_added.dia = parte ? atoi(parte) : -1;
            parte = strtok(NULL, "");
            shows->date_added.ano = parte ? atoi(parte) : -1;
        } else {
            strcpy(shows->date_added.mes, "March");
            shows->date_added.dia = 1;
            shows->date_added.ano = 1900;
        }
        free(dateStr);

        shows->release_year = (fieldCount > 7 && strlen(campos[7]) > 0) ? atoi(campos[7]) : -1;
        shows->rating = tirarAspasEEspaco(campos[8]);
        shows->duration = tirarAspasEEspaco(campos[9]);
        shows->listed_in = tratarLista(campos[10], &shows->listedInSize);
        shows->description = tirarAspasEEspaco(campos[11]);

        for (int i = 0; i < fieldCount; i++) free(campos[i]);
        lista[totalShows++] = shows;
    }

    fclose(file);
}

void printShow(Show* showAtual) {
    printf("=> %s ## %s ## %s ## %s ## [", showAtual->show_id, showAtual->title, showAtual->type, showAtual->director);
    for (int i = 0; i < showAtual->castSize; i++) {
        printf("%s%s", showAtual->cast[i], (i < showAtual->castSize - 1) ? ", " : "");
    }
    printf("] ## %s ## %s %d, %d ## %d ## %s ## %s ## [", showAtual->country, showAtual->date_added.mes, showAtual->date_added.dia,
           showAtual->date_added.ano, showAtual->release_year, showAtual->rating, showAtual->duration);
    for (int i = 0; i < showAtual->listedInSize; i++) {
        printf("%s%s", showAtual->listed_in[i], (i < showAtual->listedInSize - 1) ? ", " : "");
    }
    printf("] ##\n");
}

bool isFim(char* str) {
    return strcasecmp(str, "FIM") == 0;
}


void insercaoParcial(Show* array[], int n, int k) {
    if (k > n) k = n;

    // Inicializa os primeiros k elementos
    for (int i = 1; i < k; i++) {
        Show* key = array[i];
        int j = i - 1;
        while (j >= 0) {
            if (strcasecmp(array[j]->type, key->type) > 0 ||
                (strcasecmp(array[j]->type, key->type) == 0 && 
                 strcasecmp(array[j]->title, key->title) > 0)) {
                array[j + 1] = array[j];
                j--;
            } else {
                break;
            }
        }
        array[j + 1] = key;
    }

    // Processa os elementos restantes
    for (int i = k; i < n; i++) {
        if (strcasecmp(array[i]->type, array[k - 1]->type) < 0 ||
            (strcasecmp(array[i]->type, array[k - 1]->type) == 0 && 
             strcasecmp(array[i]->title, array[k - 1]->title) < 0)) {
            Show* key = array[i];
            int j = k - 2;
            array[k - 1] = key;
            while (j >= 0) {
                if (strcasecmp(array[j]->type, key->type) > 0 ||
                    (strcasecmp(array[j]->type, key->type) == 0 && 
                     strcasecmp(array[j]->title, key->title) > 0)) {
                    array[j + 1] = array[j];
                    
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }
}

int main() {
    readCSV(); 

    char entrada[50];
    fgets(entrada, sizeof(entrada), stdin);
    entrada[strcspn(entrada, "\n")] = 0;

    Show* resultados[TAM_MAX_SHOWS];
    int tamanhoResultados = 0;

    while (!isFim(entrada)) {
        for (int i = 0; i < totalShows; i++) {
            if (strcasecmp(lista[i]->show_id, entrada) == 0) {
                resultados[tamanhoResultados++] = lista[i];
                break;
            }
        }
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
    }

    insercaoParcial(resultados, tamanhoResultados, 10);

    int elementosParaImprimir = (tamanhoResultados < 10) ? tamanhoResultados : 10;
    for (int i = 0; i < elementosParaImprimir; i++) {
        printShow(resultados[i]);
    }


    return 0;
}