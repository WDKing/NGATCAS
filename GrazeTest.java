import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class GrazeTest extends TestCase {

    ArrayList<Aircraft> testNearList = new ArrayList<Aircraft>();
    ADSBInterface testClass;
    Random rng;

    public static void main(String[] args)
    {
        GrazeTest it = new GrazeTest();
    }

    public GrazeTest()
    {
        Haversine haversine = new Haversine();

        //create starting coordinates for grazes at specified collision points given times and headings
        //degrees, feet, seconds, and ft/sec used

        //near miss where one aircraft is moving faster than the other
        double[] collisionPoint = new double[] {39.0, -105.5, 35000};
        double[] vel1 = new double[] { 513.33, 0, 0};
        double[] vel2 = new double[] { 0, 366.66, 0};
        double[] hResults1 = haversine.calcStartPoints(collisionPoint, 480, vel1, vel2);
        System.out.println("Start points lat, long, alt for 1 faster aircraft:");
        System.out.println("Craft1 : " + hResults1[0] + ", " + hResults1[1] + ", " + hResults1[2]);
        System.out.println("Craft2 : " + hResults1[3] + ", " + hResults1[4] + ", " + hResults1[5] + "\n");

        try {
            double[] c1Coords = new double[] {hResults1[0], hResults1[1], hResults1[2]};
            double[] c2Coords = new double[] {hResults1[3], hResults1[4], hResults1[5]};
            runTest(c1Coords, vel1, c2Coords, vel2);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        //near miss where both aircraft are moving equally fast
        double[] collisionPoint2 = new double[] {39.0, -105.5, 35000};
        double[] vel1_2 = new double[] { 405.5, 0, 0};
        double[] vel2_2 = new double[] { 0, 405.5, 0};
        double[] hResults2 = haversine.calcStartPoints(collisionPoint2, 480, vel1_2, vel2_2);
        System.out.println("Start points lat, long, alt for equal speed aircraft:");
        System.out.println("Craft1 : " + hResults2[0] + ", " + hResults2[1] + ", " + hResults2[2]);
        System.out.println("Craft2 : " + hResults2[3] + ", " + hResults2[4] + ", " + hResults2[5] + "\n");

        try {
            double[] c1Coords = new double[] {hResults2[0], hResults2[1], hResults2[2]};
            double[] c2Coords = new double[] {hResults2[3], hResults2[4], hResults2[5]};
            runTest(c1Coords, vel1_2, c2Coords, vel2_2);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        //near miss where one aircraft is not moving
        double[] collisionPoint3 = new double[] {39.0, -105.5, 35000};
        double[] vel1_3 = new double[] { 513.33, 0, 0};
        double[] vel2_3 = new double[] { 0, 0, 0};
        double[] hResults3 = haversine.calcStartPoints(collisionPoint3, 480, vel1_3, vel2_3);
        System.out.println("Start points lat, long, alt for one non-moving craft:");
        System.out.println("Craft1 : " + hResults3[0] + ", " + hResults3[1] + ", " + hResults3[2]);
        System.out.println("Craft2 : " + collisionPoint3[0] + ", " + collisionPoint3[1] + ", " + collisionPoint3[2] + "\n");

        try {
            double[] c1Coords = new double[] {hResults3[0], hResults3[1], hResults3[2]};
            double[] c2Coords = new double[] {hResults3[3], hResults3[4], hResults3[5]};
            runTest(c1Coords, vel1_3, c2Coords, vel2_3);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        //near miss where both aircraft are moving equally fast but with a 900 ft altitude difference and crossing the same point at the same time
        double[] overlapCenterLL1 = new double[] {39.0, -105.5, 35000};
        double[] overlapCenterLL2 = new double[] {39.0, -105.5, 34100};
        double[] vel1_4 = new double[] { 405.5, 0, 0};
        double[] vel2_4 = new double[] { 0, 405.5, 0};
        double[] c1Start = haversine.calcSingleStartPoint(overlapCenterLL1, 480, vel1_4);
        double[] c2Start = haversine.calcSingleStartPoint(overlapCenterLL2, 480, vel2_4);
        System.out.println("Start points lat, long, alt for equal speed aircraft with 900 ft altitude difference:");
        System.out.println("Craft1 : " + c1Start[0] + ", " + c1Start[1] + ", " + c1Start[2]);
        System.out.println("Craft2 : " + c2Start[0] + ", " + c2Start[1] + ", " + c2Start[2] + "\n");

        try {
            double[] c1Coords = new double[] {c1Start[0], c1Start[1], c1Start[2]};
            double[] c2Coords = new double[] {c2Start[0], c2Start[1], c2Start[2]};
            runTest(c1Coords, vel1_4, c2Coords, vel2_4);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        //near miss where one aircraft is not moving but with a 900 ft altitude difference and crossing the same point at the same time
        double[] overlapCenterLL1_2 = new double[] {39.0, -105.5, 35000};
        double[] overlapCenterLL2_2 = new double[] {39.0, -105.5, 34100};
        double[] vel1_5 = new double[] { 405.5, 0, 0};
        double[] vel2_5 = new double[] { 0, 0, 0};
        double[] c1Start_2 = haversine.calcSingleStartPoint(overlapCenterLL1_2, 480, vel1_5);
        System.out.println("Start points lat, long, alt for 1 non moving aircraft with 900ft altitude difference:");
        System.out.println("Craft1 : " + c1Start_2[0] + ", " + c1Start_2[1] + ", " + c1Start_2[2]);
        System.out.println("Craft2 : " + overlapCenterLL2_2[0] + ", " + overlapCenterLL2_2[1] + ", " + overlapCenterLL2_2[2] + "\n");

        try {
            double[] c1Coords = new double[] {c1Start[0], c1Start[1], c1Start[2]};
            double[] c2Coords = new double[] {overlapCenterLL2_2[0], overlapCenterLL2_2[1], overlapCenterLL2_2[2]};
            runTest(c1Coords, vel1_5, c2Coords, vel2_5);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void runTest(double[] craft1Coords, double[] craft1Heading, double[] craft2Coords, double[] craft2Heading) throws Exception
    {
        ADSBInterface testADSB;
        WarningLevelCalculator testWLC;
        ArrayList<Aircraft> testNearList = new ArrayList<Aircraft>();

        //create aircraft objects
        Aircraft us = new Aircraft("TST0001", craft1Coords, craft1Heading, 0);
        Aircraft them = new Aircraft("TST0002", craft2Coords, craft2Heading, 0);

        //initiate WarningLevelCalculator
        testWLC = new WarningLevelCalculator(us, testNearList);
        //first ADSB picks up aircraft data for us and them. ADSB.listen() will detect the aircraft and call addAircraft
        testADSB = new ADSBInterface(testNearList);
        testADSB.addAircraft(them);

        //WarningLevelCalculator then checks for collisions and calls other relevant class methods.
        testWLC.parseList();
    }

}