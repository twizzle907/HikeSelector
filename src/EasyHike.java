
/**
 * EasyHike is a subclass of Hike, it sets up an EasyHike object
 * 
 * @author Rikki Swetzof
 * @version CS162 Final Project, 5/29/115
 */
public class EasyHike extends Hike
{
    private final String IMAGEPATHNAME = "images/easy/";
    private final String MAPPATHNAME = "maps/easy/";

    /**
     * Constructor for objects of class EasyHike
     * @param ID, trailName, trailLengh, terrain
     */
    public EasyHike(int ID, String trailName, double trailLength, String terrain)
    {
        super(ID, trailName, trailLength, terrain);
        level="easy";
        hikeImage = IMAGEPATHNAME + trailName + ".jpg";
        hikeMap = MAPPATHNAME + trailName + ".jpg";
    }

    /**
     * completeHike() reserved for later use
     */
    public void completeHike()
    {
        //for later use
    }

    /**
     * getImageName() returns the path for the image 
     * @return hikeImage
     */
    public String getImageName()
    {
        return hikeImage;
    }

    /**
     * getMapName() returns he path for the map
     * @return hikeMap
     */
    public String getMapName()
    {
        return hikeMap;
    }
}