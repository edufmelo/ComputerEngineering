    rjmp config
.include "tx_serial.inc"

config:
    call config_4800_8_n_2		; Configura os registradores
    
start:
    txs welcome					; Mostra mensagem de bem-vindo 
    
main_loop:
	txs digite					; Mostra mensagem para a realização do sorteio
	ldi R18,'1'					; Configura R18 para começar em 1 em CHAR (ASCII - 0x31h)
	ldi R19,'1'					; Configura R19 para começar em 1 em CHAR (ASCII - 0x31h) 
	rjmp rx_check				; Salta para a checagem de entrada e incrementação

imprime:						
    txs resultado_dado1			
	call tx_R18					; Transmite resultado via serial R18
    txs resultado_dado2
    call tx_R19					; Transmite resultado via serial R19
    rjmp main_loop				; Cria repetição para fazer infinitos sorteios

welcome:
    .db '\n',"Seja bem-vindo!",0

digite:
    .db '\n', "Digite '+' para realizar o sorteio.",'\n',0

resultado_dado1:
    .db '\n',"Resultado:",'\n',"Dado 1 = ",0

resultado_dado2:
    .db '\n',"Dado 2 = ",0
