#include <Smartcar.h>
Gyroscope gyro;
Odometer encoderLeft, encoderRight;
Car car;
SR04 front;
SR04 back;

GP2Y0A21 sideFrontIR; //measure distances between 12 and 78 centimeters
const int SIDE_FRONT_PIN = A8;
const float fSpeed = 0.5; //a ground speed (m/sec) for going forward
const float bSpeed = -2; //a ground speed (m/sec)y for going backward
const int lDegrees = -75; //degrees to turn left
const int rDegrees = 75; //degrees to turn right
const int encoderLeftPin = 2;
const int encoderRightPin = 3;
const int TRIGGER_PIN = 6; //D6
const int ECHO_PIN = 7; //D7
const int TRIGGER_PIN1 = A12;
const int ECHO_PIN1 = A13;
int count = 0;


void setup() {
  gyro.attach();
  sideFrontIR.attach(SIDE_FRONT_PIN);
  front.attach(TRIGGER_PIN, ECHO_PIN); //trigger pin, echo pin
  back.attach(TRIGGER_PIN1, ECHO_PIN1);
  encoderLeft.attach(encoderLeftPin);
  encoderRight.attach(encoderRightPin);
  delay(1500);
  Serial.begin(9600);
  Serial1.begin(9600);
  Serial3.begin(9600);
  gyro.begin(90);
  Gyroscope gyro();
  car.begin(encoderLeft, encoderRight); //initialize the car using the encoders

car.setMotorSpeed(66,66);
delay(30);
 car.setMotorSpeed(33,55);
  
  
}

void loop(){
  
Serial.println(back.getDistance());
car.updateMotors();
ir();



  
}


boolean check = false;

void ir(){

  if(check == false){
    
  if(front.getDistance()>20){
  encoderLeft.begin();
  encoderRight.begin();
  check = true;
 }
 }
  
  if(check == true && front.getDistance()>20){
    
    if(encoderLeft.getDistance()>60 && encoderRight.getDistance()>60){
      car.setMotorSpeed(0,0);
      parking();
   
  }else if(front.getDistance()<20 && encoderLeft.getDistance()<60 && encoderRight.getDistance()<60 ){
    check = false;
  }
  
 }

}



void distance(){

 // Serial.println(front.getDistance();
 // Serial.println(sideFrontIR.getDistance());
 
  if(back.getDistance() < 10 && back.getDistance() > 0 ){
   /* car.setMotorSpeed(28,40);
    delay(100);
    car.setMotorSpeed(20,27);
    delay(1000);*/
    car.setSpeed(0);
    
  
  }
} 





void parking(){

 while(sideFrontIR.getDistance()<14){
  
   car.setMotorSpeed(-32, -39);
   delay(10);
    car.setMotorSpeed(-20,-31);
  }  
  car.setMotorSpeed(-20,-31);
  delay(200);

/*
 car.setMotorSpeed(-25,-33);
 car.setAngle(lDegrees);
delay(1000);
*/


car.setMotorSpeed(-50,50);
delay(650); 

/*
while(back.getDistance()>7 || back.getDistance()==0){
  car.setMotorSpeed(-20,-31);
//delay(1200);
}

*/

car.setMotorSpeed(-22,-36);
delay(1300);

car.setMotorSpeed(50,-50);
delay(550);

/*
while(back.getDistance()>7 || back.getDistance()==0){
  car.setMotorSpeed(-22,-36);
//delay(1200);
}
*/



/*
while(back.getDistance()!=front.getDistance()){
  if(back.getDistance()>front.getDistance()){
  car.setMotorSpeed(20,-33);
  }
  else if(back.getDistance()<front.getDistance()){
    car.setMotorSpeed(-20,33);
  }
}
*/
car.setMotorSpeed(0,0);






}











