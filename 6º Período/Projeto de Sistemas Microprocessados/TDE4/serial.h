
#ifndef SERIAL_H_
#define SERIAL_H_
#include <avr/io.h>
#include <util/delay.h>
#include <string.h>
#include <stdio.h>

void config_4800_8_n_1();
void tx_bit(char c);
void tx_string(const char* str);
void tx_valor(float valor);

#endif /* SERIAL_H_ */