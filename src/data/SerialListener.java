package data;

import Model.dataStore;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialListener implements SerialPortEventListener {

    SerialPort serialPort;
    byte buffer[];
    String data;
    StringBuilder message = new StringBuilder();
    dataStore carVal;

    public SerialListener(SerialPort serialPort, dataStore carVal){
        this.serialPort = serialPort;
        this.carVal = carVal;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0 ) {

            try {
                buffer = serialPort.readBytes();
                for (byte b: buffer) {
                    if (b == '\n' && message.length() > 0) {

                        char sensor = message.toString().charAt(0);
                        switch(sensor) {

                            case 'f':
                                carVal.setFront(Integer.parseInt(message.toString().replaceAll("[\\D]", "").trim()));
                                // System.out.println("Front is: "+carVal.getFront());
                                break;
                            case 's':
                                carVal.setSide(Integer.parseInt(message.toString().replaceAll("[\\D]", "").trim()));
                                //System.out.println("Side is: "+carVal.getFront());
                                break;
                            case 'b':
                                carVal.setBack(Integer.parseInt(message.toString().replaceAll("[\\D]", "").trim()));
                                //System.out.println("Back is: "+carVal.getBack());
                                break;
                            default:
                                break;
                        }
                        message.setLength(0);
                    } else {

                        message.append((char)b);
                    }
                }
               /* String receivedData = serialPort.readString(event.getEventValue());
                System.out.println("Received response: " + receivedData);*/
            }
            catch (SerialPortException ex) {
                System.out.println("Error in receiving string from port: " + ex);
            }
        }

    }

}
