#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

#define MAX_STR 256
#define MAX_ARRAY 50
#define MAX_LINE 2048

typedef struct {
    char SHOW_ID[MAX_STR];
    char TYPE[MAX_STR];
    char TITLE[MAX_STR];
    char* DIRECTOR[MAX_ARRAY];
    char* CAST[MAX_ARRAY];
    char COUNTRY[MAX_STR];
    struct tm DATE_ADDED;
    int has_date;
    int RELEASE_YEAR;
    char RATING[MAX_STR];
    char DURATION[MAX_STR];
    char* LISTED_IN[MAX_ARRAY];
    int director_count;
    int cast_count;
    int listed_in_count;
} SHOW;

typedef struct Celula {
    SHOW* show;
    struct Celula* prox;
} Celula;

typedef struct {
    Celula* primeiro;
    Celula* ultimo;
    int tam;
} FilaFlexivel;

SHOW* show_create();
void show_destroy(SHOW* s);
void show_clone(SHOW* dest, const SHOW* src);
void show_set_id(SHOW* s, const char* id);
void show_set_type(SHOW* s, const char* type);
void show_set_title(SHOW* s, const char* title);
void show_set_director(SHOW* s, char** director, int count);
void show_set_cast(SHOW* s, char** cast, int count);
void show_set_country(SHOW* s, const char* country);
void show_set_date_added(SHOW* s, const char* date_str);
void show_set_release_year(SHOW* s, int year);
void show_set_rating(SHOW* s, const char* rating);
void show_set_duration(SHOW* s, const char* duration);
void show_set_listed_in(SHOW* s, char** listed_in, int count);
void show_read(SHOW* s, const char* line);
void show_print(const SHOW* s);
void sort_string_array(char** arr, int size);
int get_release_year(const SHOW* s);

FilaFlexivel* queue_create();
void queue_destroy(FilaFlexivel* f);
void queue_insert(FilaFlexivel* f, SHOW* s);
SHOW* queue_remove(FilaFlexivel* f);
int queue_average(FilaFlexivel* f);
void queue_print(FilaFlexivel* f);
int queue_size(FilaFlexivel* f);

SHOW* find_show(const char* id, SHOW** shows, int size);

SHOW* show_create() {
    SHOW* s = (SHOW*)malloc(sizeof(SHOW));
    if (!s) return NULL;
    s->SHOW_ID[0] = '\0';
    s->TYPE[0] = '\0';
    s->TITLE[0] = '\0';
    s->COUNTRY[0] = '\0';
    s->RATING[0] = '\0';
    s->DURATION[0] = '\0';
    s->has_date = 0;
    s->RELEASE_YEAR = 0;
    s->director_count = 0;
    s->cast_count = 0;
    s->listed_in_count = 0;
    for (int i = 0; i < MAX_ARRAY; i++) {
        s->DIRECTOR[i] = NULL;
        s->CAST[i] = NULL;
        s->LISTED_IN[i] = NULL;
    }
    return s;
}

void show_destroy(SHOW* s) {
    if (!s) return;
    for (int i = 0; i < s->director_count; i++) free(s->DIRECTOR[i]);
    for (int i = 0; i < s->cast_count; i++) free(s->CAST[i]);
    for (int i = 0; i < s->listed_in_count; i++) free(s->LISTED_IN[i]);
    free(s);
}

void show_clone(SHOW* dest, const SHOW* src) {
    strcpy(dest->SHOW_ID, src->SHOW_ID);
    strcpy(dest->TYPE, src->TYPE);
    strcpy(dest->TITLE, src->TITLE);
    strcpy(dest->COUNTRY, src->COUNTRY);
    strcpy(dest->RATING, src->RATING);
    strcpy(dest->DURATION, src->DURATION);
    dest->RELEASE_YEAR = src->RELEASE_YEAR;
    dest->has_date = src->has_date;
    if (src->has_date) dest->DATE_ADDED = src->DATE_ADDED;
    for (int i = 0; i < src->director_count; i++) {
        dest->DIRECTOR[i] = strdup(src->DIRECTOR[i]);
    }
    dest->director_count = src->director_count;
    for (int i = 0; i < src->cast_count; i++) {
        dest->CAST[i] = strdup(src->CAST[i]);
    }
    dest->cast_count = src->cast_count;
    for (int i = 0; i < src->listed_in_count; i++) {
        dest->LISTED_IN[i] = strdup(src->LISTED_IN[i]);
    }
    dest->listed_in_count = src->listed_in_count;
}

void show_set_id(SHOW* s, const char* id) {
    strncpy(s->SHOW_ID, id ? id : "NaN", MAX_STR - 1);
    s->SHOW_ID[MAX_STR - 1] = '\0';
}

void show_set_type(SHOW* s, const char* type) {
    strncpy(s->TYPE, (type && strlen(type) > 0) ? type : "NaN", MAX_STR - 1);
    s->TYPE[MAX_STR - 1] = '\0';
}

void show_set_title(SHOW* s, const char* title) {
    strncpy(s->TITLE, (title && strlen(title) > 0) ? title : "NaN", MAX_STR - 1);
    s->TITLE[MAX_STR - 1] = '\0';
}

void show_set_director(SHOW* s, char** director, int count) {
    for (int i = 0; i < s->director_count; i++) free(s->DIRECTOR[i]);
    s->director_count = 0;
    if (!director || count == 0 || (count == 1 && strlen(director[0]) == 0)) {
        s->DIRECTOR[0] = strdup("NaN");
        s->director_count = 1;
    } else {
        for (int i = 0; i < count && i < MAX_ARRAY; i++) {
            char* trimmed = strdup(director[i]);
            if (trimmed) {
                char* start = trimmed;
                while (isspace(*start)) start++;
                char* end = trimmed + strlen(trimmed) - 1;
                while (end > start && isspace(*end)) *end-- = '\0';
                s->DIRECTOR[i] = strdup(start);
                free(trimmed);
                s->director_count++;
            }
        }
    }
}

void show_set_cast(SHOW* s, char** cast, int count) {
    for (int i = 0; i < s->cast_count; i++) free(s->CAST[i]);
    s->cast_count = 0;
    if (!cast || count == 0 || (count == 1 && strlen(cast[0]) == 0)) {
        s->CAST[0] = strdup("NaN");
        s->cast_count = 1;
    } else {
        for (int i = 0; i < count && i < MAX_ARRAY; i++) {
            char* trimmed = strdup(cast[i]);
            if (trimmed) {
                char* start = trimmed;
                while (isspace(*start)) start++;
                char* end = trimmed + strlen(trimmed) - 1;
                while (end > start && isspace(*end)) *end-- = '\0';
                s->CAST[i] = strdup(start);
                free(trimmed);
                s->cast_count++;
            }
        }
        sort_string_array(s->CAST, s->cast_count);
    }
}

void show_set_country(SHOW* s, const char* country) {
    strncpy(s->COUNTRY, (country && strlen(country) > 0) ? country : "NaN", MAX_STR - 1);
    s->COUNTRY[MAX_STR - 1] = '\0';
}

void show_set_date_added(SHOW* s, const char* date_str) {
    s->has_date = 0;
    if (date_str && strlen(date_str) > 0) {
        struct tm tm = {0};
        char* result = strptime(date_str, "%B %d, %Y", &tm);
        if (result && *result == '\0') {
            s->DATE_ADDED = tm;
            s->has_date = 1;
        }
    }
}

void show_set_release_year(SHOW* s, int year) {
    s->RELEASE_YEAR = year;
}

void show_set_rating(SHOW* s, const char* rating) {
    strncpy(s->RATING, (rating && strlen(rating) > 0) ? rating : "NaN", MAX_STR - 1);
    s->RATING[MAX_STR - 1] = '\0';
}

void show_set_duration(SHOW* s, const char* duration) {
    strncpy(s->DURATION, (duration && strlen(duration) > 0) ? duration : "NaN", MAX_STR - 1);
    s->DURATION[MAX_STR - 1] = '\0';
}

void show_set_listed_in(SHOW* s, char** listed_in, int count) {
    for (int i = 0; i < s->listed_in_count; i++) free(s->LISTED_IN[i]);
    s->listed_in_count = 0;
    if (!listed_in || count == 0 || (count == 1 && strlen(listed_in[0]) == 0)) {
        s->LISTED_IN[0] = strdup("NaN");
        s->listed_in_count = 1;
    } else {
        for (int i = 0; i < count && i < MAX_ARRAY; i++) {
            char* trimmed = strdup(listed_in[i]);
            if (trimmed) {
                char* start = trimmed;
                while (isspace(*start)) start++;
                char* end = trimmed + strlen(trimmed) - 1;
                while (end > start && isspace(*end)) *end-- = '\0';
                s->LISTED_IN[i] = strdup(start);
                free(trimmed);
                s->listed_in_count++;
            }
        }
        sort_string_array(s->LISTED_IN, s->listed_in_count);
    }
}

void sort_string_array(char** arr, int size) {
    if (size <= 1 || strcmp(arr[0], "NaN") == 0) return;
    for (int i = 0; i < size - 1; i++) {
        int min_idx = i;
        for (int j = i + 1; j < size; j++) {
            if (strcmp(arr[min_idx], arr[j]) > 0) {
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

void show_read(SHOW* s, const char* line) {
    char* parts[11] = {0};
    int part_count = 0;
    char* copy = strdup(line);
    if (!copy) return;
    char* token = copy;
    int in_quotes = 0;
    char* start = copy;

    for (int i = 0; copy[i]; i++) {
        if (copy[i] == '"') {
            in_quotes = !in_quotes;
        } else if (copy[i] == ',' && !in_quotes) {
            copy[i] = '\0';
            if (part_count < 11) {
                parts[part_count++] = start;
                start = copy + i + 1;
            }
        }
    }
    if (part_count < 11) parts[part_count++] = start;

    show_set_id(s, parts[0]);
    show_set_type(s, parts[1]);
    show_set_title(s, parts[2]);

    char* directors[MAX_ARRAY];
    int dir_count = 0;
    if (parts[3] && strlen(parts[3]) > 0) {
        char* dir = strtok(parts[3], ",");
        while (dir && dir_count < MAX_ARRAY) {
            directors[dir_count++] = dir;
            dir = strtok(NULL, ",");
        }
    } else {
        directors[0] = "";
        dir_count = 1;
    }
    show_set_director(s, directors, dir_count);

    char* cast[MAX_ARRAY];
    int cast_count = 0;
    if (parts[4] && strlen(parts[4]) > 0) {
        char* c = strtok(parts[4], ",");
        while (c && cast_count < MAX_ARRAY) {
            cast[cast_count++] = c;
            c = strtok(NULL, ",");
        }
    } else {
        cast[0] = "";
        cast_count = 1;
    }
    show_set_cast(s, cast, cast_count);

    show_set_country(s, parts[5]);
    show_set_date_added(s, parts[6]);
    show_set_release_year(s, parts[7] && strlen(parts[7]) > 0 ? atoi(parts[7]) : 0);
    show_set_rating(s, parts[8]);
    show_set_duration(s, parts[9]);

    char* listed_in[MAX_ARRAY];
    int listed_count = 0;
    if (parts[10] && strlen(parts[10]) > 0) {
        char* li = strtok(parts[10], ",");
        while (li && listed_count < MAX_ARRAY) {
            listed_in[listed_count++] = li;
            li = strtok(NULL, ",");
        }
    } else {
        listed_in[0] = "";
        listed_count = 1;
    }
    show_set_listed_in(s, listed_in, listed_count);

    free(copy);
}

void show_print(const SHOW* s) {
    printf("=> %s ## %s ## %s ## ", s->SHOW_ID, s->TITLE, s->TYPE);
    if (s->director_count == 1 && strcmp(s->DIRECTOR[0], "NaN") == 0) {
        printf("NaN ## ");
    } else {
        for (int i = 0; i < s->director_count; i++) {
            printf("%s%s", s->DIRECTOR[i], i < s->director_count - 1 ? ", " : " ## ");
        }
    }
    printf("[");
    if (s->cast_count == 1 && strcmp(s->CAST[0], "NaN") == 0) {
        printf("NaN");
    } else {
        for (int i = 0; i < s->cast_count; i++) {
            printf("%s%s", s->CAST[i], i < s->cast_count - 1 ? ", " : "");
        }
    }
    printf("] ## %s ## ", s->COUNTRY);
    if (s->has_date) {
        char date_str[20];
        strftime(date_str, sizeof(date_str), "%B %d, %Y", &s->DATE_ADDED);
        printf("%s ## ", date_str);
    } else {
        printf("NaN ## ");
    }
    printf("%d ## %s ## %s ## [", s->RELEASE_YEAR, s->RATING, s->DURATION);
    if (s->listed_in_count == 1 && strcmp(s->LISTED_IN[0], "NaN") == 0) {
        printf("NaN");
    } else {
        for (int i = 0; i < s->listed_in_count; i++) {
            printf("%s%s", s->LISTED_IN[i], i < s->listed_in_count - 1 ? ", " : "");
        }
    }
    printf("]\n");
}

int get_release_year(const SHOW* s) {
    return s->RELEASE_YEAR;
}

FilaFlexivel* queue_create() {
    FilaFlexivel* f = (FilaFlexivel*)malloc(sizeof(FilaFlexivel));
    if (!f) return NULL;
    f->primeiro = (Celula*)malloc(sizeof(Celula));
    if (!f->primeiro) {
        free(f);
        return NULL;
    }
    f->primeiro->show = NULL;
    f->primeiro->prox = NULL;
    f->ultimo = f->primeiro;
    f->tam = 0;
    return f;
}

void queue_destroy(FilaFlexivel* f) {
    if (!f) return;
    Celula* current = f->primeiro;
    while (current) {
        Celula* temp = current;
        current = current->prox;
        if (temp->show) show_destroy(temp->show);
        free(temp);
    }
    free(f);
}

void queue_insert(FilaFlexivel* f, SHOW* s) {
    if (!s) return;
    if (f->tam >= 5) {
        queue_remove(f);
    }
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    if (!nova) return;
    nova->show = show_create();
    if (!nova->show) {
        free(nova);
        return;
    }
    show_clone(nova->show, s);
    nova->prox = NULL;
    f->ultimo->prox = nova;
    f->ultimo = nova;
    f->tam++;
}

SHOW* queue_remove(FilaFlexivel* f) {
    if (!f->primeiro->prox) return NULL;
    Celula* temp = f->primeiro->prox;
    SHOW* result = temp->show;
    f->primeiro->prox = temp->prox;
    temp->show = NULL;
    free(temp);
    f->tam--;
    if (!f->primeiro->prox) f->ultimo = f->primeiro;
    return result;
}

int queue_average(FilaFlexivel* f) {
    if (f->tam == 0) return 0;
    int sum = 0;
    for (Celula* i = f->primeiro->prox; i; i = i->prox) {
        sum += get_release_year(i->show);
    }
    return sum / f->tam;
}

void queue_print(FilaFlexivel* f) {
    int index = 0;
    for (Celula* i = f->primeiro->prox; i; i = i->prox) {
        printf("[%i] " , index);
        index++;
        show_print(i->show);
    }
}

int queue_size(FilaFlexivel* f) {
    return f->tam;
}

SHOW* find_show(const char* id, SHOW** shows, int size) {
    for (int i = 0; i < size; i++) {
        if (strcmp(shows[i]->SHOW_ID, id) == 0) {
            return shows[i];
        }
    }
    return NULL;
}

int main() {
    SHOW* shows[1368];
    int index = 0;
    char line[MAX_LINE];

    FILE* fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }
    if (!fgets(line, MAX_LINE, fp)) {
        fclose(fp);
        return 1;
    }
    while (fgets(line, MAX_LINE, fp) && index < 1368) {
        line[strcspn(line, "\n")] = '\0';
        shows[index] = show_create();
        if (!shows[index]) {
            fclose(fp);
            return 1;
        }
        show_read(shows[index], line);
        index++;
    }
    fclose(fp);

    FilaFlexivel* fila = queue_create();
    if (!fila) {
        for (int i = 0; i < index; i++) show_destroy(shows[i]);
        return 1;
    }

    char entrada[MAX_STR];
    while (fgets(entrada, MAX_STR, stdin)) {
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0) break;
        SHOW* tmp = find_show(entrada, shows, index);
        if (tmp) {
            queue_insert(fila, tmp);
            printf("[Media] %d\n", queue_average(fila));
        } else {
            printf("Show ID %s não encontrado.\n", entrada);
        }
    }

    int n;
    if (scanf("%d", &n) != 1) {
        queue_destroy(fila);
        for (int i = 0; i < index; i++) show_destroy(shows[i]);
        return 1;
    }
    getchar();

    for (int i = 0; i < n; i++) {
        if (!fgets(entrada, MAX_STR, stdin)) break;
        entrada[strcspn(entrada, "\n")] = '\0';
        if (entrada[0] == 'I') {
            char* id = entrada + 2;
            SHOW* tmp = find_show(id, shows, index);
            if (tmp) {
                queue_insert(fila, tmp);
                printf("[Media] %d\n", queue_average(fila));
            } else {
                printf("Show ID %s não encontrado.\n", id);
            }
        } else if (entrada[0] == 'R') {
            SHOW* tmp = queue_remove(fila);
            if (tmp) {
                printf("(R) %s\n", tmp->TITLE);
                show_destroy(tmp);
            }
        }
    }

    queue_print(fila);
    queue_destroy(fila);
    for (int i = 0; i < index; i++) show_destroy(shows[i]);
    return 0;
}