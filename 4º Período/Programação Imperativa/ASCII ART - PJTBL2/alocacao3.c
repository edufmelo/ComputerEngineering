#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// malloc - aloca x bytes sem limpar
// calloc - aloca n * bytes limpando (coloca zero)
// realloc - realoca, aumentar ou diminuir
// free - libera o espaco
// qualquer um, se der um problema, devolve NULL

typedef struct sPessoa {  
    char nome[50];  
    char email[50]; 
} tPessoa;

int main() {
    tPessoa ful = {"Fulano de Tal", "fulano@tal.com"};
    tPessoa *p; // um ponteiro para struct
    tPessoa familia[10]; // um vetor fixo de 10 pessoas
    int i;

    printf("Nome: %s\n", ful.nome);
    p = &ful; // p aponta para a mesma regiao ocupada pela varivael ful
    
	familia[0] = ful;
    printf("Nome: %s\n", (*p).nome);
    printf("Nome: %s\n", p->nome);
    printf("Nome: %s\n", familia[0].nome);

    p = (tPessoa *) malloc(10 * sizeof(tPessoa)); // alocando um espacoo de memoria contiguo com 10 x tamanho de pessoa
    // portanto, eh como um vetor de pessoas, p[0] eh a primeira pessoa e p[9] eh a ultima
    // testando o vetor de pessoas alocado dinamicamente
    for (i=0; i<10; i++){
        strcpy (p[i].nome, "teste");
        printf("Pessoa %d: %s\n", i, p[i].nome);
    }

    p = (tPessoa *) realloc(p, 5 * sizeof(tPessoa));
    for (i=0; i<5; i++){
        printf("Pessoa %d: %s\n", i, p[i].nome);
    }   
    
    p = (tPessoa *) realloc(p, 15 * sizeof(tPessoa));
    for (i=0; i<15; i++){
        printf("Pessoa %d: %s\n", i, p[i].nome);
    }   
    
    free (p); 

    return 0;
}
