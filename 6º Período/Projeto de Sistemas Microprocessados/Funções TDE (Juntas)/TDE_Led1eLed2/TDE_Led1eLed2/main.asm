	cbi DDRD,2	; Entrada S1
	cbi DDRD,3	; Entrada S2
	cbi DDRD,4	; Entrada S3
	sbi DDRD,6	; Saída LED01
	sbi DDRD,7	; Saída LED02

; Código - LED 01:

beginLED1:
	sbis PIND,2
	rjmp tryS2LED1
ligaLED1:
	sbi PORTD,6
	rjmp beginLED2
tryS2LED1:
	sbis PIND,3
	rjmp ligaLED1
desligaLED1:
	cbi PORTD,6
	rjmp beginLED2

; Código - LED 02:

beginLED2:
	sbis PIND,4
	rjmp S2LED2_01
	rjmp S2LED2_02
S2LED2_01:	
	sbic PIND,3
	rjmp S1LED2_01
ligaLED2:
	sbi PORTD,7
	rjmp beginLED1
S1LED2_01:
	sbic PIND,2
	rjmp desligaLED2
	rjmp ligaLED2
desligaLED2:
	cbi PORTD,7
	rjmp beginLED1
S2LED2_02:
	sbic PIND,3
	rjmp S1LED2_02
	rjmp desligaLED2
S1LED2_02:
	sbis PIND,2
	rjmp desligaLED2
	rjmp ligaLED2