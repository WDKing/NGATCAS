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
    public WarningLevelCalculator(){
        
    }
    
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
    
    public Collision detectCollision(Aircraft one, Aircraft two){
        //check to see if the aircraft will collide
        //math includes the math to find if the two vector headings 
        //will intersect
        //add to array
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