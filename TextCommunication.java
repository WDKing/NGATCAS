/**
 *   The TextCommunication Class should setup a new File with a BufferedWriter and FileWriter
 *   It will receive data from the Warning Class, which will include an Aircraft and Warning
 *   which was already calculated. This will then be used to log all warning and messages 
 *   for back-up.
 */

package nextgen;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author Christi Kazakov
 * @author Shelley King
 */
public class TextCommunication   {
    BufferedReader inputStream = null;
    BufferedWriter outputStream = null;
    //String warningAlert;
    String fileName;
    Aircraft myPlane;

    public TextCommunication(){
        
    }
    public TextCommunication(Aircraft thisPlane){
        this.myPlane = thisPlane;
        // fileName will be shown as our Plane ID for the files
        fileName = myPlane.getId();
        //create file to be written to for logging
    try {
    outputStream = new BufferedWriter(new FileWriter(fileName));
    }
    catch(FileNotFoundException error){
        System.out.println("Error: File Not Found");
        }
    catch (IOException e) {
        e.printStackTrace();
        }
    }
    
    // Listener() listens for messages similar to texting from other Aircraft and Tower
    public void listener() {
     
    throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String receiver(Object data){
        throw new UnsupportedOperationException("Not implemented yet.");   
    }
    
    // Send will send messages to a file separate of the logging messaging.
    // Mimic sending messages to the Tower and Other Aircraft.
    public void send(String message) {
        try {
            outputStream.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Not implemented yet."); 
    }

    // Log will be sent an aircraft and warning 
    // and use the data for logging to this plane
    public void log(Aircraft otherPlane, int warning) {
        
        // Instantiate a Date Object for the date and time
        Date date = new Date();
        
        // Display time and date using toString()
        String str = String.format("Log : %tc", date );
        
        // Different levels of Warning based on the data taken in.
        String warningLevel1 = "YELLOW";
        String warningLevel2 = "ORANGE";
        String warningLevel3 = "RED";
        
        // Logs which warning came in and what time
        // **Should show the plane ID and message **
        try {
            // Outputs the initial log message start, warning levels 
                // and the other Plane ID which was sent in
            if(warning == 1){
               outputStream.write(str + "  " + warningLevel1 + " " + otherPlane.getId());
            }
            else if(warning == 2){
                outputStream.write(str + "  " + warningLevel2 + " " + otherPlane.getId());
            }
            else if(warning == 3){
                outputStream.write(str + "  " + warningLevel3 + " " + otherPlane.getId());
            }
            // end of all warnings, if no warnings sent in, outputs message.
            else{
                outputStream.write("No plane warning level received");
            }
        } 
        // End of try
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}