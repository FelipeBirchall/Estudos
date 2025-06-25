#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
    char entrada[100];
    
    while (fgets(entrada, sizeof(entrada), stdin) != NULL)
    {
        
        if (strlen(entrada) == 1 && entrada[0] == '1')
        {
            fgets(entrada, sizeof(entrada), stdin);
            char packages[1000][100];
            int index = 0;
            while (strlen(entrada) != 1 && entrada[0] != '0')
            {
                for(int i = 0; i < strlen(entrada); i++){
                    packages[index][i] = entrada[i];
                }
                index++;
                fgets(entrada, sizeof(entrada), stdin);
            }
            // Selection sort
            for (int i = 0; i < index - 1; i++)
            {
                int menor = i;
                for (int j = i + 1; j < index; j++)
                {
                    if (strcmp(packages[j], packages[menor]) < 0)
                    {
                        menor = j;
                    }
                }
                char tmp[100];
                strcpy(tmp, packages[menor]);
                strcpy(packages[menor], packages[i]);
                strcpy(packages[i], tmp);
            }

            for (int i = 0; i < index; i++)
            {
                printf("%s", packages[i]);
            }
        }
   
    }
    return 0;
}
