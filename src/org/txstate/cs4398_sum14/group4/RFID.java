package org.txstate.cs4398_sum14.group4;
import javax.comm.*;
public class RFID implements SerialPortEventListener  {

	public void serialEvent(SerialPortEvent event){
		
		
	}
	
    /**
     * Handle output buffer empty events.
     * NOTE: The reception of this event is optional and not
     *       guaranteed by the API specification.
     * @param event The output buffer empty event
     */
    protected void outputBufferEmpty(SerialPortEvent event) {
        // Implement writing more data here
    }

    /**
     * Handle data available events.
     *
     * @param event The data available event
     */
    protected void dataAvailable(SerialPortEvent event) {
        // implement reading from the serial port here
    }
    
    private void ParseRFIDData (String data) {
    	
    }
}
