#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LINE 4096
#define MAX_ARRAY 100
#define MAX_STR 256

typedef struct {
    char show_id[MAX_STR];
    char type[MAX_STR];
    char title[MAX_STR];
    char** director;
    int director_count;
    char** cast;
    int cast_count;
    char country[MAX_STR];
    char date_added[MAX_STR];
    int release_year;
    char rating[MAX_STR];
    char duration[MAX_STR];
    char** listed_in;
    int listed_in_count;
} SHOW;

typedef struct {
    SHOW* shows;
    int index;
    int capacity;
} Lista;

void init_show(SHOW* s);
void free_show(SHOW* s);
void copy_show(SHOW* dest, const SHOW* src);
void set_id(SHOW* s, const char* id);
void set_type(SHOW* s, const char* type);
void set_title(SHOW* s, const char* title);
void set_director(SHOW* s, char** director, int count);
void set_cast(SHOW* s, char** cast, int count);
void set_country(SHOW* s, const char* country);
void set_date_added(SHOW* s, const char* date);
void set_release_year(SHOW* s, int year);
void set_rating(SHOW* s, const char* rating);
void set_duration(SHOW* s, const char* duration);
void set_listed_in(SHOW* s, char** listed_in, int count);
void print_director(const SHOW* s);
void print_cast(const SHOW* s);
void print_listed_in(const SHOW* s);
void read_show(SHOW* s, const char* line);
void sort_array(char** arr, int size);
void print_show(const SHOW* s);

void init_lista(Lista* l);
void free_lista(Lista* l);
void inserir_inicio(Lista* l, const SHOW* s);
void inserir_fim(Lista* l, const SHOW* s);
void inserir(Lista* l, const SHOW* s, int pos);
bool remover_inicio(Lista* l, SHOW* result);
bool remover_fim(Lista* l, SHOW* result);
bool remover(Lista* l, int pos, SHOW* result);
void mostrar(const Lista* l);

SHOW* localizar(const char* id, SHOW* shows, int index);

void init_show(SHOW* s) {
    s->show_id[0] = '\0';
    s->type[0] = '\0';
    s->title[0] = '\0';
    s->director = NULL;
    s->director_count = 0;
    s->cast = NULL;
    s->cast_count = 0;
    s->country[0] = '\0';
    s->date_added[0] = '\0';
    s->release_year = 0;
    s->rating[0] = '\0';
    s->duration[0] = '\0';
    s->listed_in = NULL;
    s->listed_in_count = 0;
}

void free_show(SHOW* s) {
    if (s->director) {
        for (int i = 0; i < s->director_count; i++) free(s->director[i]);
        free(s->director);
    }
    if (s->cast) {
        for (int i = 0; i < s->cast_count; i++) free(s->cast[i]);
        free(s->cast);
    }
    if (s->listed_in) {
        for (int i = 0; i < s->listed_in_count; i++) free(s->listed_in[i]);
        free(s->listed_in);
    }
    init_show(s);
}

void copy_show(SHOW* dest, const SHOW* src) {
    free_show(dest);
    init_show(dest);
    set_id(dest, src->show_id);
    set_type(dest, src->type);
    set_title(dest, src->title);
    set_director(dest, src->director, src->director_count);
    set_cast(dest, src->cast, src->cast_count);
    set_country(dest, src->country);
    set_date_added(dest, src->date_added);
    set_release_year(dest, src->release_year);
    set_rating(dest, src->rating);
    set_duration(dest, src->duration);
    set_listed_in(dest, src->listed_in, src->listed_in_count);
}

void set_id(SHOW* s, const char* id) {
    strncpy(s->show_id, id, MAX_STR - 1);
    s->show_id[MAX_STR - 1] = '\0';
}

void set_type(SHOW* s, const char* type) {
    strncpy(s->type, type[0] ? type : "NaN", MAX_STR - 1);
    s->type[MAX_STR - 1] = '\0';
}

void set_title(SHOW* s, const char* title) {
    strncpy(s->title, title[0] ? title : "NaN", MAX_STR - 1);
    s->title[MAX_STR - 1] = '\0';
}

void set_director(SHOW* s, char** director, int count) {
    if (s->director) {
        for (int i = 0; i < s->director_count; i++) free(s->director[i]);
        free(s->director);
    }
    s->director_count = count;
    s->director = malloc(count * sizeof(char*));
    if (!s->director) {
        fprintf(stderr, "Memory allocation failed for director\n");
        exit(1);
    }
    for (int i = 0; i < count; i++) {
        s->director[i] = strdup(director[i][0] ? director[i] : "NaN");
        if (!s->director[i]) {
            fprintf(stderr, "Memory allocation failed for director[%d]\n", i);
            exit(1);
        }
        char* p = s->director[i];
        while (*p == ' ') p++;
        memmove(s->director[i], p, strlen(p) + 1);
        p = s->director[i] + strlen(s->director[i]) - 1;
        while (p >= s->director[i] && *p == ' ') *p-- = '\0';
    }
}

void set_cast(SHOW* s, char** cast, int count) {
    if (s->cast) {
        for (int i = 0; i < s->cast_count; i++) free(s->cast[i]);
        free(s->cast);
    }
    s->cast_count = count;
    s->cast = malloc(count * sizeof(char*));
    if (!s->cast) {
        fprintf(stderr, "Memory allocation failed for cast\n");
        exit(1);
    }
    for (int i = 0; i < count; i++) {
        s->cast[i] = strdup(cast[i][0] ? cast[i] : "NaN");
        if (!s->cast[i]) {
            fprintf(stderr, "Memory allocation failed for cast[%d]\n", i);
            exit(1);
        }
        char* p = s->cast[i];
        while (*p == ' ') p++;
        memmove(s->cast[i], p, strlen(p) + 1);
        p = s->cast[i] + strlen(s->cast[i]) - 1;
        while (p >= s->cast[i] && *p == ' ') *p-- = '\0';
    }
    sort_array(s->cast, s->cast_count);
}

void set_country(SHOW* s, const char* country) {
    strncpy(s->country, country[0] ? country : "NaN", MAX_STR - 1);
    s->country[MAX_STR - 1] = '\0';
}

void set_date_added(SHOW* s, const char* date) {
    strncpy(s->date_added, date[0] ? date : "NaN", MAX_STR - 1);
    s->date_added[MAX_STR - 1] = '\0';
}

void set_release_year(SHOW* s, int year) {
    s->release_year = year;
}

void set_rating(SHOW* s, const char* rating) {
    strncpy(s->rating, rating[0] ? rating : "NaN", MAX_STR - 1);
    s->rating[MAX_STR - 1] = '\0';
}

void set_duration(SHOW* s, const char* duration) {
    strncpy(s->duration, duration[0] ? duration : "NaN", MAX_STR - 1);
    s->duration[MAX_STR - 1] = '\0';
}

void set_listed_in(SHOW* s, char** listed_in, int count) {
    if (s->listed_in) {
        for (int i = 0; i < s->listed_in_count; i++) free(s->listed_in[i]);
        free(s->listed_in);
    }
    s->listed_in_count = count;
    s->listed_in = malloc(count * sizeof(char*));
    if (!s->listed_in) {
        fprintf(stderr, "Memory allocation failed for listed_in\n");
        exit(1);
    }
    for (int i = 0; i < count; i++) {
        s->listed_in[i] = strdup(listed_in[i][0] ? listed_in[i] : "NaN");
        if (!s->listed_in[i]) {
            fprintf(stderr, "Memory allocation failed for listed_in[%d]\n", i);
            exit(1);
        }
        char* p = s->listed_in[i];
        while (*p == ' ') p++;
        memmove(s->listed_in[i], p, strlen(p) + 1);
        p = s->listed_in[i] + strlen(s->listed_in[i]) - 1;
        while (p >= s->listed_in[i] && *p == ' ') *p-- = '\0';
    }
    sort_array(s->listed_in, s->listed_in_count);
}

void print_director(const SHOW* s) {
    if (s->director_count == 0) {
        printf("NaN ## ");
        return;
    }
    for (int i = 0; i < s->director_count - 1; i++) {
        printf("%s, ", s->director[i]);
    }
    printf("%s ## ", s->director[s->director_count - 1]);
}

void print_cast(const SHOW* s) {
    if (s->cast_count == 0) {
        printf("[NaN] ## ");
        return;
    }
    printf("[");
    for (int i = 0; i < s->cast_count - 1; i++) {
        printf("%s, ", s->cast[i]);
    }
    printf("%s] ## ", s->cast[s->cast_count - 1]);
}

void print_listed_in(const SHOW* s) {
    if (s->listed_in_count == 0) {
        printf("[NaN] ##");
        return;
    }
    printf("[");
    for (int i = 0; i < s->listed_in_count - 1; i++) {
        printf("%s, ", s->listed_in[i]);
    }
    printf("%s] ##", s->listed_in[s->listed_in_count - 1]);
}

void sort_array(char** arr, int size) {
    if (size <= 1) return;
    for (int i = 0; i < size - 1; i++) {
        int min = i;
        for (int j = i + 1; j < size; j++) {
            if (strcmp(arr[min], arr[j]) > 0) {
                min = j;
            }
        }
        if (min != i) {
            char* temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
}

void read_show(SHOW* s, const char* line) {
    char* parts[11];
    int part_count = 0;
    bool in_quotes = false;
    char* current = malloc(MAX_LINE);
    if (!current) {
        fprintf(stderr, "Memory allocation failed in read_show\n");
        exit(1);
    }
    int current_len = 0;

    for (int i = 0; line[i] && part_count < 11; i++) {
        if (line[i] == '"') {
            in_quotes = !in_quotes;
        } else if (line[i] == ',' && !in_quotes) {
            current[current_len] = '\0';
            parts[part_count] = strdup(current);
            if (!parts[part_count]) {
                fprintf(stderr, "Memory allocation failed for parts[%d]\n", part_count);
                exit(1);
            }
            part_count++;
            current_len = 0;
        } else {
            current[current_len++] = line[i];
        }
    }
    current[current_len] = '\0';
    parts[part_count] = strdup(current);
    if (!parts[part_count]) {
        fprintf(stderr, "Memory allocation failed for parts[%d]\n", part_count);
        exit(1);
    }
    part_count++;
    free(current);

    set_id(s, parts[0]);
    set_type(s, parts[1]);
    set_title(s, parts[2]);

    char* director_token = strtok(parts[3], ",");
    char* directors[MAX_ARRAY];
    int dir_count = 0;
    while (director_token && dir_count < MAX_ARRAY) {
        directors[dir_count++] = strdup(director_token);
        if (!directors[dir_count-1]) {
            fprintf(stderr, "Memory allocation failed for director token\n");
            exit(1);
        }
        director_token = strtok(NULL, ",");
    }
    set_director(s, directors, dir_count);
    for (int i = 0; i < dir_count; i++) free(directors[i]);

    char* cast_token = strtok(parts[4], ",");
    char* casts[MAX_ARRAY];
    int cast_count = 0;
    while (cast_token && cast_count < MAX_ARRAY) {
        casts[cast_count++] = strdup(cast_token);
        if (!casts[cast_count-1]) {
            fprintf(stderr, "Memory allocation failed for cast token\n");
            exit(1);
        }
        cast_token = strtok(NULL, ",");
    }
    set_cast(s, casts, cast_count);
    for (int i = 0; i < cast_count; i++) free(casts[i]);

    set_country(s, parts[5]);
    set_date_added(s, parts[6]);
    set_release_year(s, atoi(parts[7]));
    set_rating(s, parts[8]);
    set_duration(s, parts[9]);

    char* listed_token = strtok(parts[10], ",");
    char* listed[MAX_ARRAY];
    int listed_count = 0;
    while (listed_token && listed_count < MAX_ARRAY) {
        listed[listed_count++] = strdup(listed_token);
        if (!listed[listed_count-1]) {
            fprintf(stderr, "Memory allocation failed for listed_in token\n");
            exit(1);
        }
        listed_token = strtok(NULL, ",");
    }
    set_listed_in(s, listed, listed_count);
    for (int i = 0; i < listed_count; i++) free(listed[i]);

    for (int i = 0; i < part_count; i++) free(parts[i]);
}

void print_show(const SHOW* s) {
    printf("=> %s ## %s ## %s ## ", s->show_id, s->title, s->type);
    print_director(s);
    print_cast(s);
    printf("%s ## %s ## %d ## %s ## %s ## ", s->country, s->date_added, s->release_year, s->rating, s->duration);
    print_listed_in(s);
    printf("\n");
}

void init_lista(Lista* l) {
    l->capacity = 100;
    l->shows = malloc(l->capacity * sizeof(SHOW));
    if (!l->shows) {
        fprintf(stderr, "Memory allocation failed for Lista\n");
        exit(1);
    }
    l->index = 0;
    for (int i = 0; i < l->capacity; i++) {
        init_show(&l->shows[i]);
    }
}

void free_lista(Lista* l) {
    for (int i = 0; i < l->index; i++) {
        free_show(&l->shows[i]);
    }
    free(l->shows);
    l->shows = NULL;
    l->index = 0;
    l->capacity = 0;
}

void inserir_inicio(Lista* l, const SHOW* s) {
    if (l->index >= l->capacity) return;
    for (int i = l->index; i > 0; i--) {
        copy_show(&l->shows[i], &l->shows[i-1]);
    }
    copy_show(&l->shows[0], s);
    l->index++;
}

void inserir_fim(Lista* l, const SHOW* s) {
    if (l->index >= l->capacity) return;
    copy_show(&l->shows[l->index], s);
    l->index++;
}

void inserir(Lista* l, const SHOW* s, int pos) {
    if (l->index >= l->capacity || pos > l->index) return;
    for (int i = l->index; i > pos; i--) {
        copy_show(&l->shows[i], &l->shows[i-1]);
    }
    copy_show(&l->shows[pos], s);
    l->index++;
}

bool remover_inicio(Lista* l, SHOW* result) {
    if (l->index == 0) return false;
    copy_show(result, &l->shows[0]);
    for (int i = 1; i < l->index; i++) {
        copy_show(&l->shows[i-1], &l->shows[i]);
    }
    free_show(&l->shows[l->index-1]);
    l->index--;
    return true;
}

bool remover_fim(Lista* l, SHOW* result) {
    if (l->index == 0) return false;
    copy_show(result, &l->shows[l->index-1]);
    free_show(&l->shows[l->index-1]);
    l->index--;
    return true;
}

bool remover(Lista* l, int pos, SHOW* result) {
    if (l->index == 0 || pos >= l->index) return false;
    copy_show(result, &l->shows[pos]);
    for (int i = pos + 1; i < l->index; i++) {
        copy_show(&l->shows[i-1], &l->shows[i]);
    }
    free_show(&l->shows[l->index-1]);
    l->index--;
    return true;
}

void mostrar(const Lista* l) {
    for (int i = 0; i < l->index; i++) {
        print_show(&l->shows[i]);
    }
}

SHOW* localizar(const char* id, SHOW* shows, int index) {
    for (int i = 0; i < index; i++) {
        if (strcmp(id, shows[i].show_id) == 0) {
            SHOW* tmp = malloc(sizeof(SHOW));
            if (!tmp) {
                fprintf(stderr, "Memory allocation failed in localizar\n");
                exit(1);
            }
            init_show(tmp);
            copy_show(tmp, &shows[i]);
            return tmp;
        }
    }
    return NULL;
}

int main() {
    SHOW shows[1368];
    int index = 0;

    FILE* fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }
    char line[MAX_LINE];
    fgets(line, MAX_LINE, fp);
    while (fgets(line, MAX_LINE, fp) && index < 1368) {
        line[strcspn(line, "\n")] = '\0';
        init_show(&shows[index]);
        read_show(&shows[index], line);
        index++;
    }
    fclose(fp);

    Lista lista;
    init_lista(&lista);

    char entrada[MAX_STR];
    while (fgets(entrada, MAX_STR, stdin)) {
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0) break;

        bool encontrado = false;
        fp = fopen("/tmp/disneyplus.csv", "r");
        if (!fp) {
            printf("Erro ao acessar o arquivo\n");
            continue;
        }
        fgets(line, MAX_LINE, fp);
        while (fgets(line, MAX_LINE, fp)) {
            line[strcspn(line, "\n")] = '\0';
            if (strncmp(line, entrada, strlen(entrada)) == 0 && line[strlen(entrada)] == ',') {
                SHOW tmp;
                init_show(&tmp);
                read_show(&tmp, line);
                inserir_fim(&lista, &tmp);
                free_show(&tmp);
                encontrado = true;
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
        fgets(entrada, MAX_STR, stdin);
        entrada[strcspn(entrada, "\n")] = '\0';
        char* partes[3];
        int part_count = 0;
        char* token = strtok(entrada, " ");
        while (token && part_count < 3) {
            partes[part_count++] = token;
            token = strtok(NULL, " ");
        }

        if (strcmp(partes[0], "II") == 0) {
            SHOW* tmp = localizar(partes[1], shows, index);
            if (tmp) {
                inserir_inicio(&lista, tmp);
                free_show(tmp);
                free(tmp);
            }
        } else if (strcmp(partes[0], "IF") == 0) {
            SHOW* tmp = localizar(partes[1], shows, index);
            if (tmp) {
                inserir_fim(&lista, tmp);
                free_show(tmp);
                free(tmp);
            }
        } else if (strcmp(partes[0], "I*") == 0) {
            int pos = atoi(partes[1]);
            SHOW* tmp = localizar(partes[2], shows, index);
            if (tmp) {
                inserir(&lista, tmp, pos);
                free_show(tmp);
                free(tmp);
            }
        } else if (strcmp(partes[0], "RI") == 0) {
            SHOW tmp;
            init_show(&tmp);
            if (remover_inicio(&lista, &tmp)) {
                printf("(R) %s\n", tmp.title);
                free_show(&tmp);
            }
        } else if (strcmp(partes[0], "RF") == 0) {
            SHOW tmp;
            init_show(&tmp);
            if (remover_fim(&lista, &tmp)) {
                printf("(R) %s\n", tmp.title);
                free_show(&tmp);
            }
        } else if (strcmp(partes[0], "R*") == 0) {
            int pos = atoi(partes[1]);
            SHOW tmp;
            init_show(&tmp);
            if (remover(&lista, pos, &tmp)) {
                printf("(R) %s\n", tmp.title);
                free_show(&tmp);
            }
        }
    }

    mostrar(&lista);
    free_lista(&lista);
    for (int i = 0; i < index; i++) {
        free_show(&shows[i]);
    }

    return 0;
}