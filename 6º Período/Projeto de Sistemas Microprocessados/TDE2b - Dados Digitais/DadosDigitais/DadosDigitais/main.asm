	rjmp config
.include "tx_serial.inc"

config:
	call config_4800_8_n_2
	txs welcome
start:
	txs digite
	; rjmp main_loop - redundancia

main_loop:
	call config_loop        ; Executa o loop de contagem

	rjmp main_loop          ; Volta para o loop principal


imprime:         ; Realiza o sorteio (incrementa R18 e R19)
	txs resultado_dado1     ; Envia a string "Resultado" e "Dado 1 ="
	call tx_R18             ; Envia o valor de R18 via serial
	txs resultado_dado2     ; Envia a string "Dado 2 ="
	call tx_R19             ; Envia o valor de R19 via serial
	rjmp start              ; Retorna ao loop principal para esperar mais entradas


welcome:
	.db '\n',"Seja bem-vindo!",0

digite:
	.db '\n', "Digite '+' para realizar o sorteio.",'\n',0

resultado_dado1:
	.db '\n',"Resultado:",'\n',"Dado 1 = ",'\n',0

resultado_dado2:
	.db '\n',"Dado 2 = ",'\n',0
