#include <Smartcar.h>

Gyroscope gyro;
Odometer encoderLeft, encoderRight;
Car car;
SR04 front;
GP2Y0A21 sideFrontIR; //measure distances between 12 and 78 centimeters

const int SIDE_FRONT_PIN = A8;
const float fSpeed = 0.5; //a ground speed (m/sec) for going forward
const float bSpeed = -0.5; //a ground speed (m/sec)y for going backward
const int lDegrees = -75; //degrees to turn left
const int rDegrees = 75; //degrees to turn right
const int encoderLeftPin = 2;
const int encoderRightPin = 3;
const int TRIGGER_PIN = 6; //D6
const int ECHO_PIN = 7; //D7

void setup() {
  gyro.attach();
  sideFrontIR.attach(SIDE_FRONT_PIN);
  front.attach(TRIGGER_PIN, ECHO_PIN); //trigger pin, echo pin
  encoderLeft.attach(encoderLeftPin);
  encoderRight.attach(encoderRightPin);
  delay(1500);
  Serial3.begin(9600);
  Serial.begin(9600);
  encoderLeft.begin();
  encoderRight.begin();
  gyro.begin(90);
  int offset = gyro.calibrate();
  Gyroscope gyro(offset);
  car.begin(encoderLeft, encoderRight); //initialize the car using the encoders
  car.enableCruiseControl(); //using default PID values
 //car.enableCruiseControl(12,5,17,30); //using custom values and control frequency
  car.setSpeed(fSpeed);
}

void loop() {
  gyro.update();
  car.updateMotors();
  straight();
  //handleInput();
  //distance();
}

void straight (){
  
  if (gyro.getAngularDisplacement() > 1 && gyro.getAngularDisplacement() < 180){
    car.setSpeed(fSpeed);
    car.setAngle(-30);
  }

  if (gyro.getAngularDisplacement() < 350 && gyro.getAngularDisplacement() > 180){
    car.setSpeed(fSpeed);
    car.setAngle(5);
  }
}
void distance (){
 // Serial.println(front.getDistance());
 // Serial.println(sideFrontIR.getDistance());
  if(front.getDistance() < 10){
    car.setSpeed(bSpeed);
    delay(700);
    car.setSpeed(0);
  }

}

void handleInput() { //handle serial input if there is any
 
  if (Serial3.available()) {
    char input = Serial3.read(); //read everything that has been received so far and log down the last entry
    switch (input) {
      case 'l': //rotate counter-clockwise going forward
        car.setSpeed(fSpeed);
        car.setAngle(lDegrees);
        break;
      case 'r': //turn clock-wise
        car.setSpeed(fSpeed);
        car.setAngle(rDegrees);
        break;
      case 'f': //go ahead
        car.setSpeed(fSpeed);
        car.setAngle(0);
        break;
      case 'b': //go back
        car.setSpeed(bSpeed);
        car.setAngle(0);
        break;
      default: //if you receive something that you don't know, just stop
        car.setSpeed(0);
        car.setAngle(0);
    }
  }
}
