import java.util.ArrayList;
/**
 * This class will calculate the warning level based upon the source aircraft and the array list of neary aircraft
 * 
 * @author Adam Miller
 * @author William King
 */
public class WarningLevelCalculator {
    Aircraft[] nearList;
    Aircraft thisPlane;
    TextComm textComm;
    ArrayList<Collision> collisions;
    double dist;
    
    
    /**
     * Constructor - takes this plane, and a near list of other planes, and initializes values
     * of the object.
     * 
     * @param thisPlane the origin plane for which all calculations will be performed
     * @param nearList the list of nearby planes for which collisions will be detected
     */
    public WarningLevelCalculator(Aircraft thisPlane, Aircraft[] nearList){
        this.nearList = nearList;
        this.thisPlane = thisPlane;
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
        Collision temp = null;
        
        for(int i = 0; i < this.nearList.length; i++){
            //make sure there is a list to parse
            if(nearList == null){ break;}
            //get a collision point
            temp = detectCollision(thisPlane, nearList[i]);
            //make sure you have a collision point
            if(temp != null){
                //test the point to ensure its relevant
                if(testCollisionPoint(temp)){
                    //set warning levels accordingly
                    if(dist < 100 && dist > 60){
                        setWarningLevelYellow(nearList[i]);
                    }
                    if(dist < 60 && dist > 30){
                        setWarningLevelOrange(nearList[i]);
                    }
                    if(dist < 30){
                        setWarningLevelRed(nearList[i]);
                    }
                }
                else{
                setWarningLevelGreen(nearList[i]);
                }
            }
            
            else{
                setWarningLevelGreen(nearList[i]);
            }
        }
    }
    
    /**
     * Check to see if the aircraft will collide
     * Math calculations include finding the vector headings
     * Add aircraft to the nearby aircraft array
     * @param one first aircraft to use for calculating a possible collision
     * @param two second aircraft to use for calculating a possible collision
     * @return Collision object containg information about possible collision or 
     *        non-collision of the two aircraft involved.  
     */
    public Collision detectCollision(Aircraft one, Aircraft two){
        return null;
    }
    
    /**
     * Test to see if the collision is a valid warning collision point.
     * ie. Distance is within warning area, no valid collision
     * 
     * @param collision Collision object that contains the location (if any) of the 
     *       point of collision
     * @return boolean value of true if the point of collision is a valid point,
     *       false if it is not a valid point
     */
    public boolean testCollisionPoint(Collision collision){
        dist = thisPlane.getLocation() - collision.getLocation();
        if(dist < 100){
            return true;
        }
        else{ return false;}
    }
    
    /**
     * Completes all actions to set the current warning level to Green
     * @param plane the self aircraft for which the warning level should be set
     */
    public void setWarningLevelGreen(Aircraft plane){
        // Set Gui to Green
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Yellow
     * @param plane the self aircraft for which the warning level should be set
     */
    public void setWarningLevelYellow(Aircraft plane){
        // Set Gui to Yellow
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Orange
     * @param plane the self aircraft for which the warning level should be set
     */
     public void setWarningLevelOrange(Aircraft plane){
        // Set Gui to Orange
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
    
    /**
     * Completes all actions to set the current warning level to Red
     * @param plane the self aircraft for which the warning level should be set
     */
     public void setWarningLevelRed(Aircraft plane){
        // Set Gui to Red
        // Send instructions to PeripherallInterface
        // Send instructions to TextCommunication
    }
}
