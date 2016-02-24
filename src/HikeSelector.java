import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;
/**
 * The HikeSelector class is the entry point for the application.  It makes the call to create all of the hike objects, the GUI, and creates an ArrayList for the GUI to reference.
 * 
 * @author Rikki Swetzof
 * @version CS162 Final Project, 5/29/15
 */
public class HikeSelector
{
    private int checkBoxCount=0;
    protected static ArrayList<Hike> hikeName = new ArrayList<Hike>();
    protected static ArrayList<Hike> checkBoxItems = new ArrayList<Hike>();
    private static double distance;

    //constructor
    /**
     * The constructor calls the createHikes(), which instantiates all of the hike objects
     */
    public HikeSelector()
    {
        createHikes();
    }

    /**
     * createHikes() creates all of the Hike objects, and adds them to an ArrayList
     */
    public void createHikes()
    {
        //load the hike objects and add to hikeName ArrayList
        //easy hikes
        hikeName.add(new EasyHike(0, "McDowell Creek", 2.2, "waterfall"));
        hikeName.add(new EasyHike(2, "McKenzie River Trail", 4.0, "hills"));
        hikeName.add(new EasyHike(3, "Silver Falls", 10.0, "waterfalls"));
        hikeName.add(new EasyHike(4, "Sweet Creek", 3.0, "hills"));
        hikeName.add(new EasyHike(4, "Mount Pisgah", 3.5, "mountain"));
        hikeName.add(new EasyHike(5, "Fort Stevens", 2.3, "ocean"));

        //medium Hikes
        hikeName.add(new MediumHike(5, "Kentuky Falls", 6.1, "waterfall"));
        hikeName.add(new MediumHike(6, "Marys Peak", 6.2, "hills"));
        hikeName.add(new MediumHike(7, "Lower MacLeay Park", 3.9, "hills"));
        hikeName.add(new MediumHike(8, "Table Rock", 7.8, "mountain"));
        hikeName.add(new MediumHike(9, "Peavy Arboretum", 4.2, "hills"));
        hikeName.add(new MediumHike(10, "Cape Mears", 3.2, "ocean"));

        //difficult Hikes
        hikeName.add(new DifficultHike(6, "Mt Hood South Side", 8.2, "mountain"));    
        hikeName.add(new DifficultHike(6, "South Sister Summit", 12.0, "mountain"));
        hikeName.add(new DifficultHike(6, "Mt Defiance Summit", 12.5, "mountain"));
        hikeName.add(new DifficultHike(6, "Iron Mountain", 5.5, "hills"));
        hikeName.add(new DifficultHike(12, "Ramona Falls", 7.2, "waterfall"));
        hikeName.add(new DifficultHike(13, "Humbug Mountain", 3.2, "ocean"));

        /*hikes maps and images were borrowed from the internet from the State of Oregon website and everytrail.com
        ,where possible, I left the photo credit.*/

    }
    /**
     * getTerrainList()searches the hikeName ArrayList for the specific terrain and level indicated by the GUI checkboxes
     * @return checkBoxItems
     */
    public static ArrayList<Hike> getTerrainList(String level, String terrain)
    {
        for(int i=0; i<hikeName.size(); ++i)
        {
            if((hikeName.get(i).getLevel().contains(level)) &&(hikeName.get(i).getTerrain().contains(terrain)))
            {
                checkBoxItems.add(hikeName.get(i));
            }
        }
        return checkBoxItems;
    }

    /**
     * getDistance() searches the hikeName ArrayList to return the distance for the selected trail from the GUI
     * @param trailSelection
     * @return distance
     */
    public static double getDistance(String trailSelection)
    {
        for(int i=0; i<hikeName.size(); ++i)
        {
            if((hikeName.get(i).getTrailName().contains(trailSelection)))
            {
                distance = hikeName.get(i).getTrailLength();
                //checkBoxItems.add(hikeName.get(i));
            }
        }
        return distance;
    }

    /**
     * main() creates a new instance of the HikeSelector and GUI
     */
    public static void main (String args[])
    {
        HikeSelector hikes = new HikeSelector();
        GUIComponents gui = new GUIComponents();
    }
}
