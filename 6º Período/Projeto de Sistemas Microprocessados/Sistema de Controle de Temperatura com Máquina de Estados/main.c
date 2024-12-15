#define F_CPU 16000000
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include "minha_lib.h"
#include "serial.h"
#include "adc.h"

int aquecedor=DESATIVADO;
float temp;
int ultimo_estado = -1;
int tamanhosenha = 0;           // Controla a quantidade de caracteres inseridos
char senha[5] = {'\0'};         // Inicializa com um array de 5 caracteres, sendo 4 para a senha e 1 para o terminador '\0'
char desativar[] = "4732";      // Senha correta para desativar o aquecedor

int main(void){
	config_4800_8_n_1();	// Config Serial
	configura_adc();		// Config ADC
	setbit(DDRB,5);			// LED como saida
	
	setbit(UCSR0B, 7);		// Liga RX IE
	setbit(ADCSRA,3);		// Liga ADC IE
	sei();					// Liga chave geral das INTs
	setbit(ADCSRA,6);	// Dispara ADC

	while (1){
		tx_bit('>');
		_delay_ms(1000);
	}
}


ISR(USART_RX_vect) {
	char x = UDR0;  // Lê o dado da porta serial
	int certo = 1;

	if (x == '!' && aquecedor == DESATIVADO) {
		aquecedor = DESLIGADO;
		ultimo_estado=DESLIGADO;
		tx_string("\nAquecedor Desligado\n");
		setbit(ADCSRA, 6); // Dispara a conversão ADC
		} else {
		if (tamanhosenha == 4) {
			// Shift os valores da senha para a esquerda (remover o primeiro caractere)
			for (int i = 0; i < 3; i++) {
				senha[i] = senha[i + 1];
			}
			senha[3] = x; // Adiciona o novo caractere no final
			} else {
			senha[tamanhosenha] = x;  // Adiciona o caractere digitado
			tamanhosenha++;
		}

		if (tamanhosenha == 4) {
			// Verifica se a senha digitada corresponde à senha de desativação
			for (int i = 0; i < 4; i++) {
				if (senha[i] != desativar[i]) {
					certo = 0;  // A senha não corresponde
					break;
				}
			}

			// Se a senha foi correta, desativa o aquecedor
			if (certo == 1) {
				if (aquecedor != DESATIVADO) {
					aquecedor = DESATIVADO;
					clrbit(PORTB, 5);  // Desliga o LED (exemplo)
					tx_string("\nAquecedor Desativado\n");  // Mensagem de confirmação
					} else {
					tx_string("\nAquecedor já está desativado\n");  // Caso já esteja desativado
				}
				} else {
				tx_string("\nSenha Incorreta\n");  // Se a senha for incorreta
			}


			// Reinicia o processo de digitação da senha, limpando a variável
			tamanhosenha = 0; // Reinicia a contagem
			memset(senha, '\0', sizeof(senha)); // Limpa o array de senha
		}
	}
}






ISR(ADC_vect) {
	// Cálculo da temperatura baseado no valor do ADC
	temp = ((1.1 * le_adc()) / 1023) / 0.01;

	// Verifica o estado e a temperatura para controlar o LED
	if (aquecedor != DESATIVADO && temp >= 50) { // Se não estiver desativado e temperatura >= 26.75
		aquecedor = DESLIGADO; // Muda para estado DESLIGADO
		
		// Verifica se o estado mudou para DESLIGADO e ainda não foi impresso
		if (aquecedor == DESLIGADO && ultimo_estado != DESLIGADO) {
			clrbit(PORTB, 5);    // Desliga o LED
			tx_string("\nAquecedor Desligado\n");
			tx_valor(temp);
			_delay_ms(800);
			tx_bit('\n');

			// Atualiza o último estado impresso
			ultimo_estado = DESLIGADO;
		}
	}
	else if (aquecedor != DESATIVADO && temp <= 45) { // Se não estiver desativado e temperatura <= 26.75
		aquecedor = LIGADO;    // Muda para estado LIGADO
		
		// Verifica se o estado mudou para LIGADO e ainda não foi impresso
		if (aquecedor == LIGADO && ultimo_estado != LIGADO) {
			setbit(PORTB, 5);   // Liga o LED
			tx_string("\nAquecedor Ligado\n");
			tx_valor(temp);
			_delay_ms(800);
			tx_bit('\n');
			
			// Atualiza o último estado impresso
			ultimo_estado = LIGADO;
		}
	}

	// Reativa o ADC para a próxima conversão
	setbit(ADCSRA, 6);
}


