# MPLAB IDE generated this makefile for use with GNU make.
# Project: final.mcp
# Date: Fri Jun 21 10:49:21 2013

AS = MPASMWIN.exe
CC = 
LD = mplink.exe
AR = mplib.exe
RM = rm

final.cof : p16lcd.o p16math.o final.o
	$(CC) /p16F877A "p16lcd.o" "p16math.o" "final.o" /z__MPLAB_BUILD=1 /o"final.cof" /M"final.map" /W

p16lcd.o : p16lcd.asm C:/Program\ Files/Microchip/MPASM\ Suite/P16F877A.inc
	$(AS) /q /p16F877A "p16lcd.asm" /l"p16lcd.lst" /e"p16lcd.err" /o"p16lcd.o"

p16math.o : p16math.asm C:/Program\ Files/Microchip/MPASM\ Suite/P16F877A.inc
	$(AS) /q /p16F877A "p16math.asm" /l"p16math.lst" /e"p16math.err" /o"p16math.o"

final.o : final.asm C:/Program\ Files/Microchip/MPASM\ Suite/P16F877A.INC
	$(AS) /q /p16F877A "final.asm" /l"final.lst" /e"final.err" /o"final.o"

clean : 
	$(CC) "p16lcd.o" "p16lcd.err" "p16lcd.lst" "p16math.o" "p16math.err" "p16math.lst" "final.o" "final.err" "final.lst" "final.cof" "final.hex"

