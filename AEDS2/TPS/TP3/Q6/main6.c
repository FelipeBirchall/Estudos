#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_LINE 2048
#define MAX_STR 256
#define MAX_ARRAY 100

typedef struct {
    char date_str[MAX_STR];
} Date;

typedef struct {
    char show_id[MAX_STR];
    char type[MAX_STR];
    char title[MAX_STR];
    char** director;
    int director_count;
    char** cast;
    int cast_count;
    char country[MAX_STR];
    Date date_added;
    int release_year;
    char rating[MAX_STR];
    char duration[MAX_STR];
    char** listed_in;
    int listed_in_count;
} SHOW;

typedef struct Celula {
    SHOW* show;
    struct Celula* prox;
} Celula;

typedef struct {
    Celula* topo;
} PilhaFlexivel;

Celula* new_Celula(SHOW* show) {
    Celula* tmp = (Celula*)malloc(sizeof(Celula));
    tmp->show = show;
    tmp->prox = NULL;
    return tmp;
}

PilhaFlexivel* new_PilhaFlexivel() {
    PilhaFlexivel* pilha = (PilhaFlexivel*)malloc(sizeof(PilhaFlexivel));
    pilha->topo = NULL;
    return pilha;
}

void inserir(PilhaFlexivel* pilha, SHOW* s) {
    Celula* tmp = new_Celula(s);
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
}

SHOW* remover(PilhaFlexivel* pilha) {
    if (pilha->topo == NULL) return NULL;
    SHOW* s = pilha->topo->show;
    Celula* tmp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    tmp->prox = NULL;
    free(tmp);
    return s;
}

int tamanho(PilhaFlexivel* pilha) {
    int tam = 0;
    for (Celula* i = pilha->topo; i != NULL; i = i->prox) {
        tam++;
    }
    return tam;
}

void mostrar(PilhaFlexivel* pilha) {
    int tam = tamanho(pilha) - 1;
    for (Celula* i = pilha->topo; i != NULL; i = i->prox) {
        printf("[%d] ", tam--);
        SHOW* tmp = i->show;
        printf("=> %s ## %s ## %s ## ", tmp->show_id, tmp->title, tmp->type);
        for (int j = 0; j < tmp->director_count; j++) {
            printf("%s", tmp->director[j]);
            if (j < tmp->director_count - 1) printf(", ");
        }
        printf(" ## [");
        for (int j = 0; j < tmp->cast_count; j++) {
            printf("%s", tmp->cast[j]);
            if (j < tmp->cast_count - 1) printf(", ");
        }
        printf("] ## %s ## %s ## %d ## %s ## %s ## [",
               tmp->country, tmp->date_added.date_str, tmp->release_year, tmp->rating, tmp->duration);
        for (int j = 0; j < tmp->listed_in_count; j++) {
            printf("%s", tmp->listed_in[j]);
            if (j < tmp->listed_in_count - 1) printf(", ");
        }
        printf("] ##\n");
    }
}

SHOW* new_SHOW() {
    SHOW* show = (SHOW*)malloc(sizeof(SHOW));
    show->director = NULL;
    show->director_count = 0;
    show->cast = NULL;
    show->cast_count = 0;
    show->listed_in = NULL;
    show->listed_in_count = 0;
    strcpy(show->date_added.date_str, "NaN");
    return show;
}

void free_SHOW(SHOW* show) {
    if (show == NULL) return;
    for (int i = 0; i < show->director_count; i++) free(show->director[i]);
    free(show->director);
    for (int i = 0; i < show->cast_count; i++) free(show->cast[i]);
    free(show->cast);
    for (int i = 0; i < show->listed_in_count; i++) free(show->listed_in[i]);
    free(show->listed_in);
    free(show);
}

SHOW* clone_SHOW(SHOW* src) {
    SHOW* clonado = new_SHOW();
    strcpy(clonado->show_id, src->show_id);
    strcpy(clonado->type, src->type);
    strcpy(clonado->title, src->title);
    strcpy(clonado->country, src->country);
    strcpy(clonado->date_added.date_str, src->date_added.date_str);
    clonado->release_year = src->release_year;
    strcpy(clonado->rating, src->rating);
    strcpy(clonado->duration, src->duration);

    clonado->director_count = src->director_count;
    clonado->director = (char**)malloc(src->director_count * sizeof(char*));
    for (int i = 0; i < src->director_count; i++) {
        clonado->director[i] = strdup(src->director[i]);
    }

    clonado->cast_count = src->cast_count;
    clonado->cast = (char**)malloc(src->cast_count * sizeof(char*));
    for (int i = 0; i < src->cast_count; i++) {
        clonado->cast[i] = strdup(src->cast[i]);
    }

    clonado->listed_in_count = src->listed_in_count;
    clonado->listed_in = (char**)malloc(src->listed_in_count * sizeof(char*));
    for (int i = 0; i < src->listed_in_count; i++) {
        clonado->listed_in[i] = strdup(src->listed_in[i]);
    }

    return clonado;
}

void set_string_field(char* dest, const char* src) {
    if (src == NULL || strlen(src) == 0) {
        strcpy(dest, "NaN");
    } else {
        strcpy(dest, src);
    }
}

char** set_array_field(const char* input, int* count) {
    char temp[MAX_LINE];
    strcpy(temp, input);
    char** result = NULL;
    *count = 0;

    char* token = strtok(temp, ",");
    while (token != NULL) {
        while (*token && isspace(*token)) token++;
        char* end = token + strlen(token) - 1;
        while (end > token && isspace(*end)) end--;
        *(end + 1) = '\0';

        (*count)++;
        result = (char**)realloc(result, (*count) * sizeof(char*));
        result[*count - 1] = strdup(token[0] == '\0' ? "NaN" : token);
        token = strtok(NULL, ",");
    }

    for (int i = 0; i < *count - 1; i++) {
        int menor = i;
        for (int j = i + 1; j < *count; j++) {
            if (strcmp(result[menor], result[j]) > 0) {
                menor = j;
            }
        }
        if (menor != i) {
            char* temp = result[menor];
            result[menor] = result[i];
            result[i] = temp;
        }
    }

    return result;
}

void leitura(SHOW* show, const char* entrada) {
    char partes[11][MAX_LINE];
    int parte_idx = 0;
    bool dentroAspas = false;
    char atual[MAX_LINE] = {0};
    int atual_idx = 0;

    for (int i = 0; i < strlen(entrada) && parte_idx < 11; i++) {
        char c = entrada[i];
        if (c == '"') {
            dentroAspas = !dentroAspas;
        } else if (c == ',' && !dentroAspas) {
            atual[atual_idx] = '\0';
            strcpy(partes[parte_idx], atual);
            parte_idx++;
            atual_idx = 0;
            memset(atual, 0, MAX_LINE);
        } else {
            atual[atual_idx++] = c;
        }
    }
    atual[atual_idx] = '\0';
    strcpy(partes[parte_idx], atual);

    set_string_field(show->show_id, partes[0]);
    set_string_field(show->type, partes[1]);
    set_string_field(show->title, partes[2]);
    show->director = set_array_field(partes[3], &show->director_count);
    show->cast = set_array_field(partes[4], &show->cast_count);
    set_string_field(show->country, partes[5]);
    set_string_field(show->date_added.date_str, partes[6]);
    show->release_year = atoi(partes[7]);
    set_string_field(show->rating, partes[8]);
    set_string_field(show->duration, partes[9]);
    show->listed_in = set_array_field(partes[10], &show->listed_in_count);
}

SHOW* localizar(const char* id, SHOW** shows, int index) {
    SHOW* tmp = new_SHOW();
    for (int i = 0; i < index; i++) {
        if (strcmp(id, shows[i]->show_id) == 0) {
            free_SHOW(tmp);
            return clone_SHOW(shows[i]);
        }
    }
    return tmp;
}

int main() {
    SHOW* shows[1368];
    int index = 0;

    FILE* file = fopen("/tmp/disneyplus.csv", "r");
    if (!file) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }

    char linha[MAX_LINE];
    fgets(linha, MAX_LINE, file);
    while (fgets(linha, MAX_LINE, file) && index < 1368) {
        linha[strcspn(linha, "\n")] = 0;
        shows[index] = new_SHOW();
        leitura(shows[index], linha);
        index++;
    }
    fclose(file);

    PilhaFlexivel* pilha = new_PilhaFlexivel();
    char entrada[MAX_STR];

    while (fgets(entrada, MAX_STR, stdin)) {
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        file = fopen("/tmp/disneyplus.csv", "r");
        if (!file) {
            printf("Erro ao acessar o arquivo\n");
            continue;
        }
        fgets(linha, MAX_LINE, file);
        bool encontrado = false;

        while (fgets(linha, MAX_LINE, file)) {
            linha[strcspn(linha, "\n")] = 0;
            if (strncmp(linha, entrada, strlen(entrada)) == 0 && linha[strlen(entrada)] == ',') {
                SHOW* tmp = new_SHOW();
                leitura(tmp, linha);
                inserir(pilha, tmp);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            printf("Show ID %s não encontrado.\n", entrada);
        }
        fclose(file);
    }

    int n;
    scanf("%d", &n);
    getchar();
    for (int i = 0; i < n; i++) {
        fgets(entrada, MAX_STR, stdin);
        entrada[strcspn(entrada, "\n")] = 0;
        if (entrada[0] == 'I') {
            char* id = entrada + 2;
            SHOW* tmp = localizar(id, shows, index);
            inserir(pilha, tmp);
        } else if (entrada[0] == 'R') {
            SHOW* tmp = remover(pilha);
            if (tmp != NULL) {
                printf("(R) %s\n", tmp->title);
                free_SHOW(tmp);
            }
        }
    }

    mostrar(pilha);

    for (int i = 0; i < index; i++) {
        free_SHOW(shows[i]);
    }
    while (pilha->topo != NULL) {
        SHOW* tmp = remover(pilha);
        free_SHOW(tmp);
    }
    free(pilha);

    return 0;
}