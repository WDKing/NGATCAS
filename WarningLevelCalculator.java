import java.util.ArrayList;
/**
 * This class will calculate the warning level based upon the source aircraft and the array list of neary aircraft
 * 
 * @author Adam
 * @author William King
 */
public class WarningLevelCalculator {
    Aircraft[] nearList;
    Aircraft thisPlane;
    //CollisionCalculator collCalc;
    TextComm textComm;
    ArrayList<Collision> collisions;
    
    
    public WarningLevelCalculator(Aircraft thisPlane, Aircraft[] nearList){
        this.nearList = nearList;
        this.thisPlane = thisPlane;
        //collCalc = new CollisionCalculator(mine, nearList);
        textComm = new TextComm();
        collisions = new ArrayList();
    }
    
    /**
     * Constructor - Private
     */
    private WarningLevelCalculator(){}
    
    /**
     * Calculates the distance of the collision point (if any) and set the appropriate
     * warning level based upon this calculation.
     */
    public void parseList(){
        /*
        for(int i = 0; i < this.nearList.length; i++){
            
            if(checkCollision(thisPlane, this.nearList[i])){
                //use TextComm object to alert necessary interfaces
            }
            else{
                //set warning of this.nearList[i] to green
            }
            
        }
        */  
        System.out.println("Method Tested");
    }
    
    /**
     * Check to see if the aircraft will collide
     * Math calculations include finding the vector headings
     * Add aircraft to the nearby aircraft array
    public Collision detectCollision(Aircraft one, Aircraft two){
        return null;
    
    }
    
    /**
     * Test to see if the collision is a valid warning collision point.
     * ie. Distance is within warning area, no valid collision
     * 
     * param collision Collision object that contains the location (if any) of the 
     *       point of collision
     * return boolean value of true if the point of collision is a valid point,
     *       false if it is not a valid point
     */
    public boolean testCollisionPoint(Collision collision){
        return false;
    }
    
    /**
     * Completes all actions to set the current warning level to 
     */
    public void setWarningLevelGreen(Aircraft plane){
        // Set Gui to Green
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Yellow
     */
    public void setWarningLevelYellow(Aircraft plane){
        // Set Gui to Yellow
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Orange
     */
     public void setWarningLevelOrange(Aircraft plane){
        // Set Gui to Orange
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Red
     */
     public void setWarningLevelRed(Aircraft plane){
        // Set Gui to Red
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
}
