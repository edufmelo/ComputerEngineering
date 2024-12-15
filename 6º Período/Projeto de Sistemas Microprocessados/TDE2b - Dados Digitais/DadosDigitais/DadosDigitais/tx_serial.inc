.macro le_tabela_R16
	push	R31
	push	R30
	push	R16
		add		R30, R16
		ldi		R16, 0
		adc		R31, R16
		lpm		@0, Z	; OU R17 NO LUGAR DO R17
	pop		R16
	pop		R30
	pop		R31
.endm

.macro txs
	push	R31
	push	R30
		ldi		R31, HIGH(@0*2)
		ldi		R30, LOW(@0*2)
		call	tx_string
	pop		R30
	pop		R31
.endm


tx_string:
	push	R16
	push	R17
		ldi		R16,0
	tx_string_loop:
		le_tabela_R16	R17
		cpi		R17,0
		breq	tx_string_fim 
		call	tx_R17
		inc		R16
		rjmp		tx_string_loop
	tx_string_fim:
		pop		R17
		pop		R16
		ret

; 4800 bps
; 8 bits
; n - sem paridade
; 2 - stop bit

config_4800_8_n_2:
; Baud Rate 4800bps
	; UBRR0H <- 0x01
	ldi		R17,0x01
	sts		UBRR0H, R17
	; UBRR0L <- 0xA0
	ldi		R17,0xA0
	sts		UBRR0L, R17
	; UCSR0C <- 0x0E
	ldi		R17,0x0E
	sts		UCSR0C,R17
	; UCSR0B <- 0x18
	ldi		R17,0x18
	sts		UCSR0B,R17
	; UCSR0A <- 0x22
	ldi		R17,0x22
	sts		UCSR0A,R17
	ret

; Funcao que transmite o valor de R17
tx_R17:
	push	R16			; Salva R16 na pilha
tx_R17_loop:
	lds		R16,UCSR0A
	sbrs	R16,5
	rjmp	tx_R17_loop
	sts		UDR0,R17
	pop		R16			; Restaura o valor original de R16
	ret

; Aguarda receber um valor da serial e coloca em R17
rx_R17:
	push	R16
rx_R17_loop:
	lds		R16,UCSR0A
	loop_incremento:
		inc R19
	pause:
		cpi R19,7
		brne loop_incremento
		ldi R19,1
	
		inc R18
		cpi R18,7
		brne loop_incremento
		ldi R18,1

	sbrs	R16,7
	rjmp	rx_R17_loop
	lds		R17,UDR0
	pop		R16
	ret

config_loop:
	ldi R18,1
	ldi R19,1
	rjmp rx_check




; Função para enviar o valor de R18 via serial
tx_R18:
	push R16                 ; Salva o valor de R16 na pilha
tx_R18_loop:
	lds  R16, UCSR0A
	sbrs R16, 5              ; Verifica se o buffer de transmissão está pronto
	rjmp tx_R18_loop         ; Se não estiver pronto, continua esperando
	sts  UDR0, R18           ; Envia o valor de R18
	pop  R16                 ; Restaura o valor original de R16
	ret

; Função para enviar o valor de R19 via serial
tx_R19:
	push R16                 ; Salva o valor de R16 na pilha
tx_R19_loop:
	lds  R16, UCSR0A
	sbrs R16, 5              ; Verifica se o buffer de transmissão está pronto
	rjmp tx_R19_loop         ; Se não estiver pronto, continua esperando
	sts  UDR0, R19           ; Envia o valor de R19
	pop  R16                 ; Restaura o valor original de R16
	ret

rx_check:
	push R16                 ; Salva o valor de R16
		rjmp rx_R17              ; Verifica se o usuário digitou algo
		cpi R17, '+'             ; Compara o valor recebido com '+'
		brne no_input            ; Se não for '+', pula a impressão
		call imprime             ; Se for '+', chama a função imprime
no_input:
	pop R16                  ; Restaura o valor de R16
	rjmp pause