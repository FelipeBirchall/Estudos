#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_STR 256
#define MAX_ARRAY 300
#define MAX_SHOWS 1368

typedef struct {
    char* show_id;
    char* type;
    char* title;
    char** director;
    int director_count;
    char** cast;
    int cast_count;
    char* country;
    char* date_added;
    int release_year;
    char* rating;
    char* duration;
    char** listed_in;
    int listed_in_count;
} SHOW;

typedef struct {
    SHOW* show;
    int index;
} Pilha;

void init_show(SHOW* s) {
    s->show_id = NULL;
    s->type = NULL;
    s->title = NULL;
    s->director = NULL;
    s->director_count = 0;
    s->cast = NULL;
    s->cast_count = 0;
    s->country = NULL;
    s->date_added = NULL;
    s->release_year = 0;
    s->rating = NULL;
    s->duration = NULL;
    s->listed_in = NULL;
    s->listed_in_count = 0;
}

void free_show(SHOW* s) {
    if (s->show_id) free(s->show_id);
    if (s->type) free(s->type);
    if (s->title) free(s->title);
    if (s->director) {
        for (int i = 0; i < s->director_count; i++) {
            if (s->director[i]) free(s->director[i]);
        }
        free(s->director);
    }
    if (s->cast) {
        for (int i = 0; i < s->cast_count; i++) {
            if (s->cast[i]) free(s->cast[i]);
        }
        free(s->cast);
    }
    if (s->country) free(s->country);
    if (s->date_added) free(s->date_added);
    if (s->rating) free(s->rating);
    if (s->duration) free(s->duration);
    if (s->listed_in) {
        for (int i = 0; i < s->listed_in_count; i++) {
            if (s->listed_in[i]) free(s->listed_in[i]);
        }
        free(s->listed_in);
    }
}

void init_pilha(Pilha* p) {
    p->show = (SHOW*)malloc(MAX_ARRAY * sizeof(SHOW));
    for (int i = 0; i < MAX_ARRAY; i++) {
        init_show(&p->show[i]);
    }
    p->index = 0;
}

void free_pilha(Pilha* p) {
    for (int i = 0; i < p->index; i++) {
        free_show(&p->show[i]);
    }
    free(p->show);
}

void inserir(Pilha* p, SHOW s) {
    for (int i = p->index; i > 0; i--) {
        p->show[i] = p->show[i-1];
    }
    p->show[0] = s;
    p->index++;
}

SHOW remover(Pilha* p) {
    SHOW tmp = p->show[0];
    init_show(&p->show[0]);
    for (int i = 1; i < p->index; i++) {
        p->show[i-1] = p->show[i];
        init_show(&p->show[i]);
    }
    p->index--;
    return tmp;
}

void mostrar(Pilha* p) {
    int pos = p->index - 1;
    for (int i = 0; i < p->index; i++) {
        printf("[%d] => %s ## %s ## %s ## ", pos, p->show[i].show_id, p->show[i].title, p->show[i].type);
        for (int j = 0; j < p->show[i].director_count; j++) {
            printf("%s", p->show[i].director[j]);
            if (j < p->show[i].director_count - 1) printf(", ");
        }
        printf(" ## [");
        for (int j = 0; j < p->show[i].cast_count; j++) {
            printf("%s", p->show[i].cast[j]);
            if (j < p->show[i].cast_count - 1) printf(", ");
        }
        printf("] ## %s ## %s ## %d ## %s ## %s ## [", p->show[i].country,
               p->show[i].date_added ? p->show[i].date_added : "NaN",
               p->show[i].release_year, p->show[i].rating, p->show[i].duration);
        for (int j = 0; j < p->show[i].listed_in_count; j++) {
            printf("%s", p->show[i].listed_in[j]);
            if (j < p->show[i].listed_in_count - 1) printf(", ");
        }
        printf("] ##\n");
        pos--;
    }
}

char* strdup(const char* s) {
    char* d = (char*)malloc(strlen(s) + 1);
    if (d) strcpy(d, s);
    return d;
}

void set_id(SHOW* s, const char* id) {
    if (s->show_id) free(s->show_id);
    s->show_id = strdup(id);
}

void set_type(SHOW* s, const char* type) {
    if (s->type) free(s->type);
    s->type = strdup(type && strlen(type) > 0 ? type : "NaN");
}

void set_title(SHOW* s, const char* title) {
    if (s->title) free(s->title);
    s->title = strdup(title && strlen(title) > 0 ? title : "NaN");
}

void set_director(SHOW* s, char** director, int count) {
    if (s->director) {
        for (int i = 0; i < s->director_count; i++) {
            if (s->director[i]) free(s->director[i]);
        }
        free(s->director);
    }
    s->director_count = count;
    s->director = (char**)malloc(count * sizeof(char*));
    for (int i = 0; i < count; i++) {
        s->director[i] = strdup(director[i] && strlen(director[i]) > 0 ? director[i] : "NaN");
    }
}

void set_cast(SHOW* s, char** cast, int count) {
    if (s->cast) {
        for (int i = 0; i < s->cast_count; i++) {
            if (s->cast[i]) free(s->cast[i]);
        }
        free(s->cast);
    }
    s->cast_count = count;
    s->cast = (char**)malloc(count * sizeof(char*));
    for (int i = 0; i < count; i++) {
        s->cast[i] = strdup(cast[i] && strlen(cast[i]) > 0 ? cast[i] : "NaN");
    }
    for (int i = 0; i < count - 1; i++) {
        int min = i;
        for (int j = i + 1; j < count; j++) {
            if (strcmp(s->cast[min], s->cast[j]) > 0) {
                min = j;
            }
        }
        if (min != i) {
            char* temp = s->cast[i];
            s->cast[i] = s->cast[min];
            s->cast[min] = temp;
        }
    }
}

void set_country(SHOW* s, const char* country) {
    if (s->country) free(s->country);
    s->country = strdup(country && strlen(country) > 0 ? country : "NaN");
}

void set_date_added(SHOW* s, const char* date) {
    if (s->date_added) free(s->date_added);
    s->date_added = date && strlen(date) > 0 ? strdup(date) : NULL;
}

void set_release_year(SHOW* s, int year) {
    s->release_year = year;
}

void set_rating(SHOW* s, const char* rating) {
    if (s->rating) free(s->rating);
    s->rating = strdup(rating && strlen(rating) > 0 ? rating : "NaN");
}

void set_duration(SHOW* s, const char* duration) {
    if (s->duration) free(s->duration);
    s->duration = strdup(duration && strlen(duration) > 0 ? duration : "NaN");
}

void set_listed_in(SHOW* s, char** listed_in, int count) {
    if (s->listed_in) {
        for (int i = 0; i < s->listed_in_count; i++) {
            if (s->listed_in[i]) free(s->listed_in[i]);
        }
        free(s->listed_in);
    }
    s->listed_in_count = count;
    s->listed_in = (char**)malloc(count * sizeof(char*));
    for (int i = 0; i < count; i++) {
        s->listed_in[i] = strdup(listed_in[i] && strlen(listed_in[i]) > 0 ? listed_in[i] : "NaN");
    }
    for (int i = 0; i < count - 1; i++) {
        int min = i;
        for (int j = i + 1; j < count; j++) {
            if (strcmp(s->listed_in[min], s->listed_in[j]) > 0) {
                min = j;
            }
        }
        if (min != i) {
            char* temp = s->listed_in[i];
            s->listed_in[i] = s->listed_in[min];
            s->listed_in[min] = temp;
        }
    }
}

void clone_show(SHOW* dest, const SHOW* src) {
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

void leitura(SHOW* s, char* line) {
    char* partes[11];
    int parte_count = 0;
    bool dentro_aspas = false;
    char* atual = (char*)malloc(MAX_STR);
    int atual_idx = 0;

    for (int i = 0; line[i] != '\0' && parte_count < 11; i++) {
        if (line[i] == '"') {
            dentro_aspas = !dentro_aspas;
        } else if (line[i] == ',' && !dentro_aspas) {
            atual[atual_idx] = '\0';
            partes[parte_count] = strdup(atual);
            parte_count++;
            atual_idx = 0;
        } else {
            if (atual_idx < MAX_STR - 1) {
                atual[atual_idx++] = line[i];
            }
        }
    }
    atual[atual_idx] = '\0';
    partes[parte_count] = strdup(atual);
    parte_count++;
    free(atual);

    set_id(s, partes[0]);
    set_type(s, partes[1]);
    set_title(s, partes[2]);

    char* director_token = strtok(partes[3], ",");
    char* directors[50];
    int director_count = 0;
    while (director_token && director_count < 50) {
        while (*director_token && isspace(*director_token)) director_token++;
        directors[director_count++] = director_token;
        director_token = strtok(NULL, ",");
    }
    set_director(s, directors, director_count);

    char* cast_token = strtok(partes[4], ",");
    char* cast[50];
    int cast_count = 0;
    while (cast_token && cast_count < 50) {
        while (*cast_token && isspace(*cast_token)) cast_token++;
        cast[cast_count++] = cast_token;
        cast_token = strtok(NULL, ",");
    }
    set_cast(s, cast, cast_count);

    set_country(s, partes[5]);
    set_date_added(s, partes[6]);
    set_release_year(s, atoi(partes[7]));
    set_rating(s, partes[8]);
    set_duration(s, partes[9]);

    char* listed_in_token = strtok(partes[10], ",");
    char* listed_in[50];
    int listed_in_count = 0;
    while (listed_in_token && listed_in_count < 50) {
        while (*listed_in_token && isspace(*listed_in_token)) listed_in_token++;
        listed_in[listed_in_count++] = listed_in_token;
        listed_in_token = strtok(NULL, ",");
    }
    set_listed_in(s, listed_in, listed_in_count);

    for (int i = 0; i < parte_count; i++) {
        free(partes[i]);
    }
}

SHOW localizar(const char* id, SHOW* shows, int index) {
    SHOW tmp;
    init_show(&tmp);
    for (int i = 0; i < index; i++) {
        if (strcmp(id, shows[i].show_id) == 0) {
            clone_show(&tmp, &shows[i]);
            break;
        }
    }
    return tmp;
}

int main() {
    SHOW shows[MAX_SHOWS];
    int index = 0;

    FILE* fp = fopen("/tmp/disneyplus.csv", "r");
    if (!fp) {
        printf("Erro ao acessar o arquivo\n");
        return 1;
    }

    char line[2048];
    fgets(line, 2048, fp);
    while (fgets(line, 2048, fp) && index < MAX_SHOWS) {
        line[strcspn(line, "\n")] = 0;
        init_show(&shows[index]);
        leitura(&shows[index], line);
        index++;
    }
    fclose(fp);

    Pilha pilha;
    init_pilha(&pilha);

    char entrada[256];
    while (fgets(entrada, 256, stdin)) {
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        fp = fopen("/tmp/disneyplus.csv", "r");
        if (!fp) {
            printf("Erro ao acessar o arquivo\n");
            continue;
        }
        fgets(line, 2048, fp);
        bool encontrado = false;

        while (fgets(line, 2048, fp) && !encontrado) {
            line[strcspn(line, "\n")] = 0;
            if (strncmp(line, entrada, strlen(entrada)) == 0 && line[strlen(entrada)] == ',') {
                SHOW tmp;
                init_show(&tmp);
                leitura(&tmp, line);
                inserir(&pilha, tmp);
                encontrado = true;
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
        fgets(entrada, 256, stdin);
        entrada[strcspn(entrada, "\n")] = 0;
        if (entrada[0] == 'I') {
            char* id = entrada + 2;
            SHOW tmp = localizar(id, shows, index);
            if (tmp.show_id) {
                inserir(&pilha, tmp);
            }
        } else if (entrada[0] == 'R') {
            SHOW tmp = remover(&pilha);
            if (tmp.show_id) {
                printf("(R) %s\n", tmp.title);
                free_show(&tmp);
            }
        }
    }

    mostrar(&pilha);

    for (int i = 0; i < index; i++) {
        free_show(&shows[i]);
    }
    free_pilha(&pilha);

    return 0;
}