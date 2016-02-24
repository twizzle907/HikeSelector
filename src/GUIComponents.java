import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/**
 * GUIComponents creates the user interface screens for the Hike Selector
 * 
 * @author Rikki Swetzof
 * @version CS162, Final Project, 5/22/15
 */
public class GUIComponents //implements ActionListener
{
    private JFrame frame;
    private JMenuBar menubar;
    private JPanel contentPane; 
    private JLabel welcomeLabel;
    private JTextField nameTextField;
    private JPanel buttonPanel;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton difficultButton;
    private JButton selectButton;
    private JButton hikeSelect;
    private JLabel selectLabel;
    private JPanel checkBoxPanel;
    private JCheckBox mountainCheckBox;
    private JCheckBox waterfallCheckBox;
    private JCheckBox oceanCheckBox;
    private JCheckBox hillsCheckBox;
    private JPanel mapPanel;
    private JPanel sideBarPanel;
    private JPanel welcomePanel;
    protected Hiker hiker;
    private HashMap hikes;
    protected static ArrayList<Hike> checkBoxItems = new ArrayList<Hike>();
    private String level;
    private String terrain;
    private String trailSelection;
    protected JCheckBox[] checkBoxList;
    private JLabel welcomeImage;
    private JButton hikeSelectButton;
    private JScrollPane scroll;

    /**
     * Constructor for objects of class GUIComponents makes the main screen
     */
    public GUIComponents()
    {
        makeMainScreen();

    }

    /**
     * makeMainScreen() sets up the frame and initial panel, calls makeMenu(), and welcomeScreen()
     */
    public void makeMainScreen()
    {
        frame = new JFrame("Hike Selector");
        contentPane = (JPanel)frame.getContentPane();
        //contentPane.setBorder(new EmptyBorder (12,12,12,12)));  //Later decide on border
        contentPane.setBackground(Color.BLUE);//pick more colors
        contentPane.setLayout(new BorderLayout());

        makeWelcomeScreen();
        makeMenu(frame);

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Sets up the initial screen, allows the user to select from easy, medium, and difficult hikes
     * upon a button selection calls selectLevel()
     */
    public void makeWelcomeScreen()
    {
        //Welcome Panel
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        contentPane.add(welcomePanel, BorderLayout.NORTH);

        //Welcome Panel Content
        welcomeLabel = new JLabel("Welcome to the hike selector!  Please enter your name:");
        welcomePanel.add(welcomeLabel);
        nameTextField = new JTextField(20);
        welcomePanel.add(nameTextField);

        //Sidebar Panel -- Button panel fits inside
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new BoxLayout(sideBarPanel, BoxLayout.Y_AXIS));
        contentPane.add(sideBarPanel, BorderLayout.WEST);

        //Button Panel -- goes inside of Sidebar Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout (new GridLayout (0,1));
        sideBarPanel.add(buttonPanel);
        selectLabel = new JLabel("Hike Level");
        buttonPanel.add(selectLabel);

        //Buttons
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        difficultButton = new JButton ("Difficult");
        buttonPanel.add(easyButton);//creates hiker, loads criteria, loads trailnames to an array
        easyButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {level="easy"; levelSelect();}
            });
        buttonPanel.add(mediumButton);//add creates hiker, loads criteria, loads trailnames to an array
        mediumButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {level="medium"; levelSelect();}
            });

        buttonPanel.add(difficultButton);//creates hiker, loads criteria screen, loads trailnames to an array
        difficultButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {level="difficult";levelSelect();}
            });

        //Welcome Image
        welcomeImage = new JLabel(new ImageIcon("images/South Sister Summit.jpg"));//change this to a random image
        contentPane.add(welcomeImage, BorderLayout.EAST);
    }

    /**
     * makes the menu
     */
    public void makeMenu(JFrame frame)
    {
        //menu bar
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        //File Menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener (new ActionListener() {
                public void actionPerformed(ActionEvent e) {save(); }
            });
        fileMenu.add(saveItem);

        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {quit();}
            });
        fileMenu.add(quitItem);

        //Help Menu
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {about();}
            });
        helpMenu.add(aboutItem);
    }

    /**
     * The criteria screen allows the user to choose what type of terrain they are selecting
     * called by levelSelect()
     * calls criteriaSelect()
     */
    public void makeCriteriaScreen()
    {
        //remove visibility of previous items
        contentPane.remove(sideBarPanel);
        nameTextField.setVisible(false);
        easyButton.setVisible(false);
        mediumButton.setVisible(false);
        difficultButton.setVisible(false);

        welcomeLabel.setText("Welcome " + hiker.getName()+"!  Please make your selections below"); //add name later
        selectLabel.setText("Select hike type:");

        //Checkbox panel
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout (0,1));
        contentPane.add(checkBoxPanel, BorderLayout.CENTER);

        //Items for checkbox & add to a button group
        ButtonGroup terrainGroup = new ButtonGroup();
        mountainCheckBox = new JCheckBox("mountain", false);
        waterfallCheckBox = new JCheckBox("waterfall", false);
        oceanCheckBox = new JCheckBox("ocean", false);
        hillsCheckBox = new JCheckBox("hills", false);

        terrainGroup.add(mountainCheckBox);
        terrainGroup.add(waterfallCheckBox);
        terrainGroup.add(oceanCheckBox);
        terrainGroup.add(hillsCheckBox);

        checkBoxPanel.add(mountainCheckBox);
        checkBoxPanel.add(waterfallCheckBox);
        checkBoxPanel.add(oceanCheckBox);
        checkBoxPanel.add(hillsCheckBox);

        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {criteriaSelect();}
            });
        contentPane.add(selectButton, BorderLayout.SOUTH);

        frame.pack();
    }

    /**
     * This method populates the list of hikes
     * called by the criteriaSelect() method, calls hikeSelect()
     */
    public void makeSelectionScreen()
    {
        //remove items
        checkBoxPanel.removeAll();
        selectButton.setVisible(false);

        //add new items
        selectLabel.setText("Where would you like to go today?");

        //add radio buttons to select the hikes for an array of hikes meeting criteria, then add them to the buttonGroup

        //add buttonGroup
        ButtonGroup hikeGroup = new ButtonGroup();

        //radio buttons for terrain
        if(mountainCheckBox.isSelected())
        { 
            //get the arrayList of items that match the criteria  
            terrain = "mountain";
        }

        if(waterfallCheckBox.isSelected())
        {
            //get the arrayList of items that match the criteria  
            terrain = "waterfall";
        }

        if(oceanCheckBox.isSelected())
        {
            //get the arrayList of items that match the criteria  
            terrain = "ocean";
        }

        if(hillsCheckBox.isSelected())
        {
            //get the arrayList of items that match the criteria  
            terrain = "hills";
        }

        //get array items for checkBox send over to HikeSelector ArrayList of Hikes
        //ArrayList<Hike> checkBoxItems = new ArrayList<Hike>();//just changed
        checkBoxItems = HikeSelector.getTerrainList(level, terrain);
        
        int numberOfCheckBoxes = checkBoxItems.size();
        JCheckBox[] checkBoxList = new JCheckBox[numberOfCheckBoxes];//just changed

        //populate checkBoxes from the resulting array
        for(int i = 0; i<checkBoxItems.size(); ++i)
        {
            checkBoxList[i]= new JCheckBox (checkBoxItems.get(i).getTrailName(), false);
            hikeGroup.add(checkBoxList[i]);
            checkBoxPanel.add(checkBoxList[i]);
        }
        checkBoxPanel.setVisible(true);
        contentPane.add(checkBoxPanel, BorderLayout.CENTER); 

        //add button and action listener
        hikeSelectButton = new JButton ("select");
        contentPane.add(hikeSelectButton, BorderLayout.SOUTH);
        hikeSelectButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    //search checkboxes for selected item to send to the mapScreen

                    for(int j =0; j<checkBoxList.length; ++j)
                    {
                        if(checkBoxList[j].isSelected())
                        {
                            trailSelection = checkBoxList[j].getText();
                        }
                    }
                    hikeSelect(trailSelection);}
            });
        contentPane.setVisible(true);
        frame.pack();  

    }

    /**
     * create the screen that displays the hike image and map image
     * called by hikeSelect(), calls startOver() and hikeComplete()
     */
    public void makeMapScreen(String trailSelection)
    {
        //remove old items
        checkBoxPanel.removeAll();
        contentPane.removeAll();
        selectButton.setVisible(false);
        hikeSelectButton.setVisible(false);
        buttonPanel.remove(selectLabel);
        contentPane.remove(buttonPanel);
        contentPane.remove(selectButton);
        contentPane.remove(welcomeImage);

        //add new items
        contentPane.add(welcomePanel, BorderLayout.NORTH);
        contentPane.add(welcomeLabel);
        String trail = trailSelection;
        //new map panel
        mapPanel = new JPanel();
        String map = "maps/" + trailSelection + ".JPG";
        JLabel mapImage = new JLabel(new ImageIcon(map));
        mapPanel.add(mapImage); 
        contentPane.add(mapPanel, BorderLayout.WEST);
        //image panel
        String image = "images/" + trailSelection + ".JPG";
        JLabel hikeImage = new JLabel(new ImageIcon(image));
        contentPane.add(hikeImage, BorderLayout.EAST);

        //add a start over button
        JButton startOverButton = new JButton("Start Over");
        startOverButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {startOver();}
            });
        contentPane.add(startOverButton, BorderLayout.SOUTH);

        //add checkbox to complete this hike and add it to the total
        JCheckBox hikeCompleteCheckBox = new JCheckBox("Complete", false);
        hikeCompleteCheckBox.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {hikeComplete(trailSelection);}
            });
        contentPane.add(hikeCompleteCheckBox, BorderLayout.CENTER);
        contentPane.repaint();
        frame.pack();
    }

    //events based on actionListeners
    /**
     * menu item, shuts down the app
     */
    public void quit()
    {
        System.exit(0);
    }

    /**
     * menu item, saves the hiker and distance
     * @throws IOException
     */
    public void save()
    {
        try
        {
            FileWriter writer = new FileWriter("hiker.txt");
            writer.append("Hiker:  " + hiker.getName() + "\n");
            writer.append("Distance:  " + hiker.totalDistance + "\n");
            writer.close();

        }

        catch(IOException e)
        {

            JOptionPane.showMessageDialog(frame, 
                "There was a problem writing to the file");
        }
    }

    /**
     * menu item version info
     */
    public void about()
    {
        JOptionPane.showMessageDialog(frame, 
            "Hike Selector\n"  +"Author:  Rikki Swetzof\n" + "Version 1.0");
    }

    /**
     * levelSelect() is the action performed when one of the three level buttons is selected
     * pop up will not allow user to continue without a name
     * calls makeCriteriaScreen()
     */
    public void levelSelect()
    {

        String nameText=nameTextField.getText();

        if(nameText.length() ==0)
        {
            JOptionPane.showMessageDialog(frame, 
                "Please enter your name to continue");
        }

        else
        {
            nameText=nameTextField.getText();
            hiker = new Hiker (nameText);
            makeCriteriaScreen();
        }
    }

    /**
     * criteriaSelect() processes the terrain selection and makes sure a selection is made
     * @throws NoSelectionMadeException
     */
    public void criteriaSelect()
    {
        try
        {
            checkCriteriaSelection();
            makeSelectionScreen();
        }

        catch (NoSelectionMadeException e)
        {
            JOptionPane.showMessageDialog(frame, e);
        }
    }

    /**
     * hikeSelect() processes the selected hike, and checks to make sure a selection is made
     * @throws NoSelectionMadeException
     */
    public void hikeSelect(String trailSelection)
    {
        try
        {
            checkHikeSelection();
            this.trailSelection = trailSelection;
            makeMapScreen(trailSelection);
        }

        catch (NoSelectionMadeException e)
        {
            JOptionPane.showMessageDialog(frame, e);
        }

    }

    /**
     * startOver() clears the screen and allows the user to begin again
     */
    public void startOver()
    {
        
        contentPane.removeAll();
        checkBoxPanel.removeAll();
        checkBoxItems.removeAll(checkBoxItems);
        makeWelcomeScreen();
        frame.pack();
    }

    /**
     * hikeComplete() gets the distance for the selected hike and adds it to the user's total
     */
    public void hikeComplete(String trailSelection)
    {
        this.trailSelection = trailSelection;
        double distance = HikeSelector.getDistance(trailSelection);
        hiker.totalDistance = hiker.totalDistance + distance;
        welcomeLabel.setText("Congratulations " + hiker.getName()+ ",");
        JLabel totalDistance = new JLabel ("Your cumulative distance is: " + hiker.totalDistance + " miles");
        welcomePanel.add(totalDistance);
        welcomePanel.setVisible(true);
        frame.pack();
    }

    /**
     * checkCriteriaSelection() checks to make sure the user selected the hike criteria before clicking select
     * @throws NoSelectionMadeException
     */
    public void checkCriteriaSelection() throws NoSelectionMadeException
    {
        if((!(mountainCheckBox.isSelected())) && (!(waterfallCheckBox.isSelected())) && (!(hillsCheckBox.isSelected())) && (!(oceanCheckBox.isSelected())))
        {
            throw new NoSelectionMadeException();
        }
    }

    /**
     * checkHikeSelection() checks to make sure the user selected a hike before clicking the select button
     * @throws NoSelectionMadeException
     */
    public void checkHikeSelection() throws NoSelectionMadeException
    {
        if(trailSelection == null)
            throw new NoSelectionMadeException();
    }
}