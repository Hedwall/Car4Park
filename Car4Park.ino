

#include <Smartcar.h>

Gyroscope gyro;
Odometer encoderLeft, encoderRight;
Car car;
SR04 front;
GP2Y0A21 sideFrontIR; //measure distances between 12 and 78 centimeters
SR04 back;

const int SIDE_FRONT_PIN = A8;
const float fSpeed = 0.5; //a ground speed (m/sec) for going forward
const float bSpeed = -2; //a ground speed (m/sec)y for going backward
const int lDegrees = -75; //degrees to turn left
const int rDegrees = 75; //degrees to turn right
const int encoderLeftPin = 2;
const int encoderRightPin = 3;
const int TRIGGER_PIN = 6; //D6
const int ECHO_PIN = 7; //D7
const int ECHO_PINb = A13;
const int TRIGGER_PINb = A12;
int count = 0;

void setup() {
  
  back.attach(TRIGGER_PINb,ECHO_PINb);
  gyro.attach();
  sideFrontIR.attach(SIDE_FRONT_PIN);
  front.attach(TRIGGER_PIN, ECHO_PIN); //trigger pin, echo pin
  encoderLeft.attach(encoderLeftPin);
  encoderRight.attach(encoderRightPin);
  //
  delay(1500);
  //Serial3.begin(9600);
  Serial.begin(9600);
  encoderLeft.begin();
  encoderRight.begin();
  gyro.begin(90);
  Gyroscope gyro();
  car.begin(encoderLeft, encoderRight); //initialize the car using the encoders
  // car.enableCruiseControl(); //using default PID values
 //car.enableCruiseControl(12,5,17,30); //using custom values and control frequency
 car.setMotorSpeed(30,30);
 delay(100);
 car.setMotorSpeed(66,100);
}

void loop() {
  
 //  int offset = gyro.calibrate();
 // gyro.update(offset);
  car.updateMotors();
  ir();
  // straight();
 // distance();
Serial.println(sideFrontIR.getDistance());
 

  }
 
void ir(){
 
  while(sideFrontIR.getDistance() > 20){
    delay(5); 
  count++;
    if(count > 100){
    car.setSpeed(0);
    }
    
  }
  
}





/*void straight (){

  Serial.println(gyro.getAngularDisplacement());
  if (gyro.getAngularDisplacement() > 0 && gyro.getAngularDisplacement() < 180){
    car.setAngle(-30);
  }

  if (gyro.getAngularDisplacement() < 360 && gyro.getAngularDisplacement() > 180){
    car.setAngle(30);
  }
}
*/
void distance (){
 // Serial.println(front.getDistance());
 // Serial.println(sideFrontIR.getDistance());
  if(front.getDistance() < 20 && front.getDistance() > 0 ){
    car.setSpeed(bSpeed);
    delay(1000);
    car.setSpeed(0);
  }

  if(front.getDistance() > 20){
    car.setSpeed(fSpeed);
  }
  delay (500);

}

