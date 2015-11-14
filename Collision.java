// Collision.java

import java.util.Date;

/**
 * Representation of an impending collision between the "ownship" aircraft and
 * another aircraft.
 */
public class Collision implements Comparable<Collision> {

    /** A String representation of the ID of this aircraft */
    private final Aircraft ownship;
    /** The other aircraft that may collide with this one */
    private final Aircraft othership;
    /** The time when the collision is predicted to occur */
    private Date predictedTime;

    /**
     * Sole public constructor, taking parameters for all fields.
     * @param ownship a String representation of the ID of this aircraft
     * @param othership a String representation of the ID of the other
     * aircraft that may collide with this one
     * @param predictedTime the time when the collision is predicted to occur
     */
    public Collision(Aircraft ownship, Aircraft othership, Date predictedTime) {
        this.ownship = ownship;
        this.othership = othership;

        // Make a defensive copy
        this.predictedTime = new Date(predictedTime.getTime());
    }

    /**
     * Collision objects are compared to each other based only on their time
     * values
     * @param o the Collision object to be compared
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Collision o) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Accessor for the object representing this aircraft
     * @return a representation of this aircraft
     */
    public Aircraft getOwnship() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Accessor for the the other aircraft that may collide with this one
     * @return a representation of the other craft
     */
    public Aircraft getOthership() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Accessor for the predicted time of the collision
     * @return a defensive copy of the Date object representing the time when
     * the collision is predicted to occur
     */
    public Date getTime() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Calculate the predicted location of the collision, using ownship to find
     * the current vector and location of this aircraft, and from that
     * determining where it will  be at the time stored in this Collision object
     * @return the location where this aircraft will be at the predicted time of
     * the collision
     */
    public double[] getLocation() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Calculate (this craft's altitude - other craft's altitude) by using
     * ownship and othership to find the locations of each craft, assuming
     * constant altitudes
     * @return this craft's altitude - other craft's altitude
     */
    public double getRelativeAltitude() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
