
/**
 * This abstract class is to set up hike objects, the subclasses are: Easy, Medium, and Difficult
 * 
 * @author Rikki Swetzof
 * @version CS162 Final Project, 5/29/15
 */
public abstract class Hike
{
    private int hikeID;
    private String trailName;
    private double trailLength;
    protected String level;
    private String terrain;
    protected String hikeImage;
    protected String hikeMap;

    /**
     * The constructor sets up differen hike objecs
     * @param, ID, trailName, trailLength, terrain
     */
    public Hike(int ID, String trailName, double trailLength, String terrain)
    {
        // Constructor
        ID = hikeID;
        this.trailName = trailName;
        this.trailLength = trailLength;
        this.terrain = terrain;
    }

    /**
     * The completeHike method is for later use.
     */
    public abstract void completeHike();

    /**
     * getTerrain() returns the value for the terrain field
     * @return terrain
     */
    public String getTerrain()
    {
        return terrain;
    }

    /**
     * getTrailName() returns the value for the trailName
     * @return trailName
     */
    public String getTrailName()
    {
        return trailName;
    }

    /**
     * getTrailLength() returns the length of the trail
     * @return trailLength
     */
    public double getTrailLength()
    {
        return trailLength;
    }

    /**
     * getLevel() returns the difficulty level of the hike
     * @return level
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * getImageName() reurns the string for the image path
     * @return imageName
     */
    public abstract String getImageName();

    /**
     * getMapName() returns the string for the map path
     * @return mapName
     */
    public abstract String getMapName();
}
