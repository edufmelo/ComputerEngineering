#include <stdio.h>
#include <stdlib.h>

// struct p representar numero racional
typedef struct {
    int num;
    int den;
} NRac;

// funcao cria numero racional
NRac* cria_nrac(int num, int den) {
    if (den == 0) {
        printf("Denominador não pode ser zero.\n");
        exit(EXIT_FAILURE);
    }
    NRac *novo = (NRac*)malloc(sizeof(NRac));
    novo->num = num;
    novo->den = den;
    return novo;
}

// funcao para soma dois numeros racionais
NRac* soma_nrac(NRac* r1, NRac* r2) {
    int num = r1->num * r2->den + r2->num * r1->den;
    int den = r1->den * r2->den;
    return cria_nrac(num, den);
}

// funcao para subt dois numeros racionais
NRac* subtrai_nrac(NRac* r1, NRac* r2) {
    int num = r1->num * r2->den - r2->num * r1->den;
    int den = r1->den * r2->den;
    return cria_nrac(num, den);
}

// funcao para mult dois numeros racionais
NRac* multiplica_nrac(NRac* r1, NRac* r2) {
    int num = r1->num * r2->num;
    int den = r1->den * r2->den;
    return cria_nrac(num, den);
}

// funcao para div dois numeros racionais
NRac* divide_nrac(NRac* r1, NRac* r2) {
    int num = r1->num * r2->den;
    int den = r1->den * r2->num;
    if (den == 0) {
        printf("ERRO! Não é possível realizar divisão por zero.\n");
        exit(EXIT_FAILURE);
    }
    return cria_nrac(num, den);
}

// funcao para calcular o mdc
int mdc(int a, int b) {
    while (b != 0) {
        int resto = a % b;
        a = b;
        b = resto;
    }
    return a;
}

// funcao para simplificar um numero racional
void simplifica(NRac* r) {
    int divisor = mdc(r->num, r->den);
    r->num /= divisor;
    r->den /= divisor;

    if (r->den < 0) {
        r->num = -r->num;
        r->den = -r->den;
    }
}

// funcao para imprimir um numero racional
void imprime_nrac(NRac* r) {
    printf("%d/%d", r->num, r->den);
}

int main() {
    NRac *q1 = cria_nrac(6, 2);
    NRac *q2 = cria_nrac(2, 1);

    NRac *soma = soma_nrac(q1, q2);
    NRac *subtracao = subtrai_nrac(q1, q2);
    NRac *mult = multiplica_nrac(q1, q2);
    NRac *divisao = divide_nrac(q1, q2);

    printf("----- Operacoes nao simplificadas -----\n\n");
    printf("Q1 = ");
    imprime_nrac(q1);
    printf(";\nQ2 = ");
    imprime_nrac(q2);
    printf(";\n\n");
    printf("Q1 + Q2 = ");
    imprime_nrac(soma);
    printf("\nQ1 - Q2 = ");
    imprime_nrac(subtracao);
    printf("\nQ1 * Q2 = ");
    imprime_nrac(mult);
    printf("\nQ1 / Q2 = ");
    imprime_nrac(divisao);
    printf("\n");

    simplifica(q1);
    simplifica(q2);
    simplifica(soma);
    simplifica(subtracao);
    simplifica(mult);
    simplifica(divisao);

    printf("\n----- Operacoes simplificadas -----\n\n");
    printf("Q1 = ");
    imprime_nrac(q1);
    printf(" (simplificado);\nQ2 = ");
    imprime_nrac(q2);
    printf(" (simplificado);\n\n");
    printf("Q1 + Q2 = ");
    imprime_nrac(soma);
    printf(" (simplificado)\nQ1 - Q2 = ");
    imprime_nrac(subtracao);
    printf(" (simplificado)\nQ1 * Q2 = ");
    imprime_nrac(mult);
    printf(" (simplificado)\nQ1 / Q2 = ");
    imprime_nrac(divisao);
    printf(" (simplificado)\n");

    // liberando memoria
    free(q1);
    free(q2);
    free(soma);
    free(subtracao);
    free(mult);
    free(divisao);

    return 0;
}

