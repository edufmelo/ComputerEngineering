#include <stdio.h>
#include <stdlib.h>

// malloc - aloca x bytes sem limpar
// calloc - aloca n * bytes limpando (coloca zero)
// realloc - realoca, aumentar ou diminuir
// free - libera o espaco
// qualquer um, se der um problema, devolve NULL

int main() {
    int i, j, linhas, colunas;
    int **p; // p um ponteiro para ponteiro de inteiro

    printf("\nQuantas linhas vc quer: ");
    scanf("%d", &linhas);
    printf("\nQuantas colunas vc quer: ");
    scanf("%d", &colunas);
    
	// alocar dinamicamente um espaco de linhas X colunas inteiros, sendo p um ponteiro para inteiro
    // alocando dinamicamente um matriz, array bidimensional, (linhas x colunas)
    p = (int **) malloc(linhas * sizeof(int *)); // alocando as linhas
    for (i=0; i<linhas; i++){
         //p[i] = (int *) malloc(colunas * sizeof(int)); // alocando as colunas com malloc (fica com o lixo que esta)
        p[i] = (int *) calloc(colunas, sizeof(int)); // alocando as colunas com calloc, zera a memoria
    }

    printf ("A base, posicao [0][0] esta no endereco: %X\n", p);
    for (i=0; i<linhas; i++){
        for (j=0; j<colunas; j++){
            printf("%d ", p[i][j]);
        }
        printf("\n");
    }

    // liberacao da matriz, libera primeiro as colunas de cada linha
    for (i=0; i<linhas; i++){
        free(p[i]);
    }
    free (p); // depois as linhas
    
    return 0;
}
