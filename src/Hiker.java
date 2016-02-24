
/**
 * The Hiker class sets up the Hiker object which stores the name and distance a hiker has gone
 * 
 * @author Rikki Swetzof
 * @version CS162, Final Project, 5/22/15
 */

public class Hiker
{
    // instance variables - replace the example below with your own
    protected String name;
    protected double totalDistance;
    //private int badgeLevel; //for later use

    /**
     * Constructor for objects of class Hiker
     * @param name
     */
    public Hiker(String name)
    {
        // initialise instance variables
        this.name = name;
        totalDistance = 0;
        //badgeLevel = 0; // for later use
    }

    /**
     * This method returns the hiker's name
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method returns the hikers total distance
     * @return totalDistance
     */
    public double getTotalDistance()
    {
        return totalDistance;
    }

}