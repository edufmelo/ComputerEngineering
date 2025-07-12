	cbi DDRD,2
	cbi DDRD,3
	sbi DDRD,6
begin:
	sbis PIND,2
	rjmp testa_s2
liga:
	sbi PORTD,6
	rjmp begin
testa_s2:
	sbis PIND,3
	rjmp liga
desliga:
	cbi PORTD,6
	rjmp begin