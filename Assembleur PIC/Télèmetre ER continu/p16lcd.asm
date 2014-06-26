;************************************************************************
;*	Microchip Technology Inc. 2002					*
;*	Assembler version: 2.0000					*
;*	Filename: 							*
;*		lcd16.asm			 			*
;************************************************************************

	list 		p=16F877A
	#include	P16F877A.inc

#define	LCD_D4		PORTD, 0	; LCD data bits

#define	LCD_D5		PORTD, 1    ; LCD_D5 est associé  à port PORTD byte 1
#define	LCD_D6		PORTD, 2     
#define	LCD_D7		PORTD, 3

#define	LCD_D4_DIR	TRISD, 0	; LCD data bits , bite 0
#define	LCD_D5_DIR	TRISD, 1	; pour savoir si c'est une entrée ou une sortie , bite 1
#define	LCD_D6_DIR	TRISD, 2	; bite 2
#define	LCD_D7_DIR	TRISD, 3	; bite 3
	
#define	LCD_E		PORTD, 6	; LCD E clock
#define	LCD_RW		PORTD, 5	; LCD read/write line
#define	LCD_RS		PORTD, 4	; LCD register select line

#define	LCD_E_DIR	TRISD, 6	; définition de constantes
#define	LCD_RW_DIR	TRISD, 5	; définition de constantes
#define	LCD_RS_DIR	TRISD, 4	; définition de constantes

#define	LCD_INS		0	
#define	LCD_DATA	1



D_LCD_DATA	UDATA 0x20
COUNTER		res	1  ; permet de réserver le nombre de case en mémoire
delay		res	1
temp_wr		res	1
temp_rd		res	1

	GLOBAL	temp_wr  ; variable globale peut être. ??????????????

PROG1	CODE 


;***************************************************************************
	
LCDLine_1
	banksel	temp_wr   	; action qui permet de changer de banque. On va dans la banque temp_wr 
	movlw	0x80		; initialisation d'un pointeur
	movwf	temp_wr  	; le registre w et déplacé dans le registre f
	call	i_write		; appel de i_write
	return				;Au retour d'un sous programme, le compteur ordinal est chargé par l'adresse sauvegardé dans la pile ( adresse de retour).	
	GLOBAL	LCDLine_1

LCDLine_2
	banksel	temp_wr  ; changement de banque
	movlw	0xC0	 ; les huit bits de la donnée littérale sont chargées dans le registre w
	movwf	temp_wr	 ; contenu de w mis dans f
	call	i_write	 ; on appel i_write
	return
	GLOBAL	LCDLine_2
	
d_write					;write data
	call	LCDBusy     ; appel LCD busy
	bsf	STATUS, C		; le bit de position C du registre Status (qui permet de changer de banque) est mis à 1
	call	LCDWrite    ; appel de LCDWrite
	banksel	TXREG		;move data into TXREG 
	movwf	TXREG
	banksel	TXSTA
	btfss	TXSTA,TRMT  ; test du bit TMRA du registre TXSTA
	goto	$-1     	; si ce bit est a 0, on va à $-1
	banksel	PORTA		; changement de bnque , passag en banque 0 
	return				; return
	GLOBAL	d_write      
	
i_write					;write instruction
	call	LCDBusy     ; appel de LCDBusy
	 bcf	STATUS, C   ;  remise a zéro du byte C de status
	call	LCDWrite	; appel de LCDWrite
	return				;return
 	GLOBAL	i_write		;

rlcd	macro	MYREGISTER
 IF MYREGISTER == 1   ;si my register ==1
bsf	STATUS, C		;  le bit C de status est mis  1
	call	LCDRead  ; appel de LCDRead
 ELSE
	bcf	STATUS, C       ; le bit c de status est mis a 0
	call	LCDRead     ; appel lcdread
 ENDIF					;????
	endm				;?????
;****************************************************************************




; *******************************************************************
LCDInit 			;Initialisation 

	banksel TRISD 		; changement de banque (banque1) 
	bcf		TRISD,7 	; bit 7 mise a 0 du registre TRISD
	banksel	PORTD    	; changement de banque (banque0)
	bsf		PORTD,7  	; bit de port D mis a 1
	call	Delay30ms  	; appel d'un délai (met en pause le programme) délai de 30ms
	clrf	PORTA      	;le port A est mis a 0
	
	banksel	TRISA		;configure control lines passage en banque 1
	bcf	LCD_E_DIR		; mise a zéro de LCD_E_DIR
	bcf	LCD_RW_DIR		;mise à zéro de  LCD_E_DIR
	bcf	LCD_RS_DIR		; mise a zéro LCD_RS_DIR
	
	movlw	b'00001110'    ; les 8 bits de la donnée littérale sont chargés dans le registre W  
	banksel	ADCON1			; changement de banque  (banque1)
	movwf	ADCON1			; contenu du registre w est placé dans ADCON1

	movlw	0xff			; Wait ~15ms @ 20 MHz  Les 8 bits de la donnée littérale sont chargés dans le registre W.
	banksel	COUNTER
	movwf	COUNTER
	movlw	0xFF
	banksel	delay
	movwf	delay
	call	DelayXCycles
	decfsz	COUNTER, F
	goto	$-3
;0x02------------------	
;	movlw	b'00110000'		;#1 Send control sequence 
	movlw	b'00100000'		;#1 Send control sequence 
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble

	movlw	0xff			;Wait ~4ms @ 20 MHz
	movwf	COUNTER
	movlw	0xFF
	movwf	delay
	call	DelayXCycles
	decfsz	COUNTER, F
	goto	$-3
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;28---2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;	movlw	b'00110000'		;#2 Send control sequence
	movlw	b'00100000'		;#2 Send control sequence
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble

	movlw	0xFF			;Wait ~100us @ 20 MHz
	movwf	delay
	call	DelayXCycles
;28--08						
;	movlw	b'0011000'		;#3 Send control sequence
	movlw	b'10000000'
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble

		;test delay
	movlw	0xFF			;Wait ~100us @ 20 MHz
	movwf	delay
	call	DelayXCycles

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;0C--0
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;	movlw	b'00100000'		;#4 set 4-bit
	movlw	b'00000000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble
;0x0C
	movlw	b'11000000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble



	call	Delay1ms
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;0x01
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;	movlw	b'00100000'		;#4 set 4-bit
	movlw	b'00000000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble
;0x01
	movlw	b'00010000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;0x02
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	movlw	b'00000000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble
;0x02
	movlw	b'00100000'		;#4 set 4-bit
	movwf	temp_wr
	bcf	STATUS,C
	call	LCDWriteNibble
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;	rcall	LCDBusy			;Busy?
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms
	call	LongDelay ;2ms

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;	call	LCDBusy			;Busy?
;				
;	movlw	b'00101000'		;#5   Function set
;	movwf	temp_wr
;	call	i_write
;
;	movlw	b'00001101'		;#6  Display = ON
;	movwf	temp_wr
;	call	i_write
;			
;	movlw	b'00000001'		;#7   Display Clear
;	movwf	temp_wr
;	call	i_write
;
;	movlw	b'00000110'		;#8   Entry Mode
;	movwf	temp_wr
;	call	i_write	
;
;	movlw	b'10000000'		;DDRAM addresss 0000
;	movwf	temp_wr
;	call	i_write

;	movlw	b'00000010'		;return home
;	movwf	temp_wr
;	call	i_write

	movlw	0x4E
	movwf	temp_wr
	call	LCDBusy
	bsf	STATUS, C	
	call	LCDWrite


	call	Delay1ms
	return

	GLOBAL	LCDInit	
; *******************************************************************








;****************************************************************************
;     _    ______________________________
; RS  _>--<______________________________
;     _____
; RW       \_____________________________
;                  __________________
; E   ____________/                  \___
;     _____________                ______
; DB  _____________>--------------<______
;
LCDWriteNibble
	btfss	STATUS, C		; Set the register select
	bcf	LCD_RS
	btfsc	STATUS, C	
	bsf	LCD_RS

	bcf	LCD_RW			; Set write mode

	banksel	TRISD
	bcf	LCD_D4_DIR		; Set data bits to outputs
	bcf	LCD_D5_DIR
	bcf	LCD_D6_DIR
	bcf	LCD_D7_DIR

	NOP				; Small delay
	NOP

	banksel	PORTA
	bsf	LCD_E			; Setup to clock data
	
	btfss	temp_wr, 7			; Set high nibble
	bcf	LCD_D7	
	btfsc	temp_wr, 7
	bsf	LCD_D7
	btfss	temp_wr, 6
	bcf	LCD_D6	
	btfsc	temp_wr, 6
	bsf	LCD_D6
	btfss	temp_wr, 5
	bcf	LCD_D5	
	btfsc	temp_wr, 5
	bsf	LCD_D5
	btfss	temp_wr, 4
	bcf	LCD_D4
	btfsc	temp_wr, 4
	bsf	LCD_D4	

	NOP
	NOP

	bcf	LCD_E			; Send the data

	return
; *******************************************************************





; *******************************************************************
LCDWrite
;	call	LCDBusy
	call	LCDWriteNibble
	BANKSEL	temp_wr
	swapf	temp_wr, f
	call	LCDWriteNibble
	banksel	temp_wr
	swapf	temp_wr,f

	return

	GLOBAL	LCDWrite
; *******************************************************************





; *******************************************************************
;     _____    _____________________________________________________
; RS  _____>--<_____________________________________________________
;               ____________________________________________________
; RW  _________/
;                  ____________________      ____________________
; E   ____________/                    \____/                    \__
;     _________________                __________                ___
; DB  _________________>--------------<__________>--------------<___
;
LCDRead
	banksel	TRISD
	bsf	LCD_D4_DIR		; Set data bits to inputs
	bsf	LCD_D5_DIR
	bsf	LCD_D6_DIR
	bsf	LCD_D7_DIR		

	BANKSEL	PORTA
	btfss	STATUS, C		; Set the register select
	bcf	LCD_RS
	btfsc	STATUS, C	
	bsf	LCD_RS

	bsf	LCD_RW			;Read = 1

	NOP
	NOP			

	bsf	LCD_E			; Setup to clock data

	NOP
	NOP
	NOP
	NOP

	btfss	LCD_D7			; Get high nibble
	bcf	temp_rd, 7
	btfsc	LCD_D7
	bsf	temp_rd, 7
	btfss	LCD_D6			
	bcf	temp_rd, 6
	btfsc	LCD_D6
	bsf	temp_rd, 6
	btfss	LCD_D5			
	bcf	temp_rd, 5
	btfsc	LCD_D5
	bsf	temp_rd, 5
	btfss	LCD_D4			
	bcf	temp_rd, 4
	btfsc	LCD_D4
	bsf	temp_rd, 4

	bcf	LCD_E			; Finished reading the data

	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP
	NOP

	bsf	LCD_E			; Setup to clock data

	NOP
	NOP

	btfss	LCD_D7			; Get low nibble
	bcf	temp_rd, 3
	btfsc	LCD_D7
	bsf	temp_rd, 3
	btfss	LCD_D6			
	bcf	temp_rd, 2
	btfsc	LCD_D6
	bsf	temp_rd, 2
	btfss	LCD_D5			
	bcf	temp_rd, 1
	btfsc	LCD_D5
	bsf	temp_rd, 1
	btfss	LCD_D4			
	bcf	temp_rd, 0
	btfsc	LCD_D4
	bsf	temp_rd, 0

	bcf	LCD_E			; Finished reading the data

FinRd
	return
; *******************************************************************






; *******************************************************************
LCDBusy
	call	LongDelayLast
;	call	LongDelay
	return
				; Check BF
	rlcd	LCD_INS
	btfsc	temp_rd, 7
	goto	LCDBusy
	return

	GLOBAL	LCDBusy
; *******************************************************************






; *******************************************************************
DelayXCycles
	decfsz	delay, F
	goto	DelayXCycles
	return
; *******************************************************************
	
Delay1ms			;Approxiamtely at 4Mhz
	banksel	delay
	clrf	delay
Delay_1
	nop
	decfsz	delay
	goto	Delay_1
	return




Delay30ms	;more than 30 at 4 Mhz	
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms

	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms

	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	return
LongDelay:
	call	Delay1ms	
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	return

LongDelayLast
	call	Delay1ms
	call	Delay1ms
	call	Delay1ms
	return

	END
