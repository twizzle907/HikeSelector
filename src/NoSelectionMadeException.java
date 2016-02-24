
/**
 * NoSelectionMadeException is a checked exception thrown when a button is clicked in the GUI without making a selection.
 * 
 * @author Rikki Swetzof
 * @version CS162, Final Project, 5/31/15
 */
public class NoSelectionMadeException extends Exception
{

    /**
     * Constructor for objects of class NoSelectionMadeException
     */
    public NoSelectionMadeException()
    {

    }

    /**
     * overrides the toString method
     * @return string
     */
    public String toString()
    {
        return "Please make a selection";
    }
}
