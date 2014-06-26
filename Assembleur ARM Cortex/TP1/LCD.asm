		AREA 	MULTex, CODE, READONLY


RCGC2		EQU		0x400FE000+ 0x108
GPIODIRC	EQU		0x40006000+ 0x400
GPIODIRH	EQU		0x40027000+ 0x400
GPIODENH	EQU		0x40027000+ 0x51C
GPIODENC	EQU		0x40006000+ 0x51C
E			EQU		0x40027020
RW			EQU		0x40027010
RS			EQU		0x40006040
DB4567		EQU		0x400273C0

		ENTRY
		EXPORT	__main
__main
	
	; activation de l'horloge sur les ports H ET C
	LDR R0,=RCGC2
	LDR R1,= 0x84	
	STR R1,[R0] ; on met R1 à l'adresse indiquée par R0		    
	NOP
	NOP
	NOP
	
	
	;DIRECTION  PORT C
	LDR R0,=GPIODIRC ; R0 contient 0x40006000+ 0x400
	LDR R2,[R0]     ; on charge 0x40006000+ 0x400 dans R2
	LDR R1,=0x10	 ; on charge 0x10 dans R1
	ORR R1,R2 		; mettre les bits à un qui n'y sont pas sans toucher aux autres
	STR R1,[R0]     ; on met R1 dans la case mémoire indiquée par l'adresse R0
	
	;DIRECTION  PORT H
	LDR R0,=GPIODIRH
	LDR R2,[R0]
	LDR R1,=0xFC
	ORR R1,R2
	STR R1,[R0]
	
	; FONCTION NUMERIQUE PORT C
	LDR R0,=GPIODENC
	LDR R2,[R0]
	LDR R1,=0x10
	ORR R1,R2
	STR R1,[R0]
	
	; FONCTION NUMERIQUE PORT H
	LDR R0,=GPIODENH
	LDR R2,[R0]
	LDR R1,=0xFC
	ORR R1,R2
	STR R1,[R0]
	
	; AFFICHE un caractere
	
	BL LCDINIT
	
	BL RS1
	MOV R0,#'J'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait 
	
	MOV R0,#'V'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'O'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'U'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'S'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#' '
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'A'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'I'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'M'
	BL LCDWR8
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'E'
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#' '
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'P'
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'A'
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'S'
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#' '
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#'!'
	BL LCDWR8
	
	
	LDR R1,= 0xA6B ; 50ms
	BL Wait
	
	MOV R0,#' '
	BL LCDWR8
	
	
boucle B boucle
	
	
	
	
	
	
	
	; FONCTION LCDINIT 
	
LCDINIT PUSH{LR}
		LDR R1,= 0x516155 ; 1 s 
		BL Wait
	
		BL RS0
		LDR R0,=0x30 
		BL LCDWR4
	
	LDR R1,=0x682B	; 5 ms
	BL Wait
	
	LDR R0,=0x30
	BL LCDWR4
	
	LDR R1,=0x35  ; 100 us
	BL Wait
	
	LDR R0,=0x30   
	BL LCDWR4
	
	LDR R1,=0x35 ; 100 us
	BL Wait
	
	LDR R0,=0x20 ;
	BL LCDWR4
	
	LDR R1,=0x35 ; 100us
	BL Wait
	
	LDR R0,=0x28
	BL LCDWR8
	
	LDR R1,=0x2D92B ;38 ms
	BL Wait
	
	LDR R0,=0x0F
	BL LCDWR8
	
	LDR R1,=0x2D92B ;38 ms
	BL Wait
	
	LDR R0,=0x01
	BL LCDWR8
	
	LDR R1,=0x29AB ; 2ms
	BL Wait
	
	POP {PC}
	
	;FONCTION LCDWR4
LCDWR4 	PUSH{LR}
boucle1
		MOV R0,#0xF0
	  ;on arrive ici avec R0 qui contient les  4bits de poids fort a afficher
		LDR R2,=RW      ; RW a 0
		LDR R1,= 0x0
		STR R1,[R2]
		
		NOP
		LDR R2,= DB4567   ; VALEUR DES BITS de R0
		STR R0,[R2] 
					
		LDR R2,= E              ; Remise a 1 de E
		LDR R1,= 0x8
		STR R1,[R2]
		
		LDR R1,= 3 ;R1 contient la durée
		BL Wait
			
		LDR R2,= E               ; Remise a 0 de E
		LDR R1,= 0x0
		STR R1,[R2]
		
		LDR R1,= 3              ; Attente	
		B boucle1
		BL Wait 				
		POP {PC}
		

	;FONCTION LCDWR8
LCDWR8	 PUSH{LR}

		BL LCDWR4
		LSL R0,#4
		BL LCDWR4
	
		POP {PC}
	
	;FONCTION WAIT
Wait PUSH {LR}

|b|		SUB R1,#1
		CMP	R1,#0
		BNE |b|
		POP {PC}
	
	;FONCTION RS 1	
	
RS1 	PUSH {LR}

		LDR R2,=RS
		LDR R3,=0x10
		STR R3,[R2]
	
		POP {PC}
	
	; FONCTION RS 0
RS0 	PUSH {LR}

		LDR R2,=RS
		LDR R3,=0x0
		STR R3,[R2]
	
		POP {PC}
	; Eric
	
		END
	