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
    Data data_lancamento;
    int release_year;
    char* rating;
    char* duration;
    char** categorias;
    int categoriasQtd;
    char* descricao;
} Show;

Show* lista[TAM_MAX_SHOWS];
int quantidadeCatalogo = 0;

int contComparacoes = 0;
int contMovimentacoes = 0;

// Limpa espaços desnecessários
char* limparEspacos(char* str) {
    if (!str) return strdup("NaN");
    while (isspace(*str)) str++;
    char* fim = str + strlen(str) - 1;
    while (fim > str && isspace(*fim)) fim--;
    *(fim + 1) = '\0';
    return strdup(str);
}

// Retira aspas duplas
char* tirarAspas(char* str) {
    if (!str) return NULL;
    char* copia = malloc(strlen(str) + 1);
    int idx = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            copia[idx++] = str[i];
        }
    }
    copia[idx] = '\0';
    return copia;
}

// Combina remoções
char* tratarCampo(char* campo) {
    if (!campo || strlen(campo) == 0) return strdup("NaN");
    char* semAspas = tirarAspas(campo);
    char* final = limparEspacos(semAspas);
    free(semAspas);
    return final;
}

// Divide listas e ordena
char** separarLista(char* entrada, int* total) {
    char** resultado = malloc(TAM_MAX_LISTA * sizeof(char*));
    *total = 0;

    if (!entrada || strlen(entrada) == 0) {
        resultado[(*total)++] = strdup("NaN");
        return resultado;
    }

    char* aux = tirarAspas(entrada);
    char* token = strtok(aux, ",");

    while (token && *total < TAM_MAX_LISTA) {
        resultado[(*total)++] = tratarCampo(token);
        token = strtok(NULL, ",");
    }

    free(aux);

    // Ordena lista com Bubble Sort
    for (int i = 0; i < *total - 1; i++) {
        for (int j = 0; j < *total - i - 1; j++) {
            if (strcasecmp(resultado[j], resultado[j + 1]) > 0) {
                char* tmp = resultado[j];
                resultado[j] = resultado[j + 1];
                resultado[j + 1] = tmp;
            }
        }
    }

    if (*total == 0) {
        resultado[(*total)++] = strdup("NaN");
    }

    return resultado;
}

int extrairCampos(char* linha, char* vetCampos[], int max) {
    int i = 0;
    char* ptr = linha;
    while (*ptr && i < max) {
        if (*ptr == '"') {
            ptr++;
            char* inicio = ptr;
            while (*ptr && !(*ptr == '"' && (*(ptr + 1) == ',' || *(ptr + 1) == '\0'))) {
                ptr++;
            }
            int len = ptr - inicio;
            vetCampos[i] = malloc(len + 1);
            strncpy(vetCampos[i], inicio, len);
            vetCampos[i][len] = '\0';
            if (*ptr == '"') ptr++;
            if (*ptr == ',') ptr++;
        } else {
            char* inicio = ptr;
            while (*ptr && *ptr != ',') ptr++;
            int len = ptr - inicio;
            vetCampos[i] = malloc(len + 1);
            strncpy(vetCampos[i], inicio, len);
            vetCampos[i][len] = '\0';
            if (*ptr == ',') ptr++;
        }
        i++;
    }
    return i;
}

// Shellsort
void Shellsort(Show* vet[], int tam) {
    for (int dist = tam / 2; dist > 0; dist /= 2) {
        for (int i = dist; i < tam; i++) {
            Show* aux = vet[i];
            int j;
            for (j = i; j >= dist; j -= dist) {
                contComparacoes++;
                if (strcasecmp(vet[j - dist]->type, aux->type) > 0 ||
                   (strcasecmp(vet[j - dist]->type, aux->type) == 0 &&
                    strcasecmp(vet[j - dist]->title, aux->title) > 0)) {
                    vet[j] = vet[j - dist];
                    contMovimentacoes++;
                } else {
                    break;
                }
            }
            vet[j] = aux;
            contMovimentacoes++;
        }
    }
}

// Leitura do CSV
void carregarDados() {
    FILE* arq = fopen("/tmp/disneyplus.csv", "r");
    if (!arq) return;

    char linha[TAM_MAX_LINHA];
    fgets(linha, TAM_MAX_LINHA, arq); 

    while (fgets(linha, TAM_MAX_LINHA, arq)) {
        linha[strcspn(linha, "\n")] = 0;
        char* campos[15];
        int totalCampos = extrairCampos(linha, campos, 15);

        Show* shows = malloc(sizeof(Show));
        shows->show_id = tratarCampo(campos[0]);
        shows->type = tratarCampo(campos[1]);
        shows->title = tratarCampo(campos[2]);
        shows->director = tratarCampo(campos[3]);
        shows->cast = separarLista(campos[4], &shows->castSize);
        shows->country = tratarCampo(campos[5]);

        char* dataAux = tratarCampo(campos[6]);
        if (strcmp(dataAux, "NaN") != 0) {
            char* parte = strtok(dataAux, " ");
            strcpy(shows->data_lancamento.mes, parte ? parte : "NaN");
            parte = strtok(NULL, ",");
            shows->data_lancamento.dia = parte ? atoi(parte) : -1;
            parte = strtok(NULL, "");
            shows->data_lancamento.ano = parte ? atoi(parte) : -1;
        } else {
            strcpy(shows->data_lancamento.mes, "March");
            shows->data_lancamento.dia = 1;
            shows->data_lancamento.ano = 1900;
        }
        free(dataAux);

        shows->release_year = (totalCampos > 7 && strlen(campos[7]) > 0) ? atoi(campos[7]) : -1;
        shows->rating = tratarCampo(campos[8]);
        shows->duration = tratarCampo(campos[9]);
        shows->categorias = separarLista(campos[10], &shows->categoriasQtd);
        shows->descricao = tratarCampo(campos[11]);

        for (int i = 0; i < totalCampos; i++) free(campos[i]);
        lista[quantidadeCatalogo++] = shows;
    }

    fclose(arq);
}


void exibirDados(Show* shows) {
    printf("=> %s ## %s ## %s ## %s ## [", shows->show_id, shows->title, shows->type, shows->director);
    for (int i = 0; i < shows->castSize; i++) {
        printf("%s%s", shows->cast[i], (i < shows->castSize - 1) ? ", " : "");
    }
    printf("] ## %s ## %s %d, %d ## %d ## %s ## %s ## [", shows->country,
           shows->data_lancamento.mes, shows->data_lancamento.dia, shows->data_lancamento.ano,
           shows->release_year, shows->rating, shows->duration);
    for (int i = 0; i < shows->categoriasQtd; i++) {
        printf("%s%s", shows->categorias[i], (i < shows->categoriasQtd - 1) ? ", " : "");
    }
    printf("] ##\n");
}


bool verificarFim(char* linha) {
    return strcasecmp(linha, "FIM") == 0;
}



int main() {
    carregarDados();

    char entrada[50];
    fgets(entrada, sizeof(entrada), stdin);
    entrada[strcspn(entrada, "\n")] = 0;

    Show* encontrados[TAM_MAX_SHOWS];
    int qtdEncontrados = 0;

    while (!verificarFim(entrada)) {
        for (int i = 0; i < quantidadeCatalogo; i++) {
            if (strcasecmp(lista[i]->show_id, entrada) == 0) {
                encontrados[qtdEncontrados++] = lista[i];
                break;
            }
        }
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
    }

    clock_t tempoInicio = clock();
    Shellsort(encontrados, qtdEncontrados);
    clock_t tempoFim = clock();

    for (int i = 0; i < qtdEncontrados; i++) {
        exibirDados(encontrados[i]);
    }

    double tempoExecucao = ((double)(tempoFim - tempoInicio)) / CLOCKS_PER_SEC;

    FILE* log = fopen("matricula_shellsort.txt", "w");
    fprintf(log, "844448\t%ld\t%ld\t%.6lf\n", contComparacoes, contMovimentacoes, tempoExecucao);
    fclose(log);

    return 0;
}
