#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>

#define TAM_MAX_LINHA 1024
#define TAM_MAX_SHOWS 10000
#define TAM_MAX_LISTA 50

// Estrutura para data
typedef struct {
    char mes[20];
    int dia;
    int ano;
} Data;

// Estrutura principal para shows
typedef struct {
    char* id_programa;
    char* tipo;
    char* titulo;
    char* diretor;
    char** elenco;
    int qtdeElenco;
    char* pais;
    Data dataAdicao;
    int anoLancamento;
    char* classificacao;
    char* duracao;
    char** categorias;
    int qtdeCategorias;
    char* descricao;
} SHOW;

SHOW* lista[TAM_MAX_SHOWS];

int totalShows = 0;
int contComparacoes = 0;


// Limpa espaços desnecessários
char* limparEspacos(char* texto) {
    if (!texto) return strdup("NaN");
    while (isspace(*texto)) texto++;
    char* fim = texto + strlen(texto) - 1;
    while (fim > texto && isspace(*fim)) fim--;
    *(fim + 1) = '\0';
    return strdup(texto);
}

// Prototipagem
char* limparEspacos(char* texto);
char* tirarAspas(char* texto);
char* tratarCampo(char* texto);
char** tratarLista(char* texto, int* quantidade);
int quebrarLinha(char* linha, char* campos[], int limite);
void carregarArquivo();
int pesquisa_binaria(SHOW* vet[], int tamanho, char* chave);
void ordenarPorTitulo(SHOW* vet[], int tamanho);
int ehFim(char* entrada);

// Retira aspas duplas
char* tirarAspas(char* texto) {
    if (!texto) return NULL;
    char* resultado = malloc(strlen(texto) + 1);
    int idx = 0;
    for (int i = 0; texto[i]; i++) {
        if (texto[i] != '"') resultado[idx++] = texto[i];
    }
    resultado[idx] = '\0';
    return resultado;
}

// Combina remoções
char* tratarCampo(char* texto) {
    if (!texto || strlen(texto) == 0) return strdup("NaN");
    char* semAspas = tirarAspas(texto);
    char* final = limparEspacos(semAspas);
    free(semAspas);
    return final;
}

// Divide listas por vírgula e ordena
char** tratarLista(char* texto, int* quantidade) {
    char** lista = malloc(TAM_MAX_LISTA * sizeof(char*));
    *quantidade = 0;

    if (!texto || strlen(texto) == 0) {
        lista[(*quantidade)++] = strdup("NaN");
        return lista;
    }

    char* limpo = tirarAspas(texto);
    char* item = strtok(limpo, ",");
    while (item != NULL && *quantidade < TAM_MAX_LISTA) {
        lista[(*quantidade)++] = tratarCampo(item);
        item = strtok(NULL, ",");
    }

    free(limpo);

    // Ordenação alfabética (Bubble Sort)
    for (int i = 0; i < *quantidade - 1; i++) {
        for (int j = 0; j < *quantidade - i - 1; j++) {
            if (strcasecmp(lista[j], lista[j + 1]) > 0) {
                char* temp = lista[j];
                lista[j] = lista[j + 1];
                lista[j + 1] = temp;
            }
        }
    }

    if (*quantidade == 0) lista[(*quantidade)++] = strdup("NaN");
    return lista;
}

int quebrarLinha(char* linha, char* campos[], int limite) {
    int cont = 0;
    char* ptr = linha;
    while (*ptr && cont < limite) {
        if (*ptr == '"') {
            ptr++;
            char* ini = ptr;
            while (*ptr && !(*ptr == '"' && (*(ptr + 1) == ',' || *(ptr + 1) == '\0'))) ptr++;
            int tam = ptr - ini;
            campos[cont] = malloc(tam + 1);
            strncpy(campos[cont], ini, tam);
            campos[cont][tam] = '\0';
            if (*ptr == '"') ptr++;
            if (*ptr == ',') ptr++;
        } else {
            char* ini = ptr;
            while (*ptr && *ptr != ',') ptr++;
            int tam = ptr - ini;
            campos[cont] = malloc(tam + 1);
            strncpy(campos[cont], ini, tam);
            campos[cont][tam] = '\0';
            if (*ptr == ',') ptr++;
        }
        cont++;
    }
    return cont;
}

// Leitura do CSV
void carregarArquivo() {
    FILE* arq = fopen("/tmp/disneyplus.csv", "r");
    if (!arq) {
        printf("Erro ao abrir o arquivo!\n");
        return;
    }

    char linha[TAM_MAX_LINHA];
    fgets(linha, TAM_MAX_LINHA, arq);

    while (fgets(linha, TAM_MAX_LINHA, arq)) {
        linha[strcspn(linha, "\n")] = 0;

        char* campos[15];
        int qtdCampos = quebrarLinha(linha, campos, 15);

        SHOW* shows = malloc(sizeof(SHOW));
        shows->id_programa = tratarCampo(campos[0]);
        shows->tipo = tratarCampo(campos[1]);
        shows->titulo = tratarCampo(campos[2]);
        shows->diretor = tratarCampo(campos[3]);
        shows->elenco = tratarLista(campos[4], &shows->qtdeElenco);
        shows->pais = tratarCampo(campos[5]);

        char* data = tratarCampo(campos[6]);
        if (strcmp(data, "NaN") != 0) {
            char* parte = strtok(data, " ");
            strcpy(shows->dataAdicao.mes, parte ? parte : "NaN");
            parte = strtok(NULL, ",");
            shows->dataAdicao.dia = parte ? atoi(parte) : -1;
            parte = strtok(NULL, "");
            shows->dataAdicao.ano = parte ? atoi(parte) : -1;
        } else {
            strcpy(shows->dataAdicao.mes, "March");
            shows->dataAdicao.dia = 1;
            shows->dataAdicao.ano = 1900;
        }
        free(data);

        shows->anoLancamento = (qtdCampos > 7 && strlen(campos[7]) > 0) ? atoi(campos[7]) : -1;
        shows->classificacao = tratarCampo(campos[8]);
        shows->duracao = tratarCampo(campos[9]);
        shows->categorias = tratarLista(campos[10], &shows->qtdeCategorias);
        shows->descricao = tratarCampo(campos[11]);

        for (int i = 0; i < qtdCampos; i++) free(campos[i]);
        lista[totalShows++] = shows;
    }

    fclose(arq);
}

// Ordena os programas por título
void ordenarPorTitulo(SHOW* vet[], int tamanho) {
    for (int i = 0; i < tamanho - 1; i++) {
        for (int j = 0; j < tamanho - i - 1; j++) {
            if (strcasecmp(vet[j]->titulo, vet[j + 1]->titulo) > 0) {
                SHOW* tmp = vet[j];
                vet[j] = vet[j + 1];
                vet[j + 1] = tmp;
            }
        }
    }
}

// Pesquisa Binaria
int pesquisa_binaria(SHOW* vet[], int tamanho, char* chave) {
    int esq = 0, dir = tamanho - 1;
    while (esq <= dir) {
        int meio = (esq + dir) / 2;
        int cmp = strcasecmp(vet[meio]->titulo, chave);
        contComparacoes++;
        if (cmp == 0) return 1;
        else if (cmp < 0) esq = meio + 1;
        else dir = meio - 1;
    }
    return 0;
}

int ehFim(char* entrada) {
    return strcasecmp(entrada, "FIM") == 0;
}

int main() {
    carregarArquivo();

    char entrada[100];
    SHOW* selecionados[TAM_MAX_SHOWS];
    int tamanhoLista = 0;

    fgets(entrada, sizeof(entrada), stdin);
    entrada[strcspn(entrada, "\n")] = 0;

    while (!ehFim(entrada)) {
        for (int i = 0; i < totalShows; i++) {
            if (strcasecmp(lista[i]->id_programa, entrada) == 0) {
                selecionados[tamanhoLista++] = lista[i];
                break;
            }
        }
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
    }

    ordenarPorTitulo(selecionados, tamanhoLista);

    clock_t ini = clock();

    char tituloBusca[100];
    fgets(tituloBusca, sizeof(tituloBusca), stdin);
    tituloBusca[strcspn(tituloBusca, "\n")] = 0;

    while (!ehFim(tituloBusca)) {
        printf(pesquisa_binaria(selecionados, tamanhoLista, tituloBusca) ? "SIM\n" : "NAO\n");

        fgets(tituloBusca, sizeof(tituloBusca), stdin);
        tituloBusca[strcspn(tituloBusca, "\n")] = 0;
    }

    clock_t fim = clock();
    double tempoExecucao = (double)(fim - ini) / CLOCKS_PER_SEC;

    FILE* log = fopen("matricula_pesquisaBinaria.txt", "w");
    if (log) {
        fprintf(log, "844448\t%d\t%lf\n", contComparacoes, tempoExecucao);
        fclose(log);
    }

    return 0;
}
