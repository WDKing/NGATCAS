// CollsionTest.java

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the Collision class. Simple accessor methods like
 * getOwnshipId() and getOthershipId() were not tested, and the constructor was
 * also not tested.
 */
public class CollisionTest {

    private final Collision testColl1;
    private final Collision testColl2;
    private static final int MINUTE_OFFSET1 = 50;
    private static final int MINUTE_OFFSET2 = 10;
    private static final double[] ALL_ZEROS = {0.0,0.0,0.0};
    private static final Aircraft SELF1 =
            new Aircraft("thisone", ALL_ZEROS, ALL_ZEROS, 0);
    private static final Aircraft OTHER1 =
            new Aircraft("otherone", ALL_ZEROS, ALL_ZEROS, 0);
    private static final Aircraft SELF2 =
            new Aircraft("thistwo", ALL_ZEROS, ALL_ZEROS, 0);
    private static final Aircraft OTHER2 =
            new Aircraft("othertwo", ALL_ZEROS, ALL_ZEROS, 0);

    /** xyz velocities */
    private static final double[] HEADING1 = {0.0, 550.0, 0.0};
    /** latitude, longitude, and altitude */
    private static final double[] LOCATION1 = {0.0, 0.0, 1000.0};
    /** xyz velocities */
    private static final double[] HEADING2 = {-550.0, 0.0, 0.0};
    /** latitude, longitude, and altitude */
    private static final double[] LOCATION2 = {550.0, 550.0, 1000.0};

    private static final Aircraft DOOMED_PLANE1 =
            new Aircraft("dp1", HEADING1, LOCATION1, 0);
    private static final Aircraft DOOMED_PLANE2 =
            new Aircraft("dp2", HEADING2, LOCATION2, 0);

    /**
     * Set up the test objects
     */
    public CollisionTest() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.add(Calendar.MINUTE, MINUTE_OFFSET1);
        cal2.add(Calendar.MINUTE, MINUTE_OFFSET2);

        testColl1 = new Collision(SELF1, OTHER1, cal1.getTime());
        testColl2 = new Collision(SELF2, OTHER2, cal2.getTime());
    }

    /**
     * Test of compareTo method, of class Collision.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        int result = testColl1.compareTo(testColl2);
        assertTrue(result > 0);
    }

    /**
     * Test of getTime method, of class Collision.
     * Make sure it is making a new defensive copy each time it is called.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Date testDate1 = testColl1.getTime();
        Date testDate2 = testColl1.getTime();
        assertTrue(testDate1 != testDate2);
    }

    /**
     * Test of getLocation method, of class Collision.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        double expLatitude = 550.0;
        double expLongitude = 550.0;
        double expAltitude = 1000.0;
        WarningLevelCalculator wlc = new WarningLevelCalculator();

        double[] result =
                wlc.detectCollision(DOOMED_PLANE1, DOOMED_PLANE2).getLocation();
        assertEquals(expLatitude, result[0], Math.ulp(expLatitude));
        assertEquals(expLongitude, result[1], Math.ulp(expLongitude));
        assertEquals(expAltitude, result[2], Math.ulp(expAltitude));
    }

    /**
     * Test of getRelativeAltitude method, of class Collision.
     */
    @Test
    public void testGetRelativeAltitude() {
        System.out.println("getRelativeAltitude");
        double expResult = 0.0;
        double result = testColl1.getRelativeAltitude();
        assertEquals(expResult, result, Math.ulp(result));
    }

}
