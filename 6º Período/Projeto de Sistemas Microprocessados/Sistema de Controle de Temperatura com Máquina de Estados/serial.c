/*
 * serial.c
 *
 * Created: 20/11/2024 18:49:55
 *  Author: eduardomelo
 */ 
#include "serial.h"
#include "minha_lib.h"

void config_4800_8_n_1(){
	UBRR0H = 0x00;
	UBRR0L = 0xCF;
	UCSR0C = 0x06;
	UCSR0B = 0x18;
	UCSR0A = 0x20;
}

void tx_bit(char c){
	while(getbit(UCSR0A,5) == 0){
	}
	UDR0 = c;
}
void tx_string(const char* str) {
	for (int x = 0; x < strlen(str); x++){
		tx_bit(str[x]);
	}
}
void tx_valor(float valor) {
	char buffer[30];  // String temporária para armazenar o valor formatado
	int valor_inteiro = (int)valor;                          // Parte inteira do float
	int valor_frac = (int)((valor - valor_inteiro) * 100);  // Parte fracionária com 2 casas decimais

	sprintf(buffer, "%d.%02d", valor_inteiro, valor_frac);

	// Transmite a string pela serial
	tx_string(buffer);
}
