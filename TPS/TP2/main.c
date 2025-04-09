#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct
{
    char data[100];
    
}Date;


typedef struct
{
     char SHOW_ID[10];
     char TYPE[100];
     char TITLE[200];
     char DIRECTOR[500];
     char CAST[500];
     char COUNTRY[100];
     Date DATE_ADDED;
     int RELEASE_YEAR;
     char RATING[100];
     char DURATION[100];
     char LISTED_IN[500];  
}SHOW;




int main()
{

    

    return 0;
}