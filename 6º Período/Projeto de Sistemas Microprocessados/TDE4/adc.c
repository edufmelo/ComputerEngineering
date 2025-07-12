/*
 * adc.c
 *
 * Created: 20/11/2024 18:49:16
 *  Author: DANBINA
 */ 


#define F_CPU	16000000
#include <util/delay.h>
#include <avr/io.h>
#include "minha_lib.h"

void configura_adc(){
	ADCSRA = 0x87;
}

unsigned int le_adc(){
	ADMUX = 0xC0;
	_delay_us(10);
	setbit(ADCSRA,6);	// Dispara ADC
	while(getbit(ADCSRA,6)){
	}
	return ADC;
}
