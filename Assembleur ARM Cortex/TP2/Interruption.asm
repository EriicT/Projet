		AREA TP2, CODE, READONLY
		
		EXPORT SRF05ECHO
		
; Affichage 
RCGC1				EQU  	0x400FE000+ 0x104
RCGC2				EQU		0x400FE000+ 0x108
GPIODIRC			EQU		0x40006000+ 0x400
GPIODIRH			EQU		0x40027000+ 0x400
GPIODENH			EQU		0x40027000+ 0x51C
GPIODENC			EQU		0x40006000+ 0x51C
E					EQU		0x40027020
RW					EQU		0x40027010
RS					EQU		0x40006040
DB4567				EQU		0x400273C0

; Timer

GPIOB			EQU			0X40005000
ECHO            EQU         GPIOB + (2_100<<2) ; 0000 0100 => 0001 0000
TRIGGER			EQU         GPIOB + (2_1000<<2)  ; 0000 1000 => 0010 0000


GPIOBDIR		EQU			GPIOB + 0X400
GPIOBDEN		EQU			GPIOB + 0X51C
GPIOBIBE		EQU			GPIOB + 0X408
GPIOBIM			EQU			GPIOB + 0X410
GPIOBICR		EQU			GPIOB + 0X41C

NVIC  			EQU 		0xE000E100

GPTMT0			EQU			0X40030000
GPTMCFG			EQU			GPTMT0 + 0X0	
GPTMTAMR		EQU			GPTMT0 + 0X4	
GPTMCTL			EQU			GPTMT0 + 0XC	
GPTMTAILR		EQU			GPTMT0 + 0X28
GPTMTAV			EQU			GPTMT0 + 0X50


;Calcul



	ENTRY 
	EXPORT __main
__main

;***************** INITIALISATION LCD ***************
;****************************************************

;***************** ACTIVATION DE L'HORLOGE **********
		LDR R0, =RCGC2
		LDR R1, [R0]
		LDR R2, =0x86
		ORR R1, R2
		STR R1, [R0]
		 
		NOP
		NOP
		NOP


;************ DIRECTION *************************************	
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
		
;************ FONCTION NUMERIQUE ****************************	
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
	
	
;**************** INITIALISATION DU LCD ****************
;*******************************************************
		
LCDINIT 
		LDR R1,= 2666667 ; 1 s 
		BL Wait
	
		BL RS0
		LDR R0,=0x30 
		BL LCDWR4
	
		LDR R1,=26667	; 5 ms
		BL Wait
	
		LDR R0,=0x30
		BL LCDWR4
	
		LDR R1,=1067  ; 200µs
		BL Wait
	
		LDR R0,=0x30   
		BL LCDWR4
	
		LDR R1,=1067 ; 100 us
		BL Wait
	
		LDR R0,=0x20 ;
		BL LCDWR4
	
		LDR R1,=267 ; 50us
		BL Wait
	
		LDR R0,=0x28
		BL LCDWR8
	
		LDR R1,=267 ;38 ms
		BL Wait
	
		LDR R0,=0x0C
		BL LCDWR8
	
		LDR R1,=267 ;38 ms
		BL Wait
	
		LDR R0,=0x01
		BL LCDWR8
	
		LDR R1,=10667 ; 2ms
		BL Wait
	

		

;**************** ACTIVATION DU PORT B **************
;**************** ACTIVATION DE RCGC1 ***************

		LDR R0,=RCGC1
		LDR R1,=0x10000
		STR R1,[R0]
		
;*****************************************************
;***************** DIRECTION *************************
		LDR R0, =GPIOBDIR
		LDR R1, [R0]
		LDR R2, =0x08
		;ORR R1, R2
		;MVN R2, R2 
		;AND R1, R2
		STR R2, [R0]

;*****************************************************
;************** NUMERIQUE ****************************
		LDR R0,=GPIOBDEN
		LDR R1,[R0]
		LDR R2, =0X0C
		ORR R1, R2
		STR R1,[R0]


;*****************************************************
;CONFIG GPIOBCFG POUR ETRE EN 32 bits ****************
		LDR R0, =GPTMCFG
		LDR R1, = 0X00
		STR R1,[R0]
		

;*****************************************************
;CONFIG GPIOBIBE POUR DES INTERRUPTIONS SUR LES DEUX FRONTS
		LDR R0, =GPIOBIBE
		LDR R1, = 0X4
		STR R1,[R0]



;*****************************************************
;CONFIG GPIOBIM POUR DES INTERRUPTION TOUT COURT *****
		LDR R0, =GPIOBIM
		LDR R1, = 0X4
		STR R1,[R0]
;*****************************************************


;*****************************************************
;******* GPTMTAMR EN MODE ONE-SHOT********************
		LDR R0, =GPTMTAMR
		LDR R1, =0X1
		STR R1,[R0]

;*****************************************************
;GTPMTAILR POUR REGLER LA VALEUR INITIALE DU COMPTEUR
		LDR R0, =GPTMTAILR
		LDR R1, =0XFFFFFFFF
		STR R1, [R0]
	
;*****************************************************
;************ Activation du NVIC *********************
		
		LDR R0, =NVIC
		LDR R1, =0X02
		STR R1, [R0]
		
;******************************************************

;************** PROGRAMMME PRINCIPAAAAAL **************
;******************************************************

		BL RS1
		LDR R0,=txt
		BL LCDPUTS
		LDR R1,=267
		BL Wait
		
		BL RS0
		LDR R0, =0xC6
		BL LCDWR8
		LDR R1,=267
		BL Wait
		
		BL RS1
		LDR R0,=cm
		BL LCDPUTS
		LDR R1,=267
		BL Wait

		BL RS0
		LDR R0,=0x80
		BL LCDWR8
		LDR R1,=267
		BL Wait

; 1.Générer Trigger 
test
		LDR R0, =TRIGGER
		LDR R1, =2_1000
		STR R1, [R0]
		
		LDR R1,=80 ; + de 10 micro seconde
		BL Wait
		

		LDR R0, =TRIGGER
		MOV R1,#0
		STR R1, [R0]
		
		LDR R1,=0x3FFFF ;50ms
		BL Wait
		
		
		B test
		
SRF05ECHO	PUSH{LR}	

			LDR R0,=ECHO
			LDR R1,[R0]
			CMP R1,#0
			BEQ BAS	
			
HAUT  ;Activer le Timer
		LDR R0, =GPTMCTL
		LDR R1, =0X01
		STR R1, [R0]
		B finint
		
BAS		
		;Desactiver le Timer
		LDR R0, =GPTMCTL
		LDR R1, =0X00
		STR R1, [R0]

		; Sauve valeur2
		LDR R7,=GPTMTAV
		LDR R8,[R7]

		; Calcul de la durée écoulée
		LDR R10,=0xFFFFFFFF
		SUB R9,R10,R8

		; Division par 58*16
		LDR R11,=928
		UDIV R9,R11
		LDR R1,=0x30303030
		LDR R11,=10
		UDIV R8,R9,R11
		MUL R7,R8,R11
		SUB R0,R9,R7
		LSL R0,#24
		ORR R1,R0
		
		MOV R9,R8
		UDIV R8,R9,R11
		MUL R7,R8,R11
		SUB R0,R9,R7
		LSL R0,#16
		ORR R1,R0
		
		MOV R9,R8
		UDIV R8,R9,R11
		MUL R7,R8,R11
		SUB R0,R9,R7
		LSL R0,#8
		ORR R1,R0
		
		MOV R9,R8
		UDIV R8,R9,R11
		MUL R7,R8,R11
		SUB R0,R9,R7
		ORR R1,R0
		; Fin de la	 conversion

	
		LDR R2,=res
		STR R1,[R2]
		LDR R1,=0
		STR R1,[R2,#4]
		
		
		BL RS0
		LDR R0,=0xC2
		BL LCDWR8
		LDR R1,=267
		BL Wait
		
		BL RS1
		LDR R0,=res
		BL LCDPUTS
		LDR R1,=267
		BL Wait
		
		
		BL RS1	
		LDR R0,=GPTMTAV
		LDR R1,=0xFFFFFFFF
		STR R1,[R0]
		
finint		
		LDR R0, =GPIOBICR
		LDR R1, = 0x4
		STR R1,[R0]
		
		POP{PC}



;*********************** FONCTION LCD PUTS*************
;******************************************************


LCDPUTS PUSH {LR} 
		MOV R4, R0 
loop
		LDRB R0, [R4] 
		CMP R0, #0 
		BEQ fin 
		BL LCDWR8 
		ADD R4, #1 
		LDR R1,= 267 ; 50ms BL WAIT B retour fin POP {PC}
		BL Wait 
		B loop
fin		POP {PC}



;********************* Fonction LCDWR4+8****************
;*******************************************************

LCDWR4 	PUSH{LR}
	
		LDR R2,=RW
		LDR R1,= 0x0
		STR R1,[R2]
		
		NOP
		
		LDR R2,= DB4567
		STR R0,[R2] 
		
		LDR R2,= E
		LDR R1,= 0x8
		STR R1,[R2]
		
		LDR R1,=3
		BL Wait
			
		LDR R2,= E
		LDR R1,= 0x0
		STR R1,[R2]
		
		LDR R1,=3
		BL Wait
		
		POP {PC}
		
;*************** FONCTION LCDWR8 ******************
;*************** 2x LCDWR4 + DECALLAGE ************

LCDWR8	
		PUSH{LR}
		BL LCDWR4
		LSL R0,#4
		BL LCDWR4
		POP {PC}
	
;*************** FONCTION WAIT ********************
;**************************************************
Wait 	PUSH {LR}
|b|		SUB R1,#1
		CMP	R1,#0
		BNE |b|
		POP {PC}
;*************** FONCTION MODIFICATION RS **********	
;***************************************************
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
		
Nop		
;*****************************************************
; Tableau

		AREA Variable, READWRITE
	
res DCB "0000",0

		AREA Constante, READONLY
	
txt DCB "La distance est",0
cm  DCB " cm hihihi ",0

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	END
	
