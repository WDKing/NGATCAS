/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lauren
 */
public class Aircraft {

    
    private final String id;
    private final double[] location;
    private final double[] heading;
    private final int warningLevel;
    
    
    
    public Aircraft(String id, double[]location, double[]heading, int warningLevel){
        this.id = id;
        this.location = location;
        this.heading = heading;
        this.warningLevel= warningLevel;
    }
    
    public String getId() {
        return null;
    }
    
    public double[] getLocation() {
        return null;
    }
    
    public double[] getHeading() {
        return null;
    }
    
    public int getWarningLevel() {
        return -1;
    }
    
   
}
