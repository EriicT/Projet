  
#include <Wire.h>
#include <Adafruit_MCP23017.h>
#include <Adafruit_RGBLCDShield.h>
#include <TimerOne.h>
//declaration des variables


const int emetteur = 2;
const int son = 4;
const int avant = 12;
const int arriere=13;
const int ralentit=7;
volatile  double t1 = 0;
double t2 = 0;
double duree = 0;
float demiperiode=0;
volatile  double distance ;
float frequence = 0;
int dist=30;
int distv=dist;
int validation=0;
double somme=0 ;
int m=0;
int finale;
int moins;
int plus;


Adafruit_RGBLCDShield lcd = Adafruit_RGBLCDShield();
#define WHITE 0x7

//configuration

void setup()
{
  Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.setCursor(0, 0);
  lcd.setBacklight(WHITE);
  lcd.print("Distance:");
  lcd.setCursor(14,0);
  lcd.print("cm");
  lcd.setCursor(0,1);
  lcd.print("Cmd:");
  
  pinMode(emetteur, OUTPUT);
  pinMode(son, OUTPUT);
  pinMode(avant,OUTPUT);
  pinMode(arriere,OUTPUT);
  pinMode(ralentit,OUTPUT);
  Serial.begin(9600);
  
   Timer1.initialize(10000); //10000 µs
Timer1.attachInterrupt(emission,100000); 
attachInterrupt(1, reception , FALLING) ;
}


void emission(){
  int i=0;
  t1 = micros();
  while ( i <8 )// soit 0.2ms 
  {
    digitalWrite(emetteur, HIGH);
    delayMicroseconds (9);
    digitalWrite(emetteur, LOW);
    delayMicroseconds (9);
    i = i+1; 
 }}


void reception(){
  t2 = micros();
  duree = t2 - t1;
  distance = (340 * 0.0001*duree)/2;
  

}


void loop()
{ 
 uint8_t buttons = lcd.readButtons(); 
 lcd.setCursor(7,1);
 if (validation == 1 ) { 
           //delay(100);  
           distv=dist;
           validation=0;           
           lcd.print("validee    "); }
                  
 if ( buttons & BUTTON_LEFT){
       dist=dist+1;
       
       if ( dist > 70 ){
               dist = 70;   }
         lcd.print("a valider");
         delay(150);
         }
 
 if ( buttons & BUTTON_RIGHT){
       dist=dist-1;
       if ( dist < 10 ){
               dist = 10;   }
       lcd.print("a valider");
       delay(150);
      }
 
 if ( buttons & BUTTON_DOWN){
      dist = 25;
      validation=0;   }
      
if ( buttons & BUTTON_SELECT){  
      validation = 1;   }



  //  frequence = -0.18*distance+5.9; 
  //  if (frequence < 0)
 //   {
 //     frequence=0;
 //   }
 //   demiperiode=((1/frequence)/2)*1000;
   
  //  digitalWrite(son, HIGH);
   // delay(demiperiode);
 // digitalWrite(son, LOW);
 // delay(demiperiode);
  
  finale = int((distance));
  lcd.setCursor(10,0);
  lcd.print(finale);
  lcd.setCursor(12,0);
  lcd.print("  " );
  lcd.setCursor(4,1);
  lcd.print(dist);
  moins=distv-3;
  plus=distv+3;
  
  if ( finale > moins ) {
      if ( finale < plus ) {
        digitalWrite(ralentit,HIGH);
      }
      }
  else {
    digitalWrite(ralentit,LOW);
  }
  
  
   if ( finale == distv) {
      digitalWrite(arriere,LOW);
      digitalWrite(avant,LOW);
    }
    else{
    if ( finale < distv ){
    digitalWrite(arriere,LOW);
    digitalWrite(avant,HIGH);}
    else {
    digitalWrite(avant,LOW);  
    digitalWrite(arriere,HIGH);}  }
    delay(30);
}



