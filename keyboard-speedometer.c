//Feito por: Said

#include <time.h>
#include <conio.h>
#include <stdio.h>

#define MAX_LIN 25
#define MAX_COL 25

typedef enum{false, true} bool;

int main(int argc, char *argv[]){
    double r;
    char c, tab[MAX_LIN][MAX_COL];
    int t = 0, _t = 0, n = 0, _n = 0, x;

    for(int i = 0; i < MAX_LIN; i++){
        for(int j = 0; j < MAX_COL; j++){
            tab[i][j] = ' ';
        }
    }

    while(true){
        if(kbhit() == true){
            getch();
            n++;
        }

        t = clock();
        if(t - _t >= CLOCKS_PER_SEC){
            r = (((double) n) - ((double) _n));

            x = t/CLOCKS_PER_SEC;
            if(x > MAX_COL/2){
                x = MAX_COL/2;
                for(int i = 0; i < MAX_LIN; i++){
                    for(int j = 0; j < MAX_COL; j++){
                        if(tab[i][j] == '*' && j == 0){
                            tab[i][j] = ' ';
                        }else if(tab[i][j] == '*' && tab[i][j-1] == ' '){
                            tab[i][j] = ' ';
                            tab[i][j-1] = '*';
                        }
                    }
                }
            }

            tab[(24 - ((int) (r/1.6)))][x] = '*';

            system("cls");

            for(int i = 0; i < MAX_LIN; i++){
                for(int j = 0; j < MAX_COL; j++){
                    printf("%c|", tab[i][j]);
                }
                printf("\n");
            }

            printf("Velociade = %.2lf teclas/s \n", r);

            _n = n;
            _t = t;
        }
    }

    return 0;
}
