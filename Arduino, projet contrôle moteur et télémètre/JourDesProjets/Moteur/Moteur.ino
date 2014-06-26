#include <Wire.h>
#include <Adafruit_MCP23017.h>
#include <Adafruit_RGBLCDShield.h>
#define WHITE 0x7

const int avant=12;
const int arriere= 13;
const int C1C4=5;
const int C2C3=6;
const int bipeur=10;
const int ralentit=7;
int vitesse=0;
int puissance;
const int maxi=255;

Adafruit_RGBLCDShield lcd = Adafruit_RGBLCDShield();

void setup(){
pinMode(C1C4, OUTPUT);
pinMode(C2C3,OUTPUT);
pinMode(avant, INPUT);
pinMode(arriere,INPUT);
pinMode(ralentit,INPUT);
pinMode(bipeur,OUTPUT);
Serial.begin(9600);
lcd.begin(16, 2);
lcd.setCursor(0, 0);
lcd.setBacklight(WHITE);
lcd.setCursor(0,0);
lcd.print("Sens");
lcd.setCursor(0,1);
lcd.print("Puissance:");
lcd.setCursor(14,1);
lcd.print("%");
}

void loop(){
  
  puissance = ((vitesse*100)/255);
  lcd.setCursor(10,1);     
 
  // Affichage de la puissance
if ( puissance < 100 ){ 
      if ( puissance < 10){     // puissance <10
        lcd.print("00");
        lcd.setCursor(12,1);
        lcd.print(puissance);        
      }
      else {    
      lcd.print("0"); // 10 < puissance <100
      lcd.setCursor(11,1);
      lcd.print(puissance); }}
      
else {
      lcd.print(puissance);  }  // puissance=100 
      lcd.print("");
  
  
  digitalWrite(bipeur,HIGH);
  delay(100);
  
  if (digitalRead(ralentit)==1) {
     vitesse=240; }
  else { vitesse =255; }
  
  if (digitalRead(avant)==1){
    
      analogWrite(C2C3,0);
      analogWrite(C1C4,vitesse);   
      lcd.setCursor(0,0);
      lcd.print("Sens");  
      lcd.setCursor(5,0);
      
      lcd.print("avant     ");
      digitalWrite(bipeur,LOW);
      delay(100);
          
     
    
  }
  if (digitalRead(arriere)==1){
     
      analogWrite(C1C4,0);     
      analogWrite(C2C3,vitesse);
      lcd.setCursor(0,0);
      lcd.print("Sens");
      lcd.setCursor(5,0);
      lcd.print("arriere  ");
      
      digitalWrite(bipeur,LOW);
      delay(100); 
   
  }
   
  if ( digitalRead(arriere)==0) { 
      if (digitalRead(avant)==0) {          
          vitesse=0;
          analogWrite(C1C4,0);
          analogWrite(C2C3,0);
          delay(500);
          lcd.setCursor(0,0);
          lcd.print("     Youpi     ");
          lcd.print("                    "); }

  }
   
       
   
  
}
