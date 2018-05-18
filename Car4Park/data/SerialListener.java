package data;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialListener implements SerialPortEventListener {

	SerialPort serialPort;
	byte buffer[];
	String data;
	StringBuilder message = new StringBuilder();
	int frontSensor, sideSensor, backSensor;
	
	SerialListener(SerialPort serialPort){
		this.serialPort = serialPort;
	}
	
	@Override
	public void serialEvent(SerialPortEvent event) {
		if(event.isRXCHAR() && event.getEventValue() > 0) {
		
            try {
            	buffer = serialPort.readBytes();
            	for (byte b: buffer) {
            		if (b == '\n' && message.length() > 0) {
            			
            			char sensor = message.toString().charAt(0);
            			switch(sensor) {
            			
            			case 'f': 
            				frontSensor = Integer.parseInt(message.toString().replaceAll("[\\D]", ""));
            				System.out.println(frontSensor);
            				break;
            			case 's': 
            				sideSensor = Integer.parseInt(message.toString().replaceAll("[\\D]", ""));
            				System.out.println(sideSensor);
            				break;
            			case 'b':
            				backSensor = Integer.parseInt(message.toString().replaceAll("[\\D]", ""));
            				System.out.println(backSensor);
            				break;
            			default:
            				break;
            			}
            			message.setLength(0);
            		} else {
            			
            			message.append((char)b);
            		}
            	}
                /*String receivedData = serialPort.readString(event.getEventValue());
                System.out.println("Received response: " + receivedData);*/
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving string from port: " + ex);
            }
        }
		
	}
	
}