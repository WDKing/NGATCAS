/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nextgen;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
//import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author Christi Kazakov
 * @author Shelley King
 */
public class TextCommunication   {
    BufferedReader inputStream = null;
    BufferedWriter outputStream = null;
    //String warningAlert;
    String fileName;
    Aircraft myPlane;

    public TextCommunication(Aircraft thisPlane){
        this.myPlane = thisPlane;
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
    

    public void listener() {
     
    throw new UnsupportedOperationException("Not implemented yet.");
    }

    public String receiver(Object data){
        throw new UnsupportedOperationException("Not implemented yet.");   
    }

    public void send(String message) {
        try {
            outputStream.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Not implemented yet."); 
    }

    
    public void log(Aircraft otherPlane, int warning) {
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
        try {
            if(warning == 1){
               outputStream.write(str + "  " + warningLevel1 + " " + otherPlane.getId());
            }
            else if(warning == 2){
                outputStream.write(str + "  " + warningLevel2 + " " + otherPlane.getId());
            }
            else if(warning == 3){
                outputStream.write(str + "  " + warningLevel3 + " " + otherPlane.getId());
            }
            else{
                outputStream.write("No plane warning level received");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}