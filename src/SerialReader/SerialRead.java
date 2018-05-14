package SerialReader;

import jssc.SerialPort;
import jssc.SerialPortException;

public class serialRead {


    public void getData() {
    SerialPort serialPort = new SerialPort("COM2");
    try
    {
        serialPort.openPort();//Open serial port
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        serialPort.writeBytes("C45".getBytes());
        serialPort.closePort();//Close serial port
    }
    catch(SerialPortException ex){
        System.out.println(ex);
    }
}

}
