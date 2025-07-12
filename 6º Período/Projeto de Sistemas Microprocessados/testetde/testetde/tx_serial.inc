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
	sbrs	R16,7
	rjmp	config_loop		; Cria um loop incrementando até receber a entrada do usuário
	lds		R17,UDR0		
	pop		R16
	ret						; Caso usuário digite, o bit se torna setado e retorna para rx_check (comparando se é '+')

config_loop:
	inc R19					
	cpi R19,'7'				; Compara se R19 chegou a 7 em CHAR	(EM ASCII - 0x37h)
	brne rx_R17_loop		; Caso não seja 7, volta a verificar entrada
	ldi R19,'1'				; Reseta R19 

	inc R18
	cpi R18,'7'				; Compara se R18 chegou a 7	em CHAR	(EM ASCII - 0x37h)
	brne rx_R17_loop		; Caso não seja 7, volta a verificar entrada
	ldi R18,'1'				; Reseta R19 
	rjmp rx_R17_loop		; Volta a verificar entrada

	 
; Envia valor de R18 POR COMUNICAÇÃO SERIAL (mesmo feito em sala)
tx_R18:
	push R16                 ; Salva o valor de R16 na pilha
tx_R18_loop:
	lds  R16, UCSR0A
	sbrs R16, 5              
	rjmp tx_R18_loop         
	sts  UDR0, R18           
	pop  R16                 ; Restaura o valor original de R16
	ret

; Envia valor de R19 POR COMUNICAÇÃO SERIAL (mesmo feito em sala)
tx_R19:
	push R16                 ; Salva o valor de R16 na pilha
tx_R19_loop:
	lds  R16, UCSR0A
	sbrs R16, 5             
	rjmp tx_R19_loop         
	sts  UDR0, R19           
	pop  R16                 ; Restaura o valor original de R16
	ret

rx_check:
	call rx_R17              ; Chama a função, verificando entrada
	cpi R17,'+'              ; Compara o que foi digitado com '+'
	brne rx_check			 ; Caso não seja, fica em loop
	rjmp imprime             ; Caso seja, imprime resultado
