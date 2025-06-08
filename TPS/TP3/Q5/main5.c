#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#define MAX_STR 256
#define MAX_ARRAY 50

typedef struct {
    char show_id[MAX_STR];
    char type[MAX_STR];
    char title[MAX_STR];
    char* director[MAX_ARRAY];
    char* cast[MAX_ARRAY];
    char country[MAX_STR];
    time_t date_added;
    int release_year;
    char rating[MAX_STR];
    char duration[MAX_STR];
    char* listed_in[MAX_ARRAY];
} SHOW;

typedef struct Celula {
    SHOW* show;
    struct Celula* prox;
} Celula;

typedef struct {
    Celula* primeiro;
    Celula* ultimo;
} ListaFlexivel;

SHOW* show_create();
void show_free(SHOW* show);
void show_clone(SHOW* dest, SHOW* src);
void show_set_id(SHOW* show, const char* id);
void show_set_type(SHOW* show, const char* type);
void show_set_title(SHOW* show, const char* title);
void show_set_director(SHOW* show, char** director, int count);
void show_set_cast(SHOW* show, char** cast, int count);
void show_set_country(SHOW* show, const char* country);
void show_set_date_added(SHOW* show, const char* date_str);
void show_set_release_year(SHOW* show, int year);
void show_set_rating(SHOW* show, const char* rating);
void show_set_duration(SHOW* show, const char* duration);
void show_set_listed_in(SHOW* show, char** listed_in, int count);
void show_leitura(SHOW* show, char* line);
void show_imprimir(SHOW* show);
void sort_string_array(char** arr, int size);

Celula* celula_create();
Celula* celula_create_with_show(SHOW* s);
void celula_free(Celula* cel);
ListaFlexivel* lista_create();
void lista_free(ListaFlexivel* lista);
void lista_inserir_fim(ListaFlexivel* lista, SHOW* s);
void lista_inserir_inicio(ListaFlexivel* lista, SHOW* s);
void lista_inserir(ListaFlexivel* lista, SHOW* s, int pos);
SHOW* lista_remover_fim(ListaFlexivel* lista);
SHOW* lista_remover_inicio(ListaFlexivel* lista);
SHOW* lista_remover(ListaFlexivel* lista, int pos);
void lista_mostrar(ListaFlexivel* lista);
int lista_tamanho(ListaFlexivel* lista);
SHOW* localizar(const char* id, SHOW** shows, int index);

SHOW* show_create() {
    SHOW* show = (SHOW*)malloc(sizeof(SHOW));
    if (show) {
        show->show_id[0] = '\0';
        show->type[0] = '\0';
        show->title[0] = '\0';
        for (int i = 0; i < MAX_ARRAY; i++) {
            show->director[i] = NULL;
            show->cast[i] = NULL;
            show->listed_in[i] = NULL;
        }
        show->country[0] = '\0';
        show->date_added = 0;
        show->release_year = 0;
        show->rating[0] = '\0';
        show->duration[0] = '\0';
    }
    return show;
}

void show_free(SHOW* show) {
    if (show) {
        for (int i = 0; i < MAX_ARRAY && show->director[i]; i++) free(show->director[i]);
        for (int i = 0; i < MAX_ARRAY && show->cast[i]; i++) free(show->cast[i]);
        for (int i = 0; i < MAX_ARRAY && show->listed_in[i]; i++) free(show->listed_in[i]);
        free(show);
    }
}

void show_clone(SHOW* dest, SHOW* src) {
    strcpy(dest->show_id, src->show_id);
    strcpy(dest->type, src->type);
    strcpy(dest->title, src->title);
    for (int i = 0; i < MAX_ARRAY && src->director[i]; i++) {
        dest->director[i] = strdup(src->director[i]);
    }
    for (int i = 0; i < MAX_ARRAY && src->cast[i]; i++) {
        dest->cast[i] = strdup(src->cast[i]);
    }
    strcpy(dest->country, src->country);
    dest->date_added = src->date_added;
    dest->release_year = src->release_year;
    strcpy(dest->rating, src->rating);
    strcpy(dest->duration, src->duration);
    for (int i = 0; i < MAX_ARRAY && src->listed_in[i]; i++) {
        dest->listed_in[i] = strdup(src->listed_in[i]);
    }
}

void show_set_id(SHOW* show, const char* id) {
    strncpy(show->show_id, id, MAX_STR - 1);
    show->show_id[MAX_STR - 1] = '\0';
}

void show_set_type(SHOW* show, const char* type) {
    strncpy(show->type, type[0] ? type : "NaN", MAX_STR - 1);
    show->type[MAX_STR - 1] = '\0';
}

void show_set_title(SHOW* show, const char* title) {
    strncpy(show->title, title[0] ? title : "NaN", MAX_STR - 1);
    show->title[MAX_STR - 1] = '\0';
}

void show_set_director(SHOW* show, char** director, int count) {
    for (int i = 0; i < MAX_ARRAY && show->director[i]; i++) {
        free(show->director[i]);
        show->director[i] = NULL;
    }
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        show->director[i] = strdup(director[i] && director[i][0] ? director[i] : "NaN");
    }
    sort_string_array(show->director, count);
}

void show_set_cast(SHOW* show, char** cast, int count) {
    for (int i = 0; i < MAX_ARRAY && show->cast[i]; i++) {
        free(show->cast[i]);
        show->cast[i] = NULL;
    }
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        show->cast[i] = strdup(cast[i] && cast[i][0] ? cast[i] : "NaN");
    }
    sort_string_array(show->cast, count);
}

void show_set_country(SHOW* show, const char* country) {
    strncpy(show->country, country[0] ? country : "NaN", MAX_STR - 1);
    show->country[MAX_STR - 1] = '\0';
}

void show_set_date_added(SHOW* show, const char* date_str) {
    if (date_str && date_str[0]) {
        struct tm tm = {0};
        if (strptime(date_str, "%B %d, %Y", &tm) != NULL) {
            show->date_added = mktime(&tm);
        } else {
            show->date_added = 0;
        }
    } else {
        show->date_added = 0;
    }
}

void show_set_release_year(SHOW* show, int year) {
    show->release_year = year;
}

void show_set_rating(SHOW* show, const char* rating) {
    strncpy(show->rating, rating[0] ? rating : "NaN", MAX_STR - 1);
    show->rating[MAX_STR - 1] = '\0';
}

void show_set_duration(SHOW* show, const char* duration) {
    strncpy(show->duration, duration[0] ? duration : "NaN", MAX_STR - 1);
    show->duration[MAX_STR - 1] = '\0';
}

void show_set_listed_in(SHOW* show, char** listed_in, int count) {
    for (int i = 0; i < MAX_ARRAY && show->listed_in[i]; i++) {
        free(show->listed_in[i]);
        show->listed_in[i] = NULL;
    }
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        show->listed_in[i] = strdup(listed_in[i] && listed_in[i][0] ? listed_in[i] : "NaN");
    }
    sort_string_array(show->listed_in, count);
}

void sort_string_array(char** arr, int size) {
    for (int i = 0; i < size - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < size; j++) {
            if (arr[j] && arr[min_idx] && strcmp(arr[min_idx], arr[j]) > 0) {
                min_idx = j;
            }
        }
        if (min_idx != i) {
            char* temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
        }
    }
}

void show_leitura(SHOW* show, char* line) {
    char* partes[11];
    int parte_idx = 0;
    int dentro_aspas = 0;
    char* atual = (char*)malloc(MAX_STR);
    int atual_idx = 0;

    for (int i = 0; line[i] && parte_idx < 11; i++) {
        if (line[i] == '"') {
            dentro_aspas = !dentro_aspas;
        } else if (line[i] == ',' && !dentro_aspas) {
            atual[atual_idx] = '\0';
            partes[parte_idx] = strdup(atual);
            parte_idx++;
            atual_idx = 0;
        } else {
            if (atual_idx < MAX_STR - 1) {
                atual[atual_idx++] = line[i];
            }
        }
    }
    atual[atual_idx] = '\0';
    partes[parte_idx] = strdup(atual);
    parte_idx++;

    show_set_id(show, partes[0]);
    show_set_type(show, partes[1]);
    show_set_title(show, partes[2]);

    char* directors[MAX_ARRAY];
    int dir_count = 0;
    char* token = strtok(partes[3], ",");
    while (token && dir_count < MAX_ARRAY) {
        while (*token && isspace(*token)) token++;
        directors[dir_count++] = strdup(token);
        token = strtok(NULL, ",");
    }
    show_set_director(show, directors, dir_count);

    char* cast[MAX_ARRAY];
    int cast_count = 0;
    token = strtok(partes[4], ",");
    while (token && cast_count < MAX_ARRAY) {
        while (*token && isspace(*token)) token++;
        cast[cast_count++] = strdup(token);
        token = strtok(NULL, ",");
    }
    show_set_cast(show, cast, cast_count);

    show_set_country(show, partes[5]);
    show_set_date_added(show, partes[6]);
    show_set_release_year(show, atoi(partes[7]));
    show_set_rating(show, partes[8]);
    show_set_duration(show, partes[9]);

    char* listed_in[MAX_ARRAY];
    int listed_count = 0;
    token = strtok(partes[10], ",");
    while (token && listed_count < MAX_ARRAY) {
        while (*token && isspace(*token)) token++;
        listed_in[listed_count++] = strdup(token);
        token = strtok(NULL, ",");
    }
    show_set_listed_in(show, listed_in, listed_count);

    for (int i = 0; i < parte_idx; i++) free(partes[i]);
    for (int i = 0; i < dir_count; i++) free(directors[i]);
    for (int i = 0; i < cast_count; i++) free(cast[i]);
    for (int i = 0; i < listed_count; i++) free(listed_in[i]);
    free(atual);
}

void show_imprimir(SHOW* show) {
    printf("=> %s ## %s ## %s ## ", show->show_id, show->title, show->type);
    for (int i = 0; show->director[i]; i++) {
        printf("%s%s", show->director[i], show->director[i + 1] ? ", " : " ## ");
    }
    printf("[");
    for (int i = 0; show->cast[i]; i++) {
        printf("%s%s", show->cast[i], show->cast[i + 1] ? ", " : "");
    }
    printf("] ## %s ## ", show->country);
    if (show->date_added != 0) {
        char date_str[50];
        struct tm* tm = localtime(&show->date_added);
        strftime(date_str, sizeof(date_str), "%B %d, %Y", tm);
        printf("%s ## ", date_str);
    } else {
        printf("NaN ## ");
    }
    printf("%d ## %s ## %s ## [", show->release_year, show->rating, show->duration);
    for (int i = 0; show->listed_in[i]; i++) {
        printf("%s%s", show->listed_in[i], show->listed_in[i + 1] ? ", " : "");
    }
    printf("] ##\n");
}

Celula* celula_create() {
    Celula* cel = (Celula*)malloc(sizeof(Celula));
    if (cel) {
        cel->show = NULL;
        cel->prox = NULL;
    }
    return cel;
}

Celula* celula_create_with_show(SHOW* s) {
    Celula* cel = (Celula*)malloc(sizeof(Celula));
    if (cel) {
        cel->show = show_create();
        show_clone(cel->show, s);
        cel->prox = NULL;
    }
    return cel;
}

void celula_free(Celula* cel) {
    if (cel) {
        show_free(cel->show);
        free(cel);
    }
}

ListaFlexivel* lista_create() {
    ListaFlexivel* lista = (ListaFlexivel*)malloc(sizeof(ListaFlexivel));
    if (lista) {
        lista->primeiro = celula_create();
        lista->ultimo = lista->primeiro;
    }
    return lista;
}

void lista_free(ListaFlexivel* lista) {
    Celula* current = lista->primeiro;
    while (current) {
        Celula* next = current->prox;
        celula_free(current);
        current = next;
    }
    free(lista);
}

void lista_inserir_fim(ListaFlexivel* lista, SHOW* s) {
    Celula* nova = celula_create_with_show(s);
    lista->ultimo->prox = nova;
    lista->ultimo = nova;
}

void lista_inserir_inicio(ListaFlexivel* lista, SHOW* s) {
    Celula* nova = celula_create_with_show(s);
    nova->prox = lista->primeiro->prox;
    lista->primeiro->prox = nova;
    if (lista->primeiro == lista->ultimo) {
        lista->ultimo = nova;
    }
}

void lista_inserir(ListaFlexivel* lista, SHOW* s, int pos) {
    if (pos == 0) {
        lista_inserir_inicio(lista, s);
    } else if (pos == lista_tamanho(lista)) {
        lista_inserir_fim(lista, s);
    } else {
        Celula* i = lista->primeiro;
        for (int j = 0; j < pos; j++) i = i->prox;
        Celula* nova = celula_create_with_show(s);
        nova->prox = i->prox;
        i->prox = nova;
    }
}

SHOW* lista_remover_fim(ListaFlexivel* lista) {
    if (lista->primeiro == lista->ultimo) return NULL;
    Celula* i = lista->primeiro;
    while (i->prox != lista->ultimo) i = i->prox;
    SHOW* resp = show_create();
    show_clone(resp, lista->ultimo->show);
    celula_free(lista->ultimo);
    lista->ultimo = i;
    i->prox = NULL;
    return resp;
}

SHOW* lista_remover_inicio(ListaFlexivel* lista) {
    if (lista->primeiro->prox == NULL) return NULL;
    Celula* tmp = lista->primeiro->prox;
    lista->primeiro->prox = tmp->prox;
    SHOW* resp = show_create();
    show_clone(resp, tmp->show);
    celula_free(tmp);
    if (lista->primeiro->prox == NULL) lista->ultimo = lista->primeiro;
    return resp;
}

SHOW* lista_remover(ListaFlexivel* lista, int pos) {
    if (pos == 0) return lista_remover_inicio(lista);
    if (pos == lista_tamanho(lista) - 1) return lista_remover_fim(lista);
    Celula* i = lista->primeiro;
    for (int j = 0; j < pos; j++) i = i->prox;
    Celula* tmp = i->prox;
    SHOW* resp = show_create();
    show_clone(resp, tmp->show);
    i->prox = tmp->prox;
    celula_free(tmp);
    return resp;
}

void lista_mostrar(ListaFlexivel* lista) {
    for (Celula* i = lista->primeiro->prox; i != NULL; i = i->prox) {
        show_imprimir(i->show);
    }
}

int lista_tamanho(ListaFlexivel* lista) {
    int tam = 0;
    for (Celula* i = lista->primeiro->prox; i != NULL; i = i->prox) tam++;
    return tam;
}

SHOW* localizar(const char* id, SHOW** shows, int index) {
    SHOW* tmp = show_create();
    for (int i = 0; i < index; i++) {
        if (strcmp(id, shows[i]->show_id) == 0) {
            show_clone(tmp, shows[i]);
            break;
        }
    }
    return tmp;
}

int main() {
    SHOW* shows[1368];
    int index = 0;
    char line[1024];

    FILE* fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }
    fgets(line, sizeof(line), fp);
    while (fgets(line, sizeof(line), fp) && index < 1368) {
        line[strcspn(line, "\n")] = '\0';
        shows[index] = show_create();
        show_leitura(shows[index], line);
        index++;
    }
    fclose(fp);

    ListaFlexivel* lista = lista_create();
    char entrada[256];
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0) break;

        fp = fopen("/tmp/disneyplus.csv", "r");
        if (!fp) {
            printf("Erro ao acessar o arquivo\n");
            continue;
        }
        fgets(line, sizeof(line), fp);
        int encontrado = 0;
        while (fgets(line, sizeof(line), fp)) {
            line[strcspn(line, "\n")] = '\0';
            if (strncmp(line, entrada, strlen(entrada)) == 0 && line[strlen(entrada)] == ',') {
                SHOW* tmp = show_create();
                show_leitura(tmp, line);
                lista_inserir_fim(lista, tmp);
                show_free(tmp);
                encontrado = 1;
                break;
            }
        }
        if (!encontrado) {
            printf("Show ID %s não encontrado.\n", entrada);
        }
        fclose(fp);
    }

    int n;
    scanf("%d", &n);
    getchar();
    for (int i = 0; i < n; i++) {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = '\0';
        char* partes[3];
        int parte_idx = 0;
        char* token = strtok(entrada, " ");
        while (token && parte_idx < 3) {
            partes[parte_idx++] = token;
            token = strtok(NULL, " ");
        }

        if (strcmp(partes[0], "II") == 0) {
            SHOW* tmp = localizar(partes[1], shows, index);
            lista_inserir_inicio(lista, tmp);
            show_free(tmp);
        } else if (strcmp(partes[0], "IF") == 0) {
            SHOW* tmp = localizar(partes[1], shows, index);
            lista_inserir_fim(lista, tmp);
            show_free(tmp);
        } else if (strcmp(partes[0], "I*") == 0) {
            int pos = atoi(partes[1]);
            SHOW* tmp = localizar(partes[2], shows, index);
            lista_inserir(lista, tmp, pos);
            show_free(tmp);
        } else if (strcmp(partes[0], "RI") == 0) {
            SHOW* tmp = lista_remover_inicio(lista);
            if (tmp) {
                printf("(R) %s\n", tmp->title);
                show_free(tmp);
            }
        } else if (strcmp(partes[0], "RF") == 0) {
            SHOW* tmp = lista_remover_fim(lista);
            if (tmp) {
                printf("(R) %s\n", tmp->title);
                show_free(tmp);
            }
        } else if (strcmp(partes[0], "R*") == 0) {
            int pos = atoi(partes[1]);
            SHOW* tmp = lista_remover(lista, pos);
            if (tmp) {
                printf("(R) %s\n", tmp->title);
                show_free(tmp);
            }
        }
    }

    lista_mostrar(lista);
    for (int i = 0; i < index; i++) show_free(shows[i]);
    lista_free(lista);
    return 0;
}