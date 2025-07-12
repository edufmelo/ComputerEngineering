#include <stdio.h>
#include <stdlib.h>

// *****  incluir uma struct pixel com campo r g b

// em linhas gerais, para manipular arquivos: abre, utiliza (le ou grava), fecha
// o C permite efetuar estas operacoes usande caracteres (modo texto) ou bytes (modo binaio)
// o programador que cria as funcoes de manipulacao conforme seu modelo/necessidade/estrutura do arquivo

int main() {
	// o nome do arquivo sera obtido mediante linha de comando
	char arq[] = "dog.ppm";  // nome do arquivo com caminho relativo (imagem esta no memsmo diretorio do programa)
    
	// para caminho absulto, colocar o path completo
    // char arq[] = "C:\\Users\\Fulano\\Documents\\ppm\\05.ppm"
    // para representar a barra na string devemos colocar duas barras

    FILE *fp;  // ponteiro para arquivo

    char id[3];
    int colunas, linhas, maximo;
    unsigned char r, g, b;     // variáveis para RGB precisa ser unsigned char
    unsigned char rf, gf, bf;  // sem sinal, pois variam de 0 a 255 (256 combinacoes = 2^8)
    int i, j, k;
    int posicao;

    fp = fopen(arq, "rb"); // abrir arquivo para r - Read no modo b - Binario
    if (fp == NULL) {  // retorna NULL se deu algum problema na abertura
        printf("Erro na abertura do arquivo <%s>", arq);
        exit(EXIT_FAILURE);  // sai com codigo de falha
    }

	// lendo as informacoes iniciais do arquivo ppm
    fscanf (fp, "%s", id);        // identificador do formato PPM - P6
    fscanf (fp, "%d", &colunas);  // largura da imagem = total de colunas
    fscanf (fp, "%d", &linhas);   // altura da imagem = total de linhas
    fscanf (fp, "%d", &maximo);   // maximo de intensidade (neste trabalho, sempre 256)
    fscanf (fp, "%c", &r);        // consumir um quebra de linha no arquivo

    printf("Tipo do arquivo <%s>: %s\n", arq, id);
    printf("Maximo: %d\n", maximo);
    printf("Imagem tem %d x %d pixels\n", linhas, colunas);

	// 3 linhas apenas para ler o 1o. pixel das imagens manipuladas (fundo é sempre igual)
    posicao = ftell(fp); // pegar a posicao fisica no arquivo anter de ler dados da imagem
    fscanf (fp, "%c%c%c", &rf, &gf, &bf);
    fseek(fp, posicao, SEEK_SET); // voltando ao inicio da imagem
    // retirar esta parte
    
    // ****** precisa criar uma matriz dinamica com linhas x colunas do tipo pixel
	// ver aloc2 linhas 21 a 25 e aloc 3 para struct
	// **** no for duplo abaixo, ler o r g b para os campos da matriz de struct
	
    for (i=0; i<linhas; i++) {
        for (j=0; j<colunas; j++) {
            fscanf (fp, "%c%c%c", &r, &g, &b);
            // ----------------------------------
            if ((r!=rf) || (g!=gf)  || (b!=bf) ){ 
                printf("x");
            } else {  
                printf("_");
            }
            // ----------------------------------
        }
        printf("\n");
    }
    fclose(fp);
    
    // *** criar algoritmo que analisa a imagem na matriz e gera para cada pixel um ASCII
    // criar uma estratégia para gerar "imagens" ASC Art razoaveis para rostos, paisagens etc
    // alem de testar com os arquivos ppm de exemplo, usar fotos da familia, convertendo
    // primeiro de jpg para ppm usando algum site/conversor
    
    // a escolha dos caracteres e faixas de cor ira impactar no resultado final
    // mostrar a imagem na tela
    
    // abrir arquivo texto NOVO (com 'w' - para write)
    // e salvar o ASC Art no arquivo texto com o mesmo nome do original, mas com extensao .txt
	// liberar memoria ver aloc2 linhas 36 a 39
	// fechar programa
	
    return 0;
}
