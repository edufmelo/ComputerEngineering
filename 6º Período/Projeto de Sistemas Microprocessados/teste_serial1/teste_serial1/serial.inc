/*
 * serial.inc
 *
 *  Created: 04/10/2024 20:56:39
 *   Author: afonso.miguel
 */ 
 
config_1200_8_n_1:
; Baud Rate 1200bps
	ldi		R17,0x3
	sts		UBRR0H, R17
	ldi		R17,0x40
	sts		UBRR0l, R17
	; UCSR0C <- 0x06
	ldi		R17,0x06
	sts		UCSR0C,R17
	; UCSR0B <- 0x18
	ldi		R17,0x18
	sts		UCSR0B,R17
	; UCSR0A <- 0x0x20
	ldi		R17,0x20
	sts		UCSR0A,R17
	ret


; Funcao que transmite o valor de R17
tx:
	push	R16			; Salva R16 na pilha
tx1:
	lds		R16,UCSR0A
	sbrs	R16,5
	rjmp	tx1
	sts		UDR0,R17
	pop		R16			; Restaura o valor original de R16
	ret

; Aguarda receber um valor da serial e coloca em R17
rx:
	push	R16
rx1:
	lds		R16,UCSR0A
	sbrs	R16,7
	rjmp	rx1
	lds		R17,UDR0
	pop		R16
	ret