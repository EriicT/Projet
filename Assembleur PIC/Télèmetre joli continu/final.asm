;********************************************************************************

		LIST P=16F877A ; directive qui definit le processeur utilise
		#include <P16F877A.INC> ; fichier de definition des constantes

;******************************************************************************

; BITS DE CONFIGURATION

;******************************************************************************

		__CONFIG _HS_OSC & _WDT_OFF & _CP_OFF & _CPD_OFF & _LVP_OFF

;********************************************************************************
; DEFINITIIONS DE VARIABLE *

;********************************************************************************
duree EQU 0xCD; variable de duree de 50 µs ( 205 )
EXTERN	LCDInit, temp_wr, d_write, i_write, LCDLine_1, LCDLine_2	;importation des fonctions d'affichage du LCD.
EXTERN	UMUL0808L, UDIV1608L, AARGB0, AARGB1, BARGB0				;importation des fonctions de calculs pour l'équation de la distanc		
coeff		EQU		d'70'
variables	UDATA 	0x30		;Déclaration de la section contenant des variables non initialisées.
blanc   EQU 0xFF ; variable de duree de 256 µs
adblanc EQU 0x21 ; adresse d'un registre vide qui servira de deompte

repetition EQU 0x0F ; valeur du temsp de répétition
adrepetition EQU 0x22 ; adresse de la valeur 
#DEFINE signal PORTE,2
#define	selectRB0	PORTB,0		;Push-button RB0 on PCB
#define	selectRA4 PORTA,4		;Push-button RA4 on PCB



;******************************;
; Réservation de la mémoire à  ;
;  partir de l'adresse 0x30    ;
;******************************;

ptr_pos		RES 	1			;Réservation de la mémoire (à l'adresse 0x30) pour la variable "ptr_pos".
ptr_count	RES 	1			;Réservation de la mémoire (à l'adresse 0x31) pour la variable "ptr_count".
temp_1		RES 	1
temp_2		RES 	1
temp_3		RES 	1
cmd_byte	RES 	1
temperature	RES 	1
LSD			RES 	1
MsD			RES 	1
MSD			RES 	1
seconds		RES 	1
minutes		RES 	1
hours		RES 	1

NumH		RES		1
NumL		RES 	1
TenK		RES 	1
Thou		RES 	1
Hund		RES 	1
Tens		RES 	1
Ones		RES 	1


;******************************;
;    Fin de la réservation     ;
;******************************;
;********************************************************************************
; DEMARRAGE SUR RESET *
;********************************************************************************
		org 0x0 ; Adresse de depart apres reset
		goto debut	

		org 0x10 ; adresse de debut du programme
PROG1 	CODE

stan_t	CODE	0x100			;start standard table at ROM 0x100	
stan_table						;table for standard code
			addwf	PCL,f
		dt	"   Distance =   ";0
		dt	"Prog Menu Credit"  ;16
		dt	"<RA4>      <RB0>"	;32
		dt	"          mm    "	;48
		dt  " Ce projet vous "	;64
		dt  "est presente par"  ; 80
		dt	" Florian Silva  "  ; 96
		dt	" et Eric Tran   "  ; 112
		dt	" avec l'aide de "  ; 128
		dt	"Christian Ripoll"  ;144
		dt  "Ferial Virolleau"  ;160
		dt  " et Avid Naemi  "  ; 176


		
debut

;********************************************************************************
; PROGRAMME PRINCIPAL *
;*******************************************************************************
			call init
	

		banksel	TXSTA			;initialize USART		
		movlw	B'10100100'		;Master mode, 8-bit, Async, High speed
		movwf	TXSTA
		movlw	.25				;9.6Kbaud @ 4MHz
		movwf	SPBRG
		banksel	RCSTA
		movlw	B'10010000'
		movwf	RCSTA

;******************;
; Mot de bienvenue ;
;******************;   

menu       
		banksel	ptr_pos			; Accès à l'adresse 0x30 (où se strouve la variable ptr_pos).
		movlw	.16				; Envoi "   Microchip    " to LCD.
		movwf	ptr_pos			
		call	stan_char_1		; Appel du sous-programme d'affichage de la ligne 1.
		banksel	ptr_pos
		movlw	.32				;send "   Telemetre    " to LCD.
		movwf	ptr_pos			
		call	stan_char_2		; Appel du sous-programme d'affichage de la ligne 2. 
		call	delay_1s		; Attente pour permettre à l'utilisateur de lire l'affichage.


choix
		BTFSC	selectRB0			;wait for RA4 release
		goto    suite
		goto    presentation

suite
		BTFSC	selectRA4			;wait for RA4 release
		goto    choix
		goto   	programme



programme 

	
	
			bcf PIR1,CCP1IF
			bcf INTCON,TMR0IF
			clrf CCPR1L
			clrf CCPR1H
			clrf TMR1L
			clrf TMR1H
			bsf T1CON,TMR1ON

; Premier créneau
			bsf signal ; mise à1 de la sortie
			movlw duree ; Chargement de la duree dans le registre de travail
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr1
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr1 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau
			bcf signal ; mise ?0 de la sortie
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr11
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr11 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau





; Deuxième créneau
			bsf signal ; mise ?1 de la sortie
			movlw duree ; Chargement de la duree dans le registre de travail
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr2
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr2 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau
			bcf signal ; mise ?0 de la sortie
		
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr22
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr22 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau



;Troisème créneau
			bsf signal ; mise ?1 de la sortie
			movlw duree ; Chargement de la duree dans le registre de travail
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr3
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr3 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau
			bcf signal ; mise ?1 de la sortie
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr33
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr33 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau


; Quatrième créneau
			bsf signal ; mise ?1 de la sortie
			movlw duree ; Chargement de la duree dans le registre de travail
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr4
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr4 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau
			bcf signal ; mise ?1 de la sortie		
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr44
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr44 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau


; Cinquième créneau
			bsf signal ; mise ?1 de la sortie
			movlw duree ; Chargement de la duree dans le registre de travail
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr5
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr5 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau
			bcf signal ; mise ?1 de la sortie		
			movwf TMR0 ; Chargement du registre de travail dans TMR0
boucle_tmr55
			btfss INTCON,TMR0IF ; Test bit T0IF de INTCON
			goto boucle_tmr55 ; boucle d'attente si timer non nul
			bcf INTCON,TMR0IF ; RAZ du drapeau




			


			
  ;***************** RECEPTION ******************************  

testflag		
			BTFSS PIR1,CCP1IF    ; test du port CCP1
			goto testflag

interruption
			banksel CCPR1H   	; rangement des valeurs high	
			movfw CCPR1H	
			banksel AARGB0	
			movwf AARGB0

			banksel CCPR1L
			movfw CCPR1L		; rangement des valeurs low
			banksel AARGB1
			movwf AARGB1
            
			banksel PIR1
			bcf PIR1,CCP1IF       ; RAZ du flag

; **********  AFFICHAGE *******************

;************** CALCUL ET AFFICHAGE DE LA DISTANCE **************;
;****************************************************************;
;****************************************************************;

		MOVLW   .48
		movwf ptr_pos
		call stan_char_2

		banksel	ptr_pos
		movlw	.00				; Envoi "   Distance =   " to LCD. 
		movwf	ptr_pos
		call	stan_char_1		; On affiche le mot "distance" sur la première ligne.

		banksel	BARGB0		
		movlw	coeff			; Le contenu de "vitesse" est placé dans le registre W.
		movwf	BARGB0			; Le contenu du registre W est placé dans "BARGB0", notre deuxième facteur.
		bcf		PCLATH,4		
		bsf		PCLATH,3

		call	UDIV1608L  ;UMUL0808L		 Appel de la fonction de multiplication.

		clrf	PCLATH					
		movf	AARGB0,w		; Les 8 bits de poid forts, du resulat, sont placés dans le registre W.
		movwf	NumH			; Le contenu du registre W est placé dans "NumH".
		movf	AARGB1,w		; Les 8 bits de poid faible, du résultat, sont placés dans le registre W.
		movwf	NumL			; Le contenu du registre W est placé dans "NumL".
		call	bin16_bcd		; Appel ce la fonction de conversion des 16 bits en BCD.
	
						
	


;**********************;
;  Ajout de 3 espaces  ;
; au début de la ligne ;
;**********************;
		call LCDLine_2
		movlw	A' '	
		movwf	temp_wr
		call	d_write
		movlw	A' '
		movwf	temp_wr
		call	d_write
		movlw	A' '
		movwf	temp_wr
		call	d_write
		movlw	A' '	
		movwf	temp_wr
		call	d_write
		movlw	A' '	
		movwf	temp_wr
		call	d_write
		movlw	A' '	
		movwf	temp_wr
		call	d_write


;************************;
; Ecriture des centaines ;
;************************;
		movf	Hund,w			; Le contenu de "Hund" est placé dans le registre W.
		call	bin_bcd			; Appel de la conversion en BCD.
		movf	LSD,w			; Le premier digit est placé dans le registre W.
		movwf	temp_wr			; Le contenu des W est rangé dans temp_wr, pour l'écriture.
		call	d_write			; Appel de la fonction d'écriture. Les centaines seront affichées.

;***********************;
; Ecriture des dizaines ;
;***********************;	
		movf	Tens,w			
		call	bin_bcd
		movf	LSD,w			
		movwf	temp_wr
		call	d_write

;*********************;
; Ecriture des unités ;
;*********************;
		movf	Ones,w
		call	bin_bcd
		movf	LSD,w
		movwf	temp_wr
		call	d_write
		banksel	TXREG			
		movlw	"\n"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1
		banksel	TXREG			 
		movlw	"\r"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1	
		call delay_1s
		call delay_1s

		

		BTFSC	selectRB0			;wait for RA4 release
		goto    programme
		goto    menu


  ;************ Presentation
 ;****************************
;**********************************************
presentation 
	
		banksel ptr_pos
		MOVLW   .64
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .80
		movwf ptr_pos
		call stan_char_2

		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s




		banksel ptr_pos
		MOVLW   .96
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .112
		movwf ptr_pos
		call stan_char_2

		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s


banksel ptr_pos
		MOVLW   .96
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .112
		movwf ptr_pos
		call stan_char_2

		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s



		banksel ptr_pos
		MOVLW   .96
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .112
		movwf ptr_pos
		call stan_char_2

	
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s


		banksel ptr_pos
		MOVLW   .128
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .144
		movwf ptr_pos
		call stan_char_2

		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s
		call delay_1s



		banksel ptr_pos
		MOVLW   .160
		movwf ptr_pos
		call stan_char_1
		banksel ptr_pos
		MOVLW   .176
		movwf ptr_pos
		call stan_char_2


		
		banksel	PORTB

la
		BTFSC	selectRB0			;wait for RA4 release
		goto    suite2
		goto    presentation
suite2
		BTFSC	selectRA4			;wait for RA4 release
		goto    la
		goto    menu
		

	

		
;************************** ROUTINES ******************************;
;******************************************************************;
;******************************************************************;
		
;----Affihage des caractères sur la première ligne----

stan_char_1
		call	LCDLine_1		; Positionnement du cursor à la première ligne.
		banksel	ptr_count		; Accès la variable "ptr_count".
		movlw	.16
		movwf	ptr_count     	; "ptr_count" vaut 16 car il y a 16 caractères par linge.
stan_next_char_1
		movlw	HIGH stan_table
		movwf	PCLATH
		movf	ptr_pos,w		
		call	stan_table		; On se positionne à l'adresse où se trouve le caractère positionné à la valeur de "ptr_pos" dans la ligne du tableau d'éclaré.
		movwf	temp_wr			; On place ce caractère dans "temp_wr" pour l'écrire.
		call	d_write			; Appel de la fonction pour écrire le caractère.

		banksel	ptr_pos
		incf	ptr_pos,f		; On se place sur le caractère suivant, dans notre ligne du tableau.
		decfsz	ptr_count,f		; On se place sur le caractère suivant, dans notre ligne de l'afficheur.
		goto	stan_next_char_1; On recommence jusqu'à ce que la ligne soit remplie.

		banksel	TXREG			
		movlw	"\n"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1
		banksel	TXREG			 
		movlw	"\r"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1	

	
	return

;----Affihage des caractères sur la deuxième ligne--------------------------
stan_char_2	
		call	LCDLine_2		 
		banksel	ptr_count
		movlw	.16			
		movwf	ptr_count
stan_next_char_2
		movlw	HIGH stan_table
		movwf	PCLATH
		movf	ptr_pos,w		
		call	stan_table		
		movwf	temp_wr			
		call	d_write			

		banksel	ptr_pos			
		incf	ptr_pos,f
		decfsz	ptr_count,f		
		goto	stan_next_char_2
	
		banksel	TXREG			
		movlw	"\n"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1
		banksel	TXREG			 
		movlw	"\r"			
		movwf	TXREG
		banksel	TXSTA
		btfss	TXSTA,TRMT		
		goto	$-1
	

		return
;----------------------------------------------------------------------


;------------------ 100ms Delay --------------------------------
delay_100ms
		banksel	temp_1
		movlw	0xFF
		movwf	temp_1
		movlw	0x83
		movwf	temp_2

		decfsz	temp_1,f
		goto	$-1
		decfsz	temp_2,f
		goto	$-3
		return

;---------------- 1s Delay -----------------------------------
delay_1s
		banksel	temp_1
		movlw	0xFF
		movwf	temp_1
		movwf	temp_2
		movlw	0x05

		movwf	temp_3
		decfsz	temp_1,f
		goto	$-1
		decfsz	temp_2,f
		goto	$-3
		decfsz	temp_3,f
		goto	$-5
		return	

;---------------- Binary (8-bit) to BCD -----------------------
;		255 = highest possible result
bin_bcd
		banksel	MSD
		clrf	MSD
		clrf	MsD
		movwf	LSD		;move value to LSD
ghundreth	
		movlw	.100		;subtract 100 from LSD
		subwf	LSD,w
		btfss	STATUS,C	;is value greater then 100
		goto	gtenth		;NO goto tenths
		movwf	LSD		;YES, move subtraction result into LSD
		incf	MSD,f		;increment hundreths
		goto	ghundreth	
gtenth
		movlw	.10		;take care of tenths
		subwf	LSD,w
		btfss	STATUS,C
		goto	over		;finished conversion
		movwf	LSD
		incf	MsD,f		;increment tenths position
		goto	gtenth
over				;0 - 9, high nibble = 3 for LCD
		movf	MSD,w		;get BCD values ready for LCD display
		xorlw	0x30		;convert to LCD digit
		movwf	MSD
		movf	MsD,w
		xorlw	0x30		;convert to LCD digit
		movwf	MsD
		movf	LSD,w
		xorlw	0x30		;convert to LCD digit
		movwf	LSD
		retlw	0



;---------------- Binary (16-bit) to BCD -----------------------
;		xxx = highest possible result
bin16_bcd			; Takes number in NumH:NumL 
	                       	; Returns decimal in TenK:Thou:Hund:Tens:Ones 
        swapf   NumH,w 
        andlw   0x0F             
        addlw   0xF0             
        movwf   Thou 
        addwf   Thou,f 
        addlw   0xE2 
        movwf   Hund 
        addlw   0x32 
        movwf   Ones 

        movf    NumH,w 
        andlw   0x0F 
        addwf   Hund,f 
        addwf   Hund,f 
        addwf   Ones,f 
        addlw   0xE9 
        movwf   Tens 
        addwf   Tens,f 
        addwf   Tens,f 

        swapf   NumL,w 
        andlw   0x0F 
        addwf   Tens,f 
        addwf   Ones,f 

        rlf     Tens,f 
        rlf     Ones,f 
        comf    Ones,f 
        rlf     Ones,f 

        movf    NumL,w 
        andlw   0x0F 
        addwf   Ones,f 
        rlf     Thou,f 

        movlw   0x07 
        movwf   TenK 

        movlw   0x0A                             ; Ten 
Lb1: 
        addwf   Ones,f 
        decf    Tens,f 
        btfss   3,0 
         goto   Lb1 
Lb2: 
        addwf   Tens,f 
        decf    Hund,f 
        btfss   3,0 
         goto   Lb2 
Lb3: 
        addwf   Hund,f 
        decf    Thou,f 
        btfss   3,0 
         goto   Lb3 
Lb4: 
        addwf   Thou,f 
        decf    TenK,f 
        btfss   3,0 
         goto   Lb4 

        retlw   0



		
;********************************************************************************
; 						INITIALISATION *
;********************************************************************************
init        					; initialisation du PORTE en sortie
			BANKSEL PORTE 		; passage en banque 0
			clrf PORTE 			; RAZ des bascules D du port B
			BANKSEL TRISE		; passage en banque 
			movlw b'000'        ; Chargement de la valeur  dans le registre de travail
			movwf TRISE 	 	; PORTE en sortie
			movlw b'11001000'  ; Chargement de la valeur dans 
			movwf	OPTION_REG
			BANKSEL PORTE ; retour en banque 0


			banksel PORTC 	; passage en banque 0

			; Initialisation de Conditions d'interruption de CCP1 
			movlw b'00000101' 	; detection front montant 
			movwf CCP1CON


            ; Initialisation du Port C, le Port RC2 en entrée
			banksel TRISC		; passage en banque 1
			bsf	TRISA,4			;make switch RA4 an Input
			bsf	TRISB,0			;make switch RB0 an Input
			movlw b'00000100'
			movwf TRISC
      


			; Mise à 0 des flags de PIR1 qui contient les flags
        	banksel PORTC 		
			clrf PIR1  			
			
			; Initialisation du Timer 1 : horloge interne et mise en route
			bcf T1CON,TMR1CS
			bsf	TRISA,4			;make switch RA4 an Input

			
			call LCDInit
	


	return
			
						
;***********************************************************************************

			END