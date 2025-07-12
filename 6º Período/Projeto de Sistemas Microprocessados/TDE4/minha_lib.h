/*
 * minha_lib.h
 *
 * Created: 20/11/2024 18:51:42
 *  Author: DANBINA
 */ 
#ifndef MINHA_LIB_H_
#define MINHA_LIB_H_

// Macros para manipula��o de bits
#define setbit(porta,b)     porta |= (1 << b)
#define clrbit(porta,b)     porta &= ~(1 << b)
#define getbit(porta,b)     ((porta >> b) & 1)

// Enum para estados da m�quina
typedef enum {
	DESATIVADO = 0,
	DESLIGADO = 1,
	LIGADO = 2
} Estado;

#endif /* MINHA_LIB_H_ */