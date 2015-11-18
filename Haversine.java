/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nextgen;

//This class has formulas used to calculate lat/long conversions
public class Haversine {

    final static double EARTH_RADIUS = 2.0902e+7; //6,371km converted to feet

    //find the distance between 2 lat/long pairs
    public static double calcDistance(Aircraft us, Aircraft them){
        double a, c;

        double sinLat = Math.sin(Math.toRadians((them.getLocation()[0] - us.getLocation()[0]) / 2.0));
        double sinLong = Math.sin(Math.toRadians((them.getLocation()[1] - us.getLocation()[1]) / 2.0));

        a = sinLat * sinLat + Math.cos(Math.toRadians(us.getLocation()[0])) * Math.cos(Math.toRadians(them.getLocation()[0])) *
                sinLong * sinLong;

        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_RADIUS * c;
    }
    //Overloaded the calcDistance class to take an aircraft and collision
    //used in WarningLevelCalculator inside method testCollisionPoint
    public static double calcDistance(Aircraft us, Collision coll){
        double a, c;

        double sinLat = Math.sin(Math.toRadians((coll.getLocation()[0] - us.getLocation()[0]) / 2.0));
        double sinLong = Math.sin(Math.toRadians((coll.getLocation()[1] - us.getLocation()[1]) / 2.0));

        a = sinLat * sinLat + Math.cos(Math.toRadians(us.getLocation()[0])) * Math.cos(Math.toRadians(coll.getLocation()[0])) *
                sinLong * sinLong;

        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return EARTH_RADIUS * c;
    }

    //find the bearing in degrees for travel from point a to point b (0 is north)
    public static double calcBearing(double[] a, double[] b)
    {
        double deltaLong = b[1] - a[1];
        deltaLong = Math.toRadians(deltaLong);
        double a0 = Math.toRadians(a[0]);
        double b0 = Math.toRadians(b[0]);
        double bearing = Math.atan2(Math.sin(deltaLong) * Math.cos(b0),
                Math.cos(a0) * Math.sin(b0) - Math.sin(a0) * Math.cos(b0) * Math.cos(deltaLong));
        //normallize
        bearing = Math.toDegrees(bearing);
        bearing = (bearing + 360.0) % 360.0;
        return bearing;
    }
    
      //pass in an aircraft and time to get position after that time using the aircraft's velocity.
    //make sure time is in the correct format
    public static double[] calcEndCoords(Aircraft aircraft, double time)
    {
        double[] endPoint = new double[3];
        double[] loc = aircraft.getLocation();
        double[] vel = aircraft.getHeading();

        double distanceLL = Math.sqrt(vel[0] * vel[0] + vel[1] * vel[1]);
        double angDistance = distanceLL / EARTH_RADIUS;
        double bearing = Math.atan(vel[1]/vel[0]);
        if(bearing < 0)
            bearing += 2 * Math.PI;
        endPoint[0] = Math.asin(Math.sin(loc[0]) * Math.cos(angDistance) +
                Math.cos(loc[0]) * Math.sin(angDistance) * Math.cos(bearing));
        endPoint[1] = loc[1] + Math.atan2(Math.sin(bearing) * Math.sin(angDistance) * Math.cos(loc[0]),
                Math.cos(angDistance) - Math.sin(loc[0]) * Math.sin(endPoint[0]));
        endPoint[2] = loc[2] + vel[2] * time;

        return endPoint;
    }

    // given a desired collision point, time from start to collison, and the velocity/headings of two aircrafts,
    // determine the lat/long/alt of their starting points returned as a 6 slot array with
    // plane 1's as 0-2, plave 2's as 3-5
    // velocity is [0]e/w [1]n/s [2]altitudinal
    // collisionPoint[0] is latitude [1] is longitude
    public static double[] calcStartPoints(double[] cp, double time, double[] vel1, double[] vel2)
    {
        double[] collisionPoint = new double[3];
        double[] results = new double[6];

        collisionPoint[0] = Math.toRadians(cp[0]); //lat
        collisionPoint[1] = Math.toRadians(cp[1]); //long
        collisionPoint[2] = cp[2]; // altitude
        //determine lat/long travelled distance
        double craft1DistanceTravelledLL = Math.sqrt(vel1[0] * vel1[0] + vel1[1] * vel1[1]) * time;
        double craft2DistanceTravelledLL = Math.sqrt(vel2[0] * vel2[0] + vel2[1] * vel2[1]) * time;

        //reminder: decimal degrees = degrees + minutes/60 + seconds/3600

        //traverse backwards along heading to get to start point. reminder: north and east are positive, north is 0 degrees
        double craft1HeadingLL = Math.atan(vel1[0] / vel1[1]);
        double craft1StartLat = Math.asin(Math.sin(collisionPoint[0]) * Math.cos(craft1DistanceTravelledLL / EARTH_RADIUS) +
                                Math.cos(collisionPoint[0]) * Math.sin(craft1DistanceTravelledLL / EARTH_RADIUS) *
                                Math.cos(-craft1HeadingLL));
        double craft1StartLong = collisionPoint[1] + Math.atan2(Math.sin(-craft1HeadingLL) * Math.sin(craft1DistanceTravelledLL / EARTH_RADIUS) *
                                Math.cos(collisionPoint[0]), Math.cos(craft1DistanceTravelledLL / EARTH_RADIUS) - (Math.sin(collisionPoint[0]) *
                                Math.sin(craft1StartLat)));

        double craft2HeadingLL = Math.atan(vel2[0] / vel2[1]);
        double craft2StartLat = Math.asin(Math.sin(collisionPoint[0]) * Math.cos(craft2DistanceTravelledLL / EARTH_RADIUS) +
                Math.cos(collisionPoint[0]) * Math.sin(craft2DistanceTravelledLL / EARTH_RADIUS) *
                        Math.cos(-craft2HeadingLL));
        double craft2StartLong = collisionPoint[1] + Math.atan2(Math.sin(-craft2HeadingLL) * Math.sin(craft2DistanceTravelledLL / EARTH_RADIUS) *
                Math.cos(collisionPoint[0]), Math.cos(craft2DistanceTravelledLL / EARTH_RADIUS) - (Math.sin(collisionPoint[0]) *
                Math.sin(craft2StartLat)));

        results[0] = Math.toDegrees(craft1StartLat);
        results[1] = Math.toDegrees(craft1StartLong);
        results[2] = collisionPoint[2] - vel1[2];
        results[3] = Math.toDegrees(craft2StartLat);
        results[4] = Math.toDegrees(craft2StartLong);
        results[5] = collisionPoint[2] - vel2[2];

        return results;
    }

    public static double[] calcSingleStartPoint(double[] point, double time, double[] vel1)
    {
        double[] collisionPoint = new double[3];
        double[] results = new double[3];

        collisionPoint[0] = Math.toRadians(point[0]); //lat
        collisionPoint[1] = Math.toRadians(point[1]); //long
        collisionPoint[2] = point[2]; // altitude
        //determine lat/long travelled distance
        double craft1DistanceTravelledLL = Math.sqrt(vel1[0] * vel1[0] + vel1[1] * vel1[1]) * time;

        //reminder: decimal degrees = degrees + minutes/60 + seconds/3600

        //traverse backwards along heading to get to start point. reminder: north and east are positive, north is 0 degrees
        double craft1HeadingLL = Math.atan(vel1[0] / vel1[1]);
        double craft1StartLat = Math.asin(Math.sin(collisionPoint[0]) * Math.cos(craft1DistanceTravelledLL / EARTH_RADIUS) +
                Math.cos(collisionPoint[0]) * Math.sin(craft1DistanceTravelledLL / EARTH_RADIUS) *
                        Math.cos(-craft1HeadingLL));
        double craft1StartLong = collisionPoint[1] + Math.atan2(Math.sin(-craft1HeadingLL) * Math.sin(craft1DistanceTravelledLL / EARTH_RADIUS) *
                Math.cos(collisionPoint[0]), Math.cos(craft1DistanceTravelledLL / EARTH_RADIUS) - (Math.sin(collisionPoint[0]) *
                Math.sin(craft1StartLat)));


        results[0] = Math.toDegrees(craft1StartLat);
        results[1] = Math.toDegrees(craft1StartLong);
        results[2] = collisionPoint[2] - vel1[2];

        return results;
    }
}

