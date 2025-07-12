	cbi DDRD,2
	cbi DDRD,3
	cbi DDRD,4
	sbi DDRD,7
begin:
	sbis PIND,4
	rjmp testa_s20
	rjmp testa_s21
testa_s20:
	sbic PIND,3
	rjmp testa_s10
liga:
	sbi PORTD,7
	rjmp begin
testa_s10:
	sbic PIND,2
	rjmp desliga
	rjmp liga
desliga:
	cbi PORTD,7
	rjmp begin
testa_s21:
	sbic PIND,3
	rjmp testa_s11
	rjmp desliga
testa_s11:
	sbis PIND,2
	rjmp desliga
	rjmp liga

