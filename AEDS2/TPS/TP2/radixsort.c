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

int contComparacoes = 0;
int contMovimentacoes = 0;

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

// Função auxiliar para obter o dígito na posição 'pos' do release_year
int getDigit(int number, int pos) {
    while (pos > 0) {
        number /= 10;
        pos--;
    }
    return number % 10;
}

// Função para encontrar o valor máximo de release_year
int getMaxYear(Show* array[], int n) {
    int max = array[0]->release_year;
    for (int i = 1; i < n; i++) {
        if (array[i]->release_year > max) {
            max = array[i]->release_year;
        }
    }
    return max;
}

/* 
 * RadixSort para ordenar por release_year.
 * Usa CountingSort por dígito, do menos ao mais significativo.
 * Mantém estabilidade e ordena por title em caso de empate.
 * Complexidade: O(d*n), onde d é o número de dígitos e n o tamanho do array.
 */
void radixSort(Show* array[], int n) {
    // Encontra o maior ano para determinar o número de dígitos
    int maxYear = getMaxYear(array, n);
    
    // Array temporário para CountingSort
    Show* output[n];
    int count[10];  // Para dígitos 0-9

    // Processa cada dígito
    for (int pos = 0; maxYear > 0; maxYear /= 10, pos++) {
        // Inicializa array de contagem
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        // Conta ocorrências de cada dígito
        for (int i = 0; i < n; i++) {
            int digit = getDigit(array[i]->release_year, pos);
            count[digit]++;
            contComparacoes++;
        }

        // Ajusta contagem para indicar posições finais
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Constrói array de saída, processando do fim para o início
        // para manter estabilidade
        for (int i = n - 1; i >= 0; i--) {
            int digit = getDigit(array[i]->release_year, pos);
            output[count[digit] - 1] = array[i];
            count[digit]--;
            contMovimentacoes++;
        }

        // Copia array de saída para array original
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            contMovimentacoes++;
        }

        // Após ordenar por todos os dígitos do release_year,
        // verifica empates e ordena por title
        if (maxYear / 10 == 0) {
            // Verifica sequências de elementos com mesmo release_year
            for (int i = 0; i < n - 1; i++) {
                int start = i;
                while (i < n - 1 && array[i]->release_year == array[i + 1]->release_year) {
                    i++;
                }
                // Se encontrou sequência com mesmo release_year
                if (i > start) {
                    // Ordena por title usando inserção (estável)
                    for (int j = start + 1; j <= i; j++) {
                        Show* key = array[j];
                        int k = j - 1;
                        while (k >= start && strcasecmp(array[k]->title, key->title) > 0) {
                            array[k + 1] = array[k];
                            k--;
                            contMovimentacoes++;
                            contComparacoes++;
                        }
                        array[k + 1] = key;
                        contMovimentacoes++;
                    }
                }
            }
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

    clock_t inicio = clock();

    radixSort(resultados, tamanhoResultados);

    clock_t fim = clock();

    for (int i = 0; i < tamanhoResultados; i++) {
        printShow(resultados[i]);
    }

    double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC;
 
    FILE* log = fopen("matricula_radixsort.txt", "w");
    if (log) {
        fprintf(log, "844448\t%d\t%d\t%lf\n", contComparacoes, contMovimentacoes, tempoExecucao);
        fclose(log);
    }

    return 0;
}