import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class ADSBInterfaceTest extends TestCase {

    ArrayList<Aircraft> testNearList = new ArrayList<Aircraft>();
    ADSBInterface testClass;
    Random rng;

    public static void main(String[] args)
    {
        ADSBInterfaceTest it = new ADSBInterfaceTest();
    }

    public ADSBInterfaceTest()
    {
        rng = new Random();
        double[] l = new double[4];
        double[] h = new double[4];

        for (int i = 0; i < 40; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                l[j] = 0;
                h[j] = 0;
            }
            String id;
            if(i < 10)
                id = "TST100" + i;
            else
                id = "TST10" + i;
            testNearList.add(new Aircraft(id, l, h));
        }
        testClass = new ADSBInterface(null);
        try
        {
            testAddAircraft();
        }
        catch(Exception e)
        {
            System.out.println("Error occurred: " + e.getMessage());
        }

    }

    public void testAddAircraft() throws Exception
    {
        int result;
        //test the addition of 1 aircraft
        result = testClass.addAircraft(testNearList.get(0));
        System.out.println("Aircraft " + testNearList.get(0).getId() + " added. Result: " + determineResult(result));

        //test the addition of multiple aircraft <= 20
        for(int i = 1; i < 20; i++)
        {
            result = testClass.addAircraft(testNearList.get(i));
            System.out.println("Aircraft " + testNearList.get(i).getId() + " added. Result: " + determineResult(result));
        }

        //test the updating of aircraft
        for(int i = 0; i < 20; i++)
        {
            result = testClass.addAircraft(testNearList.get(i));
            System.out.println("Aircraft " + testNearList.get(i).getId() + " added. Result: " + determineResult(result));
        }

        //test the addition of > 20 aircraft. This one should always error out.
        for(int i = 20; i < 40; i++)
        {
            result = testClass.addAircraft(testNearList.get(i));
            System.out.println("Aircraft " + testNearList.get(i).getId() + " added. Result: " + determineResult(result));
        }
    }

    public String determineResult(int result)
    {
        String report;

        switch(result)
        {
            case 1:
                report = "passed as new add.";
                break;
            case 2:
                report = "passed as updated aircraft.";
                break;
            case 0:
            default:
                report = "failed.";
                break;
        }

        return report;
    }
}