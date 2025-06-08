#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <stdbool.h>

#define MAX_STR 256
#define MAX_ARRAY 100
#define QUEUE_SIZE 6
#define MAX_SHOWS 1368

typedef struct {
    char show_id[MAX_STR];
    char type[MAX_STR];
    char title[MAX_STR];
    char* director[MAX_ARRAY];
    char* cast[MAX_ARRAY];
    char country[MAX_STR];
    struct tm* date_added;
    int release_year;
    char rating[MAX_STR];
    char duration[MAX_STR];
    char* listed_in[MAX_ARRAY];
    int director_count;
    int cast_count;
    int listed_in_count;
} Show;

typedef struct {
    Show* show[QUEUE_SIZE];
    int primeiro, ultimo;
} FilaCircular;

void init_fila(FilaCircular* fila);
void inserir(FilaCircular* fila, Show* s);
int media(FilaCircular* fila);
Show* remover(FilaCircular* fila);
void mostrar(FilaCircular* fila);
void init_show(Show* s);
Show* clone_show(Show* s);
void set_id(Show* s, const char* id);
void set_type(Show* s, const char* type);
void set_title(Show* s, const char* title);
void set_director(Show* s, char** director, int count);
void set_cast(Show* s, char** cast, int count);
void set_country(Show* s, const char* country);
void set_date_added(Show* s, struct tm* date);
void set_release_year(Show* s, int year);
void set_rating(Show* s, const char* rating);
void set_duration(Show* s, const char* duration);
void set_listed_in(Show* s, char** listed_in, int count);
void leitura(Show* s, char* entrada);
void ordenar(char** lista, int count);
void imprimir(Show* s);
Show* localizar(const char* id, Show* s, int index);

void init_fila(FilaCircular* fila) {
    for (int i = 0; i < QUEUE_SIZE; i++) {
        fila->show[i] = NULL;
    }
    fila->primeiro = 0;
    fila->ultimo = 0;
}

void inserir(FilaCircular* fila, Show* s) {
    if (((fila->ultimo + 1) % QUEUE_SIZE) == fila->primeiro) {
        remover(fila);
    }
    fila->show[fila->ultimo] = clone_show(s);
    fila->ultimo = (fila->ultimo + 1) % QUEUE_SIZE;
}

int media(FilaCircular* fila) {
    int soma = 0, tam = 0;
    int i = fila->primeiro;
    while (i != fila->ultimo) {
        soma += fila->show[i]->release_year;
        tam++;
        i = (i + 1) % QUEUE_SIZE;
    }
    return tam > 0 ? soma / tam : 0;
}

Show* remover(FilaCircular* fila) {
    if (fila->primeiro == fila->ultimo) return NULL;
    Show* resp = fila->show[fila->primeiro];
    fila->show[fila->primeiro] = NULL;
    fila->primeiro = (fila->primeiro + 1) % QUEUE_SIZE;
    return resp;
}

void mostrar(FilaCircular* fila) {
    int i = fila->primeiro;
    while (i != fila->ultimo) {
        printf("[%d] ", i - 1);
        imprimir(fila->show[i]);
        i = (i + 1) % QUEUE_SIZE;
    }
}

void init_show(Show* s) {
    s->show_id[0] = '\0';
    s->type[0] = '\0';
    s->title[0] = '\0';
    for (int i = 0; i < MAX_ARRAY; i++) {
        s->director[i] = NULL;
        s->cast[i] = NULL;
        s->listed_in[i] = NULL;
    }
    s->country[0] = '\0';
    s->date_added = NULL;
    s->release_year = 0;
    s->rating[0] = '\0';
    s->duration[0] = '\0';
    s->director_count = 0;
    s->cast_count = 0;
    s->listed_in_count = 0;
}

Show* clone_show(Show* s) {
    Show* clonado = (Show*)malloc(sizeof(Show));
    init_show(clonado);
    
    set_id(clonado, s->show_id);
    set_type(clonado, s->type);
    set_title(clonado, s->title);
    set_director(clonado, s->director, s->director_count);
    set_cast(clonado, s->cast, s->cast_count);
    set_country(clonado, s->country);
    set_date_added(clonado, s->date_added);
    set_release_year(clonado, s->release_year);
    set_rating(clonado, s->rating);
    set_duration(clonado, s->duration);
    set_listed_in(clonado, s->listed_in, s->listed_in_count);
    
    return clonado;
}

void set_id(Show* s, const char* id) {
    strncpy(s->show_id, id, MAX_STR - 1);
    s->show_id[MAX_STR - 1] = '\0';
}

void set_type(Show* s, const char* type) {
    strncpy(s->type, type[0] == '\0' ? "NaN" : type, MAX_STR - 1);
    s->type[MAX_STR - 1] = '\0';
}

void set_title(Show* s, const char* title) {
    strncpy(s->title, title[0] == '\0' ? "NaN" : title, MAX_STR - 1);
    s->title[MAX_STR - 1] = '\0';
}

void set_director(Show* s, char** director, int count) {
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        if (director[i] && director[i][0] != '\0') {
            s->director[i] = strdup(director[i][0] == '\0' ? "NaN" : director[i]);
            s->director_count++;
        }
    }
}

void set_cast(Show* s, char** cast, int count) {
    char* temp[MAX_ARRAY];
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        temp[i] = cast[i] ? strdup(cast[i]) : NULL;
    }
    ordenar(temp, count);
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        if (temp[i]) {
            s->cast[i] = strdup(temp[i][0] == '\0' ? "NaN" : temp[i]);
            s->cast_count++;
            free(temp[i]);
        }
    }
}

void set_country(Show* s, const char* country) {
    strncpy(s->country, country[0] == '\0' ? "NaN" : country, MAX_STR - 1);
    s->country[MAX_STR - 1] = '\0';
}

void set_date_added(Show* s, struct tm* date) {
    if (date) {
        s->date_added = (struct tm*)malloc(sizeof(struct tm));
        *s->date_added = *date;
    } else {
        s->date_added = NULL;
    }
}

void set_release_year(Show* s, int year) {
    s->release_year = year;
}

void set_rating(Show* s, const char* rating) {
    strncpy(s->rating, rating[0] == '\0' ? "NaN" : rating, MAX_STR - 1);
    s->rating[MAX_STR - 1] = '\0';
}

void set_duration(Show* s, const char* duration) {
    strncpy(s->duration, duration[0] == '\0' ? "NaN" : duration, MAX_STR - 1);
    s->duration[MAX_STR - 1] = '\0';
}

void set_listed_in(Show* s, char** listed_in, int count) {
    char* temp[MAX_ARRAY];
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        temp[i] = listed_in[i] ? strdup(listed_in[i]) : NULL;
    }
    ordenar(temp, count);
    for (int i = 0; i < count && i < MAX_ARRAY; i++) {
        if (temp[i]) {
            s->listed_in[i] = strdup(temp[i][0] == '\0' ? "NaN" : temp[i]);
            s->listed_in_count++;
            free(temp[i]);
        }
    }
}

void leitura(Show* s, char* entrada) {
    char* partes[MAX_ARRAY];
    int parte_count = 0;
    bool dentroAspas = false;
    char atual[MAX_STR] = "";
    int atual_idx = 0;

    for (int i = 0; entrada[i] != '\0' && parte_count < MAX_ARRAY; i++) {
        if (entrada[i] == '"') {
            dentroAspas = !dentroAspas;
        } else if (entrada[i] == ',' && !dentroAspas) {
            atual[atual_idx] = '\0';
            partes[parte_count] = strdup(atual);
            parte_count++;
            atual_idx = 0;
            atual[0] = '\0';
        } else {
            atual[atual_idx++] = entrada[i];
        }
    }
    if (atual_idx > 0) {
        atual[atual_idx] = '\0';
        partes[parte_count] = strdup(atual);
        parte_count++;
    }

    set_id(s, partes[0]);
    set_type(s, partes[1]);
    set_title(s, partes[2]);

    char* directors[MAX_ARRAY];
    int dir_count = 0;
    char* token = strtok(partes[3], ",");
    while (token && dir_count < MAX_ARRAY) {
        directors[dir_count++] = token;
        token = strtok(NULL, ",");
    }
    set_director(s, directors, dir_count);

    char* casts[MAX_ARRAY];
    int cast_count = 0;
    token = strtok(partes[4], ",");
    while (token && cast_count < MAX_ARRAY) {
        casts[cast_count++] = token;
        token = strtok(NULL, ",");
    }
    set_cast(s, casts, cast_count);

    set_country(s, partes[5]);

    struct tm* date_added = NULL;
    if (partes[6][0] != '\0') {
        date_added = (struct tm*)malloc(sizeof(struct tm));
        strptime(partes[6], "%B %d, %Y", date_added);
    }
    set_date_added(s, date_added);

    set_release_year(s, atoi(partes[7]));
    set_rating(s, partes[8]);
    set_duration(s, partes[9]);

    char* listed_ins[MAX_ARRAY];
    int listed_count = 0;
    token = strtok(partes[10], ",");
    while (token && listed_count < MAX_ARRAY) {
        listed_ins[listed_count++] = token;
        token = strtok(NULL, ",");
    }
    set_listed_in(s, listed_ins, listed_count);

    for (int i = 0; i < parte_count; i++) {
        free(partes[i]);
    }
}

void ordenar(char** lista, int count) {
    for (int i = 0; i < count - 1; i++) {
        int menor = i;
        for (int j = i + 1; j < count; j++) {
            if (lista[j] && lista[menor] && strcmp(lista[menor], lista[j]) > 0) {
                menor = j;
            }
        }
        if (menor != i) {
            char* temp = lista[menor];
            lista[menor] = lista[i];
            lista[i] = temp;
        }
    }
}

void imprimir(Show* s) {
    printf("=> %s ## %s ## %s ## ", s->show_id, s->title, s->type);
    for (int i = 0; i < s->director_count; i++) {
        printf("%s%s", s->director[i], i < s->director_count - 1 ? ", " : " ## ");
    }
    printf("[");
    for (int i = 0; i < s->cast_count; i++) {
        printf("%s%s", s->cast[i], i < s->cast_count - 1 ? ", " : "");
    }
    printf("] ## %s ## ", s->country);
    
    if (s->date_added) {
        char date_str[50];
        strftime(date_str, sizeof(date_str), "%B %d, %Y", s->date_added);
        printf("%s ## ", date_str);
    } else {
        printf("NaN ## ");
    }
    
    printf("%d ## %s ## %s ## [", s->release_year, s->rating, s->duration);
    for (int i = 0; i < s->listed_in_count; i++) {
        printf("%s%s", s->listed_in[i], i < s->listed_in_count - 1 ? ", " : "");
    }
    printf("]\n");
}

Show* localizar(const char* id, Show* s, int index) {
    Show* tmp = (Show*)malloc(sizeof(Show));
    init_show(tmp);
    for (int i = 0; i < index; i++) {
        if (strcmp(id, s[i].show_id) == 0) {
            *tmp = s[i];
            break;
        }
    }
    return tmp;
}

int main() {
    Show shows[MAX_SHOWS];
    int index = 0;
    char entrada[MAX_STR];

    FILE* file = fopen("/tmp/disneyplus.csv", "r");
    if (!file) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }

    char linha[2048];
    fgets(linha, sizeof(linha), file);
    while (fgets(linha, sizeof(linha), file) && index < MAX_SHOWS) {
        linha[strcspn(linha, "\n")] = '\0';
        init_show(&shows[index]);
        leitura(&shows[index], linha);
        index++;
    }
    fclose(file);

    FilaCircular fila;
    init_fila(&fila);

    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0) break;

        file = fopen("/tmp/disneyplus.csv", "r");
        if (!file) {
            printf("Erro ao acessar o arquivo\n");
            continue;
        }

        fgets(linha, sizeof(linha), file);
        bool encontrado = false;
        while (fgets(linha, sizeof(linha), file)) {
            linha[strcspn(linha, "\n")] = '\0';
            if (strncmp(linha, entrada, strlen(entrada)) == 0 && linha[strlen(entrada)] == ',') {
                Show* tmp = (Show*)malloc(sizeof(Show));
                init_show(tmp);
                leitura(tmp, linha);
                inserir(&fila, tmp);
                printf("[Media] %d\n", media(&fila));
                free(tmp);
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
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = '\0';
        if (entrada[0] == 'I') {
            char* id = entrada + 2;
            Show* tmp = localizar(id, shows, index);
            inserir(&fila, tmp);
            printf("[Media] %d\n", media(&fila));
            free(tmp);
        } else if (entrada[0] == 'R') {
            Show* tmp = remover(&fila);
            if (tmp) {
                printf("(R) %s\n", tmp->title);
                for (int j = 0; j < tmp->director_count; j++) free(tmp->director[j]);
                for (int j = 0; j < tmp->cast_count; j++) free(tmp->cast[j]);
                for (int j = 0; j < tmp->listed_in_count; j++) free(tmp->listed_in[j]);
                if (tmp->date_added) free(tmp->date_added);
                free(tmp);
            }
        }
    }

    mostrar(&fila);

    for (int i = 0; i < index; i++) {
        for (int j = 0; j < shows[i].director_count; j++) free(shows[i].director[j]);
        for (int j = 0; j < shows[i].cast_count; j++) free(shows[i].cast[j]);
        for (int j = 0; j < shows[i].listed_in_count; j++) free(shows[i].listed_in[j]);
        if (shows[i].date_added) free(shows[i].date_added);
    }
    for (int i = 0; i < QUEUE_SIZE; i++) {
        if (fila.show[i]) {
            for (int j = 0; j < fila.show[i]->director_count; j++) free(fila.show[i]->director[j]);
            for (int j = 0; j < fila.show[i]->cast_count; j++) free(fila.show[i]->cast[j]);
            for (int j = 0; j < fila.show[i]->listed_in_count; j++) free(fila.show[i]->listed_in[j]);
            if (fila.show[i]->date_added) free(fila.show[i]->date_added);
            free(fila.show[i]);
        }
    }

    return 0;
}