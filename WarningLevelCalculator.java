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
    
    public void parseList(){
        /*
        for(int i = 0; i < this.nearList.length; i++){
            
            if(checkCollision(thisPlane, this.nearList[i])){
                //do the math needed for calculating warnings
                //this math includes finding out how far away 
                //the point is and determining the level based
                //on that distance
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
    
    public boolean testCollisionPoint(Collision collision){
        //check to see if the collision is worth looking at
        //i.e. if its too far away, its not a point of interest
        return false;
    }
    
    public void setWarningLevelYellow(Aircraft plane){
        //generate instructions??
    }
     public void setWarningLevelOrange(Aircraft plane){
        //generate instructions??
    }
      public void setWarningLevelRed(Aircraft plane){
        //generate instructions??
    }
}
