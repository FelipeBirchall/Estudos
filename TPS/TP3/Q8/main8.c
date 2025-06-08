#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define MAX_LINE 1024
#define MAX_SHOWS 10000
#define MAX_ITEMS 50

typedef struct Date {
    char mes[20];
    int dia;
    int ano;
} Date;

typedef struct Show {
    char* show_id;
    char* type;
    char* title;
    char* director;
    char** cast;       
    int castSize;      
    char* country;
    Date date_added;
    int release_year;
    char* rating;
    char* duration;
    char** listed_in; 
    int listedInSize;
    char* description;
} Show;

typedef struct CelulaDupla {
    Show* elemento;            
    struct CelulaDupla* prox;   
    struct CelulaDupla* ant;    
} CelulaDupla;

Show* catalogo[MAX_SHOWS];
int totalShows = 0;
int comparacoes = 0;
int movimentacoes = 0;

CelulaDupla* criarCelulaDupla(Show* elemento) {
    CelulaDupla* nova = malloc(sizeof(CelulaDupla));
    if (!nova) {
        fprintf(stderr, "Erro na alocação de memória!\n");
        exit(1);
    }
    nova->elemento = elemento;
    nova->prox = nova->ant = NULL;
    return nova;
}

CelulaDupla* primeiro;
CelulaDupla* ultimo;

void inicializarLista() {
    primeiro = criarCelulaDupla(NULL);
    ultimo = primeiro;
}

void adicionarInicio(Show* s) {
    CelulaDupla* nova = criarCelulaDupla(s);
    nova->prox = primeiro->prox;
    nova->ant = primeiro;
    primeiro->prox = nova;
    
    if (primeiro == ultimo) {
        ultimo = nova;
    } else if (nova->prox) {
        nova->prox->ant = nova;
    }
}

void adicionarFim(Show* s) {
    CelulaDupla* nova = criarCelulaDupla(s);
    nova->ant = ultimo;
    ultimo->prox = nova;
    ultimo = nova;
}

Show* extrairInicio() {
    if (primeiro == ultimo) {
        fprintf(stderr, "Lista vazia!\n");
        exit(1);
    }
    
    CelulaDupla* tmp = primeiro->prox;
    Show* elemento = tmp->elemento;
    primeiro->prox = tmp->prox;
    
    if (tmp == ultimo) {
        ultimo = primeiro;
    } else {
        tmp->prox->ant = primeiro;
    }
    
    free(tmp);
    return elemento;
}

Show* extrairFim() {
    if (primeiro == ultimo) {
        fprintf(stderr, "Lista vazia!\n");
        exit(1);
    }
    
    Show* elemento = ultimo->elemento;
    CelulaDupla* tmp = ultimo;
    ultimo = ultimo->ant;
    ultimo->prox = NULL;
    free(tmp);
    return elemento;
}

int obterTamanho() {
    int contador = 0;
    CelulaDupla* atual = primeiro->prox;
    while (atual) {
        contador++;
        atual = atual->prox;
    }
    return contador;
}

void inserirNaPosicao(Show* s, int pos) {
    int tam = obterTamanho();
    if (pos < 0 || pos > tam) {
        fprintf(stderr, "Posição inválida: %d (tamanho: %d)\n", pos, tam);
        exit(1);
    }
    
    if (pos == 0) {
        adicionarInicio(s);
        return;
    }
    if (pos == tam) {
        adicionarFim(s);
        return;
    }
    
    CelulaDupla* nova = criarCelulaDupla(s);
    CelulaDupla* atual = primeiro->prox;
    for (int i = 0; i < pos - 1; i++) {
        atual = atual->prox;
    }
    
    nova->prox = atual->prox;
    nova->ant = atual;
    atual->prox->ant = nova;
    atual->prox = nova;
}

Show* removerDaPosicao(int pos) {
    int tam = obterTamanho();
    if (primeiro == ultimo) {
        fprintf(stderr, "Lista vazia!\n");
        exit(1);
    }
    if (pos < 0 || pos >= tam) {
        fprintf(stderr, "Posição inválida: %d (tamanho: %d)\n", pos, tam);
        exit(1);
    }
    
    if (pos == 0) return extrairInicio();
    if (pos == tam - 1) return extrairFim();
    
    CelulaDupla* atual = primeiro->prox;
    for (int i = 0; i < pos; i++) {
        atual = atual->prox;
    }
    
    Show* elemento = atual->elemento;
    atual->ant->prox = atual->prox;
    atual->prox->ant = atual->ant;
    free(atual);
    return elemento;
}

void exibirLista() {
    CelulaDupla* atual = primeiro->prox;
    while (atual) {
        Show* s = atual->elemento;
        printf("%s ## %s ## %s ## %s ## [", s->show_id, s->title, s->type, s->director);
        for (int k = 0; k < s->castSize; k++) {
            printf("%s%s", s->cast[k], k < s->castSize - 1 ? ", " : "");
        }
        printf("] ## %s ## %s %d, %d ## %d ## %s ## %s ## [", 
               s->country, s->date_added.mes, s->date_added.dia,
               s->date_added.ano, s->release_year, s->rating, s->duration);
        for (int k = 0; k < s->listedInSize; k++) {
            printf("%s%s", s->listed_in[k], k < s->listedInSize - 1 ? ", " : "");
        }
        printf("] ##\n");
        atual = atual->prox;
    }
}

Show* localizarShow(char* show_id) {
    for (int i = 0; i < totalShows; i++) {
        if (strcasecmp(catalogo[i]->show_id, show_id) == 0) {
            return catalogo[i];
        }
    }
    return NULL;
}

char* limparString(char* str) {
    if (!str) return strdup("NaN");
    while (isspace(*str)) str++;
    char* end = str + strlen(str) - 1;
    while (end > str && isspace(*end)) end--;
    *(end + 1) = '\0';
    return strdup(str);
}

char* removeAspas(char* str) {
    if (!str) return NULL;
    char* resultado = malloc(strlen(str) + 1);
    int j = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            resultado[j++] = str[i];
        }
    }
    resultado[j] = '\0';
    return resultado;
}

char* removeAspasEspacoVazio(char* field) {
    if (!field || strlen(field) == 0) return strdup("NaN");
    char* noQuotes = removeAspas(field);
    char* resultado = limparString(noQuotes);
    free(noQuotes);
    return resultado;
}

char** trataLista(char* str, int* size) {
    char** items = malloc(MAX_ITEMS * sizeof(char*));
    *size = 0;
    if (!str || strlen(str) == 0) {
        items[(*size)++] = strdup("NaN");
        return items;
    }
    char* clear = removeAspas(str);
    char* token = strtok(clear, ",");
    while (token != NULL && *size < MAX_ITEMS) {
        char* item = removeAspasEspacoVazio(token);
        items[(*size)++] = item;
        token = strtok(NULL, ",");
    }
    free(clear);
    for (int i = 0; i < *size - 1; i++) {
        for (int j = 0; j < *size - i - 1; j++) {
            if (strcasecmp(items[j], items[j + 1]) > 0) {
                char* temp = items[j];
                items[j] = items[j + 1];
                items[j + 1] = temp;
            }
        }
    }
    if (*size == 0) {
        items[(*size)++] = strdup("NaN");
    }
    return items;
}

int separarCampos(char* linha, char* campos[], int maxCampos) {
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

bool isFim(char* str) {
    return strcasecmp(str, "FIM") == 0;
}

void preencherCatalogo() {
    FILE* file = fopen("/tmp/disneyplus.csv", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo!\n");
        return;
    }

    char linha[MAX_LINE];
    fgets(linha, MAX_LINE, file);

    while (fgets(linha, MAX_LINE, file)) {
        linha[strcspn(linha, "\n")] = 0;
        char* campos[15];
        int fieldCount = separarCampos(linha, campos, 15);

        Show* novoShow = malloc(sizeof(Show));
        novoShow->show_id = removeAspasEspacoVazio(campos[0]);
        novoShow->type = removeAspasEspacoVazio(campos[1]);
        novoShow->title = removeAspasEspacoVazio(campos[2]);
        novoShow->director = removeAspasEspacoVazio(campos[3]);
        novoShow->cast = trataLista(campos[4], &novoShow->castSize);
        novoShow->country = removeAspasEspacoVazio(campos[5]);

        char* dateStr = removeAspasEspacoVazio(campos[6]);
        if (strcmp(dateStr, "NaN") != 0) {
            char* parte = strtok(dateStr, " ");
            strcpy(novoShow->date_added.mes, parte ? parte : "NaN");
            parte = strtok(NULL, ",");
            novoShow->date_added.dia = parte ? atoi(parte) : -1;
            parte = strtok(NULL, "");
            novoShow->date_added.ano = parte ? atoi(parte) : -1;
        } else {
            strcpy(novoShow->date_added.mes, "March");
            novoShow->date_added.dia = 1;
            novoShow->date_added.ano = 1900;
        }
        free(dateStr);

        novoShow->release_year = (fieldCount > 7 && strlen(campos[7]) > 0) ? atoi(campos[7]) : -1;
        novoShow->rating = removeAspasEspacoVazio(campos[8]);
        novoShow->duration = removeAspasEspacoVazio(campos[9]);
        novoShow->listed_in = trataLista(campos[10], &novoShow->listedInSize);
        novoShow->description = removeAspasEspacoVazio(campos[11]);

        for (int i = 0; i < fieldCount; i++) free(campos[i]);
        catalogo[totalShows++] = novoShow;
    }

    fclose(file);
}

const char* mesOrdem[] = {"January", "February", "March", "April", "May", "June", 
                          "July", "August", "September", "October", "November", "December"};

int compareShows(Show* a, Show* b) {
    int mesA = -1, mesB = -1;
    for (int i = 0; i < 12; i++) {
        if (strcasecmp(a->date_added.mes, mesOrdem[i]) == 0) mesA = i;
        if (strcasecmp(b->date_added.mes, mesOrdem[i]) == 0) mesB = i;
    }
    if (a->date_added.ano != b->date_added.ano)
        return a->date_added.ano - b->date_added.ano;
    if (mesA != mesB)
        return mesA - mesB;
    if (a->date_added.dia != b->date_added.dia)
        return a->date_added.dia - b->date_added.dia;
    return strcasecmp(a->title, b->title);
}

void ordenarQuicksort(Show* array[], int inicio, int fim) {
    if (inicio >= fim) return;

    Show* pivo = array[fim];
    int i = inicio - 1;
    
    for (int j = inicio; j < fim; j++) {
        comparacoes++;
        if (compareShows(array[j], pivo) < 0) {
            i++;
            Show* temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            movimentacoes++;
        }
    }
    
    Show* temp = array[i + 1];
    array[i + 1] = array[fim];
    array[fim] = temp;
    movimentacoes++;
    
    int indicePivo = i + 1;
    ordenarQuicksort(array, inicio, indicePivo - 1);
    ordenarQuicksort(array, indicePivo + 1, fim);
}

int main() {
    preencherCatalogo();
    inicializarLista();

    char entrada[50];
    fgets(entrada, sizeof(entrada), stdin);
    entrada[strcspn(entrada, "\n")] = 0;

    Show* resultados[MAX_SHOWS];
    int tamanhoResultados = 0;

    while (!isFim(entrada)) {
        Show* show = localizarShow(entrada);
        if (show) {
            resultados[tamanhoResultados++] = show;
        }
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
    }

    clock_t inicio = clock();
    ordenarQuicksort(resultados, 0, tamanhoResultados - 1);
    clock_t fim = clock();

    for (int i = 0; i < tamanhoResultados; i++) {
        adicionarFim(resultados[i]);
    }

    exibirLista();

    double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC;
    FILE* log = fopen("matricula_quicksort.txt", "w");
    if (log) {
        fprintf(log, "850847\t%d\t%d\t%lf\n", comparacoes, movimentacoes, tempoExecucao);
        fclose(log);
    }

    CelulaDupla* atual = primeiro;
    while (atual) {
        CelulaDupla* tmp = atual;
        atual = atual->prox;
        free(tmp);
    }

    for (int i = 0; i < totalShows; i++) {
        Show* s = catalogo[i];
        free(s->show_id);
        free(s->type);
        free(s->title);
        free(s->director);
        for (int j = 0; j < s->castSize; j++) free(s->cast[j]);
        free(s->cast);
        free(s->country);
        free(s->rating);
        free(s->duration);
        for (int j = 0; j < s->listedInSize; j++) free(s->listed_in[j]);
        free(s->listed_in);
        free(s->description);
        free(s);
    }

    return 0;
}