	jmp		start

.include "serial.inc"

start:
	call	config_1200_8_n_1
	call	rx
	cpi		R17,'H'
	brlo	imprime_arroba
	call	tx
fim:
	rjmp	fim

imprime_arroba:
	ldi		R17,'@'
	call	tx
	jmp		fim
