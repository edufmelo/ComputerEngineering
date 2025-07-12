#include <stdio.h>
#include <stdlib.h>

// malloc - aloca x bytes sem limpar
// calloc - aloca n * bytes limpando (coloca zero)
// realloc - realoca, aumentar ou diminuir
// free - libera o espaco
// qualquer um, se der um problema, devolve NULL

int main() {
    int i, tam;
    int *p; 

    printf("\n\nQuanto inteiros vc quer: ");
    scanf("%d", &tam);

    // alocar dinamicamente um espaco de tam inteiro, sendo p um ponteiro para inteiro
    p = (int *) malloc(tam * sizeof(int)); // malloc aloca um total de tam*4 bytes contíguos em memoria
    if (p == NULL) {
        printf("Oops, erro de alocacao!");
        exit(1);
    }

    printf("\nP aponta para %X:\n", p); // malloc devolve um ponteiro para o primeiro byte
    printf("Espaco alocado para os inteiros:\n");
    for (i=0; i<tam; i++){
        if (i%100 == 0) {
            printf ("\n\n%d =>\n", i);
        }
        printf ("%d ", p[i]);  // ponteiro como vetor, ou seja, p[i] => *(p+i)
    }
    
	free(p); // libera o espaco alocado
    
    return 0;
}
