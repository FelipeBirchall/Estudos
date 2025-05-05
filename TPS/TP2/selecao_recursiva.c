#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define TAM_MAX_LINHA 1024
#define TAM_MAX_SHOWS 10000
#define TAM_MAX_LISTA 50

// Estrutura para Data
typedef struct Data {
    char mes[20];
    int dia;
    int ano;
} Data;

// Estrutura principal para shows
typedef struct Show {
    char* id;
    char* tipo;
    char* titulo;
    char* diretor;
    char** elenco;       
    int tamanhoElenco;      
    char* pais;
    Data dataAdicionado;   
    int anoLancamento;
    char* classificacao;
    char* duracao;
    char** categorias; 
    int tamanhoCategorias;
    char* descricao;
} Show;

Show* lista[TAM_MAX_SHOWS];
int totalShows = 0;


int contComparacoes = 0;
int contMovimentacoes = 0;

// Limpa espaços desnecessários
char* limparEspaco(char* str) {
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
    char* resultado = malloc(strlen(str) + 1);
    if (!resultado) {
        exit(1);
    }
    int j = 0;  
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            resultado[j++] = str[i];
        }
    }
    resultado[j] = '\0';
    return resultado;
}

// Processa o campo removendo aspas e espaços
char* tirarAspasEEspaco(char* campo) {
    if (!campo || strlen(campo) == 0) return strdup("NaN");
    char* semAspas = tirarAspas(campo);
    char* resultado = limparEspaco(semAspas);
    free(semAspas);
    return resultado;
}


char** tratarLista(char* str, int* tamanho) {
    char** itens = malloc(TAM_MAX_LISTA * sizeof(char*));
    if (!itens) {
        exit(1);
    }
    *tamanho = 0;

    if (!str || strlen(str) == 0) {
        itens[(*tamanho)++] = strdup("NaN");
        return itens;
    }

    char* limpo = tirarAspas(str);
    char* token = strtok(limpo, ",");
    while (token != NULL && *tamanho < TAM_MAX_LISTA) {
        char* item = tirarAspasEEspaco(token);
        itens[(*tamanho)++] = item;
        token = strtok(NULL, ",");
    }

    free(limpo);


    for (int i = 0; i < *tamanho - 1; i++) {
        for (int j = 0; j < *tamanho - i - 1; j++) {
            if (strcasecmp(itens[j], itens[j + 1]) > 0) {
                char* temp = itens[j];
                itens[j] = itens[j + 1];
                itens[j + 1] = temp;
            }
        }
    }

    if (*tamanho == 0) {
        itens[(*tamanho)++] = strdup("NaN");
    }
    return itens;
}


int dividirCampos(char* linha, char* campos[], int maxCampos) {
    int i = 0;
    char* ptr = linha;
    while (*ptr && i < maxCampos) {
        if (*ptr == '"') {
            ptr++;
            char* inicio = ptr;
            while (*ptr && !(*ptr == '"' && (*(ptr + 1) == ',' || *(ptr + 1) == '\0'))) {
                ptr++;
            }
            int len = ptr - inicio;
            campos[i] = malloc(len + 1);
            if (!campos[i]) {
                exit(1);
            }
            strncpy(campos[i], inicio, len);
            campos[i][len] = '\0';
            if (*ptr == '"') ptr++;
            if (*ptr == ',') ptr++;
        } else {
            char* inicio = ptr;
            while (*ptr && *ptr != ',') ptr++;
            int len = ptr - inicio;
            campos[i] = malloc(len + 1);
            if (!campos[i]) {
                exit(1);
            }
            strncpy(campos[i], inicio, len);
            campos[i][len] = '\0';
            if (*ptr == ',') ptr++;
        }
        i++;
    }
    return i;
}

// Leitura do CSV
void carregarCSV() {
    FILE* arquivo = fopen("/tmp/disneyplus.csv", "r");
    if (arquivo == NULL) {
        return;
    }

    char linha[TAM_MAX_LINHA];
    fgets(linha, TAM_MAX_LINHA, arquivo); 

    while (fgets(linha, TAM_MAX_LINHA, arquivo)) {
        linha[strcspn(linha, "\n")] = 0;

        char* campos[15];
        int quantidadeCampos = dividirCampos(linha, campos, 15);

        Show* shows = malloc(sizeof(Show));
        if (!shows) {
            exit(1);
        }
        shows->id = tirarAspasEEspaco(campos[0]);
        shows->tipo = tirarAspasEEspaco(campos[1]);
        shows->titulo = tirarAspasEEspaco(campos[2]);
        shows->diretor = tirarAspasEEspaco(campos[3]);
        shows->elenco = tratarLista(campos[4], &shows->tamanhoElenco);
        shows->pais = tirarAspasEEspaco(campos[5]);

        // Processa a data
        char* dataStr = tirarAspasEEspaco(campos[6]);
        if (strcmp(dataStr, "NaN") != 0) {
            char* parte = strtok(dataStr, " ");
            strcpy(shows->dataAdicionado.mes, parte ? parte : "NaN");
            parte = strtok(NULL, ",");
            shows->dataAdicionado.dia = parte ? atoi(parte) : -1;
            parte = strtok(NULL, "");
            shows->dataAdicionado.ano = parte ? atoi(parte) : -1;
        } else {
            strcpy(shows->dataAdicionado.mes, "March");
            shows->dataAdicionado.dia = 1;
            shows->dataAdicionado.ano = 1900;
        }
        free(dataStr);

        shows->anoLancamento = (quantidadeCampos > 7 && strlen(campos[7]) > 0) ? atoi(campos[7]) : -1;
        shows->classificacao = tirarAspasEEspaco(campos[8]);
        shows->duracao = tirarAspasEEspaco(campos[9]);
        shows->categorias = tratarLista(campos[10], &shows->tamanhoCategorias);
        shows->descricao = tirarAspasEEspaco(campos[11]);

        for (int i = 0; i < quantidadeCampos; i++) free(campos[i]);
        lista[totalShows++] = shows;
    }

    fclose(arquivo);
}


void exibirShow(Show* showAtual) {
    printf("=> %s ## %s ## %s ## %s ## [", showAtual->id, showAtual->titulo, showAtual->tipo, showAtual->diretor);
    for (int i = 0; i < showAtual->tamanhoElenco; i++) {
        printf("%s%s", showAtual->elenco[i], (i < showAtual->tamanhoElenco - 1) ? ", " : "");
    }
    printf("] ## %s ## %s %d, %d ## %d ## %s ## %s ## [", showAtual->pais, showAtual->dataAdicionado.mes, showAtual->dataAdicionado.dia,
           showAtual->dataAdicionado.ano, showAtual->anoLancamento, showAtual->classificacao, showAtual->duracao);
    for (int i = 0; i < showAtual->tamanhoCategorias; i++) {
        printf("%s%s", showAtual->categorias[i], (i < showAtual->tamanhoCategorias - 1) ? ", " : "");
    }
    printf("] ##\n");
}


bool verificarFim(char* str) {
    return strcasecmp(str, "FIM") == 0;
}

// Ordenacao por selecao recursiva
void ordenarPorSelecaoRecursiva(Show* array[], int n, int i) {
    if (i >= n - 1) return;

    int menor = i;
    for (int j = i + 1; j < n; j++) {
        contComparacoes++;
        if (strcasecmp(array[j]->titulo, array[menor]->titulo) < 0) {
            menor = j;
        }
    }
    if (menor != i) {
        Show* temp = array[i];
        array[i] = array[menor];
        array[menor] = temp;
        contMovimentacoes++;
    }
    ordenarPorSelecaoRecursiva(array, n, i + 1);
}


int main() {
    carregarCSV();

    char entrada[50];
    fgets(entrada, sizeof(entrada), stdin);
    entrada[strcspn(entrada, "\n")] = 0;

    Show* resultados[TAM_MAX_SHOWS];
    int tamanhoResultados = 0;

    while (!verificarFim(entrada)) {
        for (int i = 0; i < totalShows; i++) {
            if (strcasecmp(lista[i]->id, entrada) == 0) {
                resultados[tamanhoResultados++] = lista[i];
                break;
            }
        }
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
    }

    clock_t inicio = clock();

    ordenarPorSelecaoRecursiva(resultados, tamanhoResultados, 0);

    clock_t fim = clock();

    for (int i = 0; i < tamanhoResultados; i++) {
        exibirShow(resultados[i]);
    }

    double tempoExecucao = ((double)(fim - inicio)) / CLOCKS_PER_SEC;

    FILE* log = fopen("matricula_selecaoRecursiva.txt", "w");
    if (log) {
        fprintf(log, "844448\t%d\t%d\t%lf\n", contComparacoes, contMovimentacoes, tempoExecucao);
        fclose(log);
    }

    return 0;
}
