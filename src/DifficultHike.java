/**
 * DifficultHike is a subclass of Hike, it instantiates DifficultHike objects
 * 
 * @author Rikki Swetzof
 * @version CS162, Final Project, 6/1/15
 */
public class DifficultHike extends Hike
{
    private final String IMAGEPATHNAME = "images/difficult/";
    private final String MAPPATHNAME = "maps/difficult/";

    /**
     * Constructor for objects of class DifficultHike
     * @param ID, trailName, trailLength, terrain
     */
    public DifficultHike(int ID, String trailName, double trailLength, String terrain)
    {
        super(ID, trailName, trailLength, terrain);
        level="difficult";
        hikeImage = IMAGEPATHNAME + trailName + ".jpg";
        hikeMap = MAPPATHNAME + trailName + ".jpg";
    }

    /**
     * completeHike() is reserved for future use
     */
    public void completeHike()

    {
        //save for later use
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