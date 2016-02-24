
/**
 * MediumHike is a subclass of Hike, it sets up a Medium Hike object
 * 
 * @author Rikki Swetzof
 * @version CS162, Final Project, 6/1/15
 */
public class MediumHike extends Hike
{
    private final String IMAGEPATHNAME = "images/medium/";
    private final String MAPPATHNAME = "maps/medium/";

    /**
     * Constructor for objects of class MediumHike
     * @param ID, trailName, trailLength, terrain
     */
    public MediumHike(int ID, String trailName, double trailLength, String terrain)
    {
        super(ID, trailName, trailLength, terrain);
        level="medium";
        hikeImage = IMAGEPATHNAME + trailName + ".jpg";
        hikeMap = MAPPATHNAME + trailName + ".jpg";
    }

    /**
     * completeHike() is reserved for future use
     */
    public void completeHike()

    {
        // for later use
    }

    /**
     * getImageName() returns the string for the image path
     * @return hikeImage
     */
    public String getImageName()
    {
        return hikeImage;
    }

    /**
     * getMapName() returns the string for the map path
     * @return mapImage
     */
    public String getMapName()
    {
        return hikeMap;
    }
}