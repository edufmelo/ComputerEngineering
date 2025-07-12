#define F_CPU    16000000
#include <avr/io.h>
#include <util/delay.h>
#include <string.h>
#include <stdio.h>
#include "minha_lib.h"

void config_57600_8_n_1(){
	UBRR0H = 0x00;
	UBRR0L = 0x22;
	UCSR0C = 0x06;
	UCSR0B = 0x18;
	UCSR0A = 0x22;
}

void tx_bit(char c){
	while(getbit(UCSR0A,5) == 0){
	}
	UDR0 = c;
}
void tx_valor(float valor) {
	char buffer[20];  // String temporária para armazenar o valor formatado
	int valor_inteiro = (int)valor;                          // Parte inteira do float
	int valor_frac = (int)((valor - valor_inteiro) * 100);  // Parte fracionária com 2 casas decimais

	sprintf(buffer, "%d.%02d", valor_inteiro, valor_frac);

	// Transmite a string pela serial
	tx_string(buffer);
}

void tx_string(const char* str) {
	for (int x = 0; x < strlen(str); x++){
		tx_bit(str[x]);
	}
}

char rx(){
	while(getbit(UCSR0A,7) == 0){
	}
	return UDR0;
}

void config_adc(){
	ADCSRA = 0x87;
}

unsigned int converte_adc_1_1v(){
	ADMUX = 0xC0; // Configura canal 0 PARA 1.1V
	_delay_us(10);
	setbit(ADCSRA,6); // Dispara a conversao
	while(getbit(ADCSRA,6) == 1){    // Aguarda fim da conversao
	}
	return ADC;
}

unsigned int converte_adc_5v(){
	ADMUX = 0x41; // Configura canal 1 PARA 5V
	_delay_us(10);
	setbit(ADCSRA,6); // Dispara a conversao
	while(getbit(ADCSRA,6) == 1){    // Aguarda fim da conversao
	}
	return ADC;
}

void menu(){
	tx_string("\nSelecione uma opcao: \n");
	tx_string("\n1 - Medir temperatura (LM35)\n");
	tx_string("2 - Medir tensao (Potenciometro)\n");
}

int main(void){
	config_57600_8_n_1();
	config_adc();
	menu();

	while (1) {
		char opcao = rx(); // Recebe a opção do usuário
		
		if (opcao == '1'){
			float tensao = (converte_adc_1_1v() * 1.1) / 1023.0;
			float temp = (tensao / 0.01);
			
			tx_string("\nTemperatura: ");
			tx_valor(temp);
			tx_string("ºC\n");
			menu();
			
			} else if (opcao == '2'){
			// Leitura do ADC e cálculo da tensão em volts
			float calculo_tensao = ((converte_adc_5v() * 5.0) / 1023.0);
			
			tx_string("\nTensão: ");
			tx_valor(calculo_tensao);
			tx_string(" V\n");                               // Unidade de medida
			menu();
			} else{
			tx_string("\nErro! Digite uma das opções disponíveis!\n");
			menu();
		}
	}
}