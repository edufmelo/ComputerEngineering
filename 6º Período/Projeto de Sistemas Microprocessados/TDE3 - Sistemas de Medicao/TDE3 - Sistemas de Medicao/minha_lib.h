#ifndef MINHA_LIB_H_
#define MINHA_LIB_H_

#define setbit(porta,b)		porta |= (1 << b)
#define clrbit(porta,b)		porta &= ~(1 << b)
#define getbit(porta,b)		((porta>>b) & 1)

#endif /* MINHA_LIB_H_ */