/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.ArrayList;

/**
 *
 * @author Adam
 */
public class WarningLevelCalculator {
    Aircraft[] nearList;
    Aircraft thisPlane;
    TextComm textComm;
    ArrayList<Collision> collisions;
    double dist;
    
    public WarningLevelCalculator(Aircraft thisPlane, Aircraft[] nearList){
        this.nearList = nearList;
        this.thisPlane = thisPlane;
        textComm = new TextComm();
        collisions = new ArrayList();
    }
    public WarningLevelCalculator(){
        
    }
    /**
     * 
     * @author Adam
     */
    public void parseList(){
        
        Collision temp;
        
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
     * 
     * @param one
     * @param two
     * @return 
     */
    public Collision detectCollision(Aircraft one, Aircraft two){
        
        return null;
    }
    
    /**
     * 
     * @param collision
     * @return 
     */
    public boolean testCollisionPoint(Collision collision){
        //all headings should be positive and all points of collision
        //will be in the positive direction from the current plane position
        //so error checking to make sure dist is positive would be redundant
        dist = thisPlane.getLocation() - collision.getLocation();
        if(dist < 100){
            return true;
        }
        else{ return false;}
    }
    /**
     * 
     * @param plane 
     */
    public void setWarningLevelGreen(Aircraft plane){
        
    }
    /**
     * 
     * @param plane 
     */
    public void setWarningLevelYellow(Aircraft plane){
        //generate instructions??
    }
    /**
     * 
     * @param plane 
     */
    public void setWarningLevelOrange(Aircraft plane){
        //generate instructions??
    }
    /**
     * 
     * @param plane 
     */
    public void setWarningLevelRed(Aircraft plane){
        //generate instructions??
    }
}
