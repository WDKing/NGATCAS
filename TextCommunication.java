import java.util.Date;
import java.util.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
/**
 * TextCommunicator that references the NearList.
 * 
 * @author Christi Kazakov
 * @author Shelley King
 */
public class TextCommunication   {
    BufferedReader inputStream = null;
    BufferedWriter outputStream = null;
    String warningAlert;
    String fileName;
    Aircraft plane, myPlane;
    int warning;

    public TextCommunication(Aircraft plane, Aircraft thisPlane, int warning){
        this.plane = plane;
        this.myPlane = thisPlane;
        this.warning = warning;
        fileName = myPlane.getId().toString();
    }
    public TextCommunication(){
        
    }
   
    public void listener() throws Exception {  
    try {
    inputStream = new BufferedReader(new FileReader(fileName));
    outputStream = new BufferedWriter(new FileWriter(fileName));
    String line = inputStream.readLine();
        while(line != null){
            line = inputStream.readLine();
            }
    }
    catch(FileNotFoundException error){
        System.out.println("Error: File Not Found");
        }
    catch (IOException e) {
        e.printStackTrace();
        }
    }
  
    public String receiver(Object data){
        
        return data.toString();
    }
    
    public void send(String message) throws Exception{
        outputStream.write(message);
    }
    
    public void log(String log) throws Exception {
        // Instantiate a Date Object for the date and time
        Date date = new Date();
        // display time and date using toString()
        String str = String.format("Log : %tc", date );
        //Different levels of Warning based on the data taken in.
        String warningLevel1 = "YELLOW";
        String warningLevel2 = "ORANGE";
        String warningLevel3 = "RED";
        //logs which warning came in and what time
        // **Should show the plane ID and message **
        if(warning == 1){
           outputStream.write(str + "  " + warningLevel1 + " " + plane.getId());
        }
        else if(warning == 2){
            outputStream.write(str + "  " + warningLevel2 + " " + plane.getId());
        }
        else if(warning == 3){
            outputStream.write(str + "  " + warningLevel3 + " " + plane.getId());
        }    
        else{
            outputStream.write("No plane warning level received");
        }
    }    
    
}
