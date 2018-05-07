package SerialReader;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

public class SerialRead {

    public static void main(String args[]) throws Exception{
        IODevice device = new FirmataDevice("/dev/ttyACM0");
        device.start();
        device.ensureInitializationIsDone();

        Pin pin = device.getPin(13);
        pin.setMode(Pin.Mode.INPUT);

    }
}
