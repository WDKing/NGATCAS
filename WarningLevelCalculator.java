/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Adam
 */
public class WarningLevelCalculator {
    ArrayList<Aircraft> nearList;
    Aircraft thisPlane;
    //CollisionCalculator collCalc;
    TextComm textComm;
    ArrayList<Collision> collisions;
    Haversine haversine = new Haversine();

    static final double SAFE_ZONE = 3 * 1.15078 * 5280; //safe zone in feet

    
    public WarningLevelCalculator(Aircraft thisPlane, ArrayList<Aircraft> nearList){
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

    //this aircraft is one, other is two
    public Collision detectCollision(Aircraft one, Aircraft two){
        //check to see if the aircraft will collide
        //math includes the math to find if the two vector headings 
        //will intersect
        //add to array


        Collision collision = null;
        //load location and velocity data into Vector3 objects by relative cartesian position with us at the origin
        //for easier readability and math
        double distance = haversine.calcDistance(one, two);
        double bearing = haversine.calcBearing(one.getLocation(), two.getLocation());
        bearing += 90;
        bearing = Math.toRadians(bearing);
        double[] twoPos = new double[3];
        twoPos[0] = Math.cos(bearing) * distance;
        twoPos[1] = Math.sin(bearing) * distance;
        twoPos[2] = two.getLocation()[2];
        Vector3 usPos = new Vector3(0, 0, one.getLocation()[2]);
        Vector3 themPos = new Vector3(twoPos[0], twoPos[1], twoPos[2]);

        //convert velocity from knots (nm/hr) to ft/sec
        double[] oneVel = one.getHeading();
        double[] twoVel = two.getHeading();
        for (int i = 0; i < 3; i++)
        {
            oneVel[i] *= 1.15078 * 5280.0 / 60.0;
            twoVel[i] *= 1.15078 * 5280.0 / 60.0;
        }
        Vector3 usVel = new Vector3(oneVel[0], oneVel[1], oneVel[2]);
        Vector3 themVel = new Vector3(twoVel[0], twoVel[1], twoVel[2]);
        Vector3 themVelMinusUsVel = themVel.minus(usVel);
        Vector3 themPosMinusUsPos = themPos.minus(usPos);

        //determine if the two planes can collide in 2 dimensions (x,y)
        double a = themVelMinusUsVel.dot(themVelMinusUsVel);
        double b = 2 * (themVelMinusUsVel.dot(themPosMinusUsPos));
        double c = themPosMinusUsPos.dot(themPosMinusUsPos) - ((2 * SAFE_ZONE) * (2 * SAFE_ZONE));
        double u = b * b - (4 * a * c);

        if(u < 0)
        {//no collision will occur on (x,y)
            System.out.println("u < 0");
            return null;
        }
        else
        {//otherwise potential collision
            double s = Math.sqrt(u);
            double root1 = (-b + s) / (2 * a);
            double root2 = (-b - s) / (2 * a);

            if((root1 <= 0 && root2 <= 0))
            { //parallel or grazed - no collision on (x,y)
                //System.out.println("root1 & 2 <= 0");
                return null;
            }
            else
            {//possible collision
                //check altitude difference at root1 and root2 for collision on (z)
                double diff1 = Math.abs((usPos.z + usVel.z * root1) - (themPos.z + themVel.z * root1));
                double diff2 = Math.abs((usPos.z + usVel.z * root2) - (themPos.z + themVel.z * root2));
                if(diff1 > 900. && diff2 > 900.0)
                {//altitudes are always a safe distance away, no collision n z
                    //System.out.println("altitude non-overlap");
                    return null;
                }
                System.out.println(a);
                System.out.println(b);
                System.out.println(c);
                System.out.println(u);
                //if we've come this far, there is a collision so
                //smallest root is time of collision
                long milliseconds = 0;
                if(root1 < root2)
                {
                    //System.out.println("Collision in " + root1 + " seconds");
                    root1 *= 1000;
                    milliseconds = (long)root1;
                }
                else
                {
                    //System.out.println("Collision in " + root2 + " seconds");
                    root2 *= 1000;
                    milliseconds = (long)root2;
                }

                //create collision object
                Date collisionTime = new Date(System.currentTimeMillis() + milliseconds);
                collision = new Collision(one.getId(), two.getId(), collisionTime);
            }
        }

        return collision;
    
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