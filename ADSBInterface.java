import java.util.ArrayList;

/**
 * Created by Joshua on 11/2/2015.
 */
public class ADSBInterface {

    ArrayList<Aircraft> otherAircaft = new ArrayList<Aircraft>();

    public ADSBInterface(ArrayList<Aircraft> nearList)
    {
        otherAircaft = nearList;
    }

    public void listen()
    {

    }

    public int addAircraft(Aircraft newAircraft)
    {
        return 0;
    }

}
