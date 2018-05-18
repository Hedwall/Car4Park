#include <Smartcar.h>

// Sensors used
Gyroscope gyro;
Odometer encoderLeft, encoderRight;
Car car;
SR04 front;
SR04 back;
SR04 side;

// Variables
const float fSpeed = 0.5; //a ground speed (m/sec) for going forward
const float bSpeed = -2; //a ground speed (m/sec)y for going backward
const int lDegrees = -75; //degrees to turn left
const int rDegrees = 75; //degrees to turn right

const int SIDE_FRONT_PIN = A8;
const int encoderLeftPin = 2;
const int encoderRightPin = 3;
const int TRIGGER_PIN = 6; //D6
const int ECHO_PIN = 7; //D7
const int TRIGGER_PIN1 = A12;
const int TRIGGER_PIN2 = A8;
const int ECHO_PIN2 = A9;
const int ECHO_PIN1 = A13;
<<<<<<< HEAD
<<<<<<< HEAD
int count = 0;
int motorSpeed = 45; //40% of the max speed
const int input[3] = {7, A9, A13}; //using a constant would keep you from making your error
int result[3];
=======
>>>>>>> 590e034b70c3cd2695c26b41801ed267238c9e15

int motorSpeed = 65; //40% of the max speed


=======

int motorSpeed = 65; //40% of the max speed


>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587


void setup() {
  // Attaching sensors
  gyro.attach();
  front.attach(TRIGGER_PIN, ECHO_PIN); //trigger pin, echo pin
  back.attach(TRIGGER_PIN1, ECHO_PIN1);
  side.attach(TRIGGER_PIN2, ECHO_PIN2);
  encoderLeft.attach(encoderLeftPin);
  encoderRight.attach(encoderRightPin);
  delay(1500);
  
  Serial.begin(9600);
  Serial1.begin(9600);
  Serial3.begin(9600);
  gyro.begin(90);
  
  int offset = gyro.calibrate();
  Gyroscope gyro(offset);

 
  car.begin(encoderRight, encoderLeft, gyro); //initialize the car using the encoders

<<<<<<< HEAD
<<<<<<< HEAD
 car.setMotorSpeed(30, 45);
 delay(10);
 car.setMotorSpeed(20,31);
=======
  car.setMotorSpeed(40,51);
   
>>>>>>> 590e034b70c3cd2695c26b41801ed267238c9e15
=======
  car.setMotorSpeed(40,51);
   
>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587


}

void loop(){
  
<<<<<<< HEAD
side.getDistance();
car.updateMotors();
<<<<<<< HEAD
// ir();
readAllSensors();
=======
gyro.update();
findSpot();
=======
>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587

sensorPrint();
car.updateMotors();
gyro.update();
findSpot();

>>>>>>> 590e034b70c3cd2695c26b41801ed267238c9e15
  
}


boolean check = false;

<<<<<<< HEAD
<<<<<<< HEAD
void readAllSensors(){
  for (byte y = 0; y < 3; y++)
  {
    result[y] = analogRead(input[y]);
  }
   Serial.print("result: front, side, back");
  for (byte y = 0; y < 3; y++)
  {
    
    Serial.print("\t");
    Serial.print(result[y]);
  }
  delay(500);
  }
  
void ir(){
=======
void findSpot(){
>>>>>>> 590e034b70c3cd2695c26b41801ed267238c9e15
=======
void findSpot(){
>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587

  if(check == false){
  
  if(side.getDistance()>20){
  encoderLeft.begin();
  encoderRight.begin();
  check = true;
 }
 }
  
  if(check == true && side.getDistance()>20){
    
    if(encoderLeft.getDistance()>45 && encoderRight.getDistance()>45){
      car.setMotorSpeed(0,0);
      parking();
   
  }else if(side.getDistance()<20 && encoderLeft.getDistance()<45 && encoderRight.getDistance()<45 ){
    check = false;
  }
  
 }

}

void parking(){

// When a spot is found rotate 45 degrees 
rotateOnSpot(-45);
delay(600);


// Back up into the spot until close enough to wall.
parkingFinal();
delay(300);

// Last rotation to be in line.
rotateOnSpot(45);
rotateOnSpot(45);

delay(300);
// Method to make the car stop at any chance. 
stopIt();

car.setMotorSpeed(0,0);

}

void parkingFinal(){
  
<<<<<<< HEAD
   while ( back.getDistance() > 15 || back.getDistance() == 0 ){
    
    car.setMotorSpeed(-25,-36);
    
    }
  
  }


void stopIt(){
while( back.getDistance() >= 0) {
  car.setMotorSpeed(0,0);
  car.stop();
  
  } 
  
  }

=======
   if ( back.getDistance() > 15 || back.getDistance() == 0 ){
    
    car.go(- (back.getDistance() * 0.5));
    
    
    }
 
  }


void stopIt(){
while( back.getDistance() >= 0) {
  car.setMotorSpeed(0,0);
  car.stop();
  
  } 
  
  }


void sensorPrint(){

  String sside = "s";
  String sfront ="f";
  String sback = "b";
  Serial.println(sfront + front.getDistance());
  Serial.println(sside + side.getDistance());
  Serial.println(sback + back.getDistance());
  
}
>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587





void rotateOnSpot(int targetDegrees) {
  targetDegrees %= 360; //put it on a (-360,360) scale
  if (!targetDegrees) return; //if the target degrees is 0, don't bother doing anything
  /* Let's set opposite speed on each side of the car, so it rotates on spot */
  if (targetDegrees > 0) { //positive value means we should rotate clockwise
    car.setMotorSpeed(motorSpeed, -motorSpeed); // left motors spin forward, right motors spin backward
  } else { //rotate counter clockwise
    car.setMotorSpeed(-motorSpeed, motorSpeed); // left motors spin backward, right motors spin forward
  }
  unsigned int initialHeading = gyro.getAngularDisplacement(); //the initial heading we'll use as offset to calculate the absolute displacement
  int degreesTurnedSoFar = 0; //this variable will hold the absolute displacement from the beginning of the rotation
  while (abs(degreesTurnedSoFar) < abs(targetDegrees)) { //while absolute displacement hasn't reached the (absolute) target, keep turning
    gyro.update(); //update to integrate the latest heading sensor readings
    int currentHeading = gyro.getAngularDisplacement(); //in the scale of 0 to 360
    if ((targetDegrees < 0) && (currentHeading > initialHeading)) { //if we are turning left and the current heading is larger than the
      //initial one (e.g. started at 10 degrees and now we are at 350), we need to substract 360, so to eventually get a signed
      currentHeading -= 360; //displacement from the initial heading (-20)
    } else if ((targetDegrees > 0) && (currentHeading < initialHeading)) { //if we are turning right and the heading is smaller than the
      //initial one (e.g. started at 350 degrees and now we are at 20), so to get a signed displacement (+30)
      currentHeading += 360;
    }
    degreesTurnedSoFar = initialHeading - currentHeading; //degrees turned so far is initial heading minus current (initial heading
    //is at least 0 and at most 360. To handle the "edge" cases we substracted or added 360 to currentHeading)
  }
  car.stop(); //we have reached the target, so stop the car
}

<<<<<<< HEAD




=======
>>>>>>> b37d4c59722a63ba11f0b099d308d6384882c587
